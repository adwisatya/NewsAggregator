/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myta.engine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import myta.model.Berita;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.meta.RandomizableFilteredClassifier;
import weka.core.Debug;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.ManhattanDistance;
import weka.core.neighboursearch.LinearNNSearch;
import weka.core.tokenizers.WordTokenizer;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * 
 * @author Ahmad Zaky
 */
public class LearningEngine {
    
    // Instance kelas ini
    private static LearningEngine thisInstance = null;
    
    // Atribut terkait learning
    private Instances data = null;
    private FilteredClassifier model = null;
    private Classifier classifier = null;
    private Filter filter = null;
    private NominalToString nominalToString = null;
    
    // Delimiter pada file CSV
    private char CSV_DELIMITER = ';';
    
    // ARFF file yang berisi atribut dari instance yang dipakai:
    // TEXT (string) dan LABEL (nominal)
    private final String MODEL_ARFF = "@relation QueryResult-weka.filters.unsupervised.attribute.NominalToString-C1-2\n" +
            "\n" +
            "@attribute TEXT string\n" +
            "@attribute LABEL {Pendidikan,Politik,'Hukum dan Kriminal','Sosial Budaya',Olahraga,'Teknologi dan Sains',Hiburan,'Bisnis dan Ekonomi',Kesehatan,'Bencana dan Kecelakaan'}\n" +
            "\n" +
            "@data";
    
    // Konstanta terkait basis data
    private final String SQL_QUERY = "SELECT CAST(CONCAT_WS(\" \",`JUDUL`,`JUDUL`,`FULL_TEXT`) " +
            "AS CHAR(10000) CHARACTER SET utf8) AS `TEXT`, `LABEL` from `artikel` " +
            "NATURAL JOIN `artikel_kategori_verified` NATURAL JOIN `kategori`";
    private final String DB_URL = "jdbc:mysql://localhost:3306/news_aggregator";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "";
    
    // Konstanta pada StringToWordVector
    private final int STRING_TO_WORD_VECTOR_WORDS_TO_KEEP = 500;
    private final int STRING_TO_WORD_VECTOR_MIN_TERM_FREQ = 10;
    private final String STRING_TO_WORD_VECTOR_DELIMITER = " \t\r\n`1234567890-=~!@#$%^&*()_+[]\\{}|;':\"–,./<>?“” ";
    
    public LearningEngine() {
        // Inisialisasi NominalToString:
        // Mandatory karena hasil dari database berupa nominal
        nominalToString = new NominalToString();
        nominalToString.setAttributeIndexes("1");
        
        // initialize data format agar dikenal
        try {
            data = new Instances(new StringReader(MODEL_ARFF));
            data.setClass(data.attribute("LABEL"));
        } catch (IOException ex) {
            data = null;
        }
    }
    
    /**
     * Mendapatkan instance dari LearningEngine
     * @return Objek LearningEngine
     */
    public static LearningEngine getInstance() {
        if (thisInstance == null) {
            thisInstance = new LearningEngine();
        }
        return thisInstance;
    }
    
    /**
     * Membangun model berdasarkan data dari database. Model disimpan
     * pada atribut model di kelas ini.
     * 
     * Bentuk instance yang dapat diklasifikasikan oleh model ini
     * adalah instance dengan dua atribut: TEXT(string) dan LABEL(nominal).
     * 
     * Perhatikan bahwa model adalah FilteredClassifier, jadi dibutuhkan
     * filter dan classifier. Untuk mengubah filter maupun classifier,
     * ubahlah kode di bawah komentar "Inisialisasi Classifier dan Filter."
     * 
     * Filter haruslah menerima instance dengan atribut-atribut
     * seperti yang dijelaskan di atas. Filter tidak boleh mengubah atribut
     * LABEL, karena atribut ini adalah atribut kelas.
     * 
     * Classifier haruslah dapat menerima instance hasil filter di atas.
     * 
     * @throws java.lang.Exception
     */
    public void buildModel() throws Exception {
        // Memuat data dari database
        InstanceQuery query = new InstanceQuery();
        query.setDatabaseURL(DB_URL);
        query.setUsername(DB_USERNAME);
        query.setPassword(DB_PASSWORD);
        query.setQuery(SQL_QUERY);
        data = query.retrieveInstances();
        
        // Mengaplikasikan Filter NominalToString
        nominalToString.setInputFormat(data);
        data = Filter.useFilter(data, nominalToString);
        
        // Set atribut kelas
        data.setClass(data.attribute("LABEL"));
        
        // Inisialisasi Classifier dan Filter
//        initializeIBk();
        initializeRandomizableFilteredClassifier();
        initializeStringToWordVector();
        
        // Membangun Model
        model = new FilteredClassifier();
        model.setFilter(filter);
        model.setClassifier(classifier);
        model.buildClassifier(data);
        
        // Evaluate deh biar mantap
        System.out.println("Mengevaluasi dengan skema full training...");
        Evaluation e = new Evaluation(data);
        e.evaluateModel(model, data);
        System.out.println(e.toClassDetailsString());
        System.out.println(e.toSummaryString());
        System.out.println(e.toMatrixString());
        
        System.out.println("\nSelesai membangun model...\n");
    }
    
    /**
     * Memperbarui model dengan berita baru. Berita ini sudah mempunyai
     * label (e.g. masukan dari user).
     * @param berita Berita yang akan dimasukkan ke dataset
     * @throws java.sql.SQLException
     */
    public void updateModel(Berita berita) throws SQLException, Exception {
        if (berita.getKategori() == null) {
            return;
        }
        
        // Masukkan ke basis data terlebih dahulu
        insertRecord(berita);
        
        // Rebuild model
        buildModel();
    }
    
    /**
     * Memperbarui model dengan list berita baru. Berita yang akan
     * dimasukkan hanya yang mempunyai label
     * @param listBerita list berita yang akan dimasukkan ke dataset.
     * @throws Exception 
     */
    public void updateModel(List<Berita> listBerita) throws Exception {
        boolean updated = false;
        for (Berita berita : listBerita) {
            if (berita.getKategori() != null) {
                insertRecord(berita);
                updated = true;
            }
        }
        if (updated) {
            buildModel();
        }
    }
    
    /**
     * Memperbarui model dengan list berita yang diperoleh dari
     * fileInput.
     * @param fileInput file berisi list berita dalam format CSV
     * @throws java.lang.Exception
     */
    public void updateModel(String fileInput) throws Exception {
        Reader reader = new FileReader(fileInput);
        CSVParser parser = new CSVParser(reader, 
                CSVFormat.EXCEL.withHeader().withDelimiter(CSV_DELIMITER));
        List<Berita> listBerita = new ArrayList<>();
        
        for (CSVRecord record : parser) {
            Berita berita = new Berita();
            
            // Add each attribute one by one
            // HTML
            if (record.isMapped("HTML")) {
                berita.setHtml(record.get("HTML"));
            }
            // FULL_TEXT
            if (record.isMapped("FULL_TEXT")) {
                berita.setFullText(record.get("FULL_TEXT"));
            }
//            // TGL_TERBIT
//            if (record.isMapped("TGL_TERBIT")) {
//                berita.setTanggalTerbit(new java.util.Date(record.get("TGL_TERBIT")));
//            }
//            // TGL_CRAWL
//            if (record.isMapped("TGL_CRAWL")) {
//                berita.setTanggalCrawl(new java.util.Date(record.get("TGL_CRAWL")));
//            }
            // JUDUL
            if (record.isMapped("JUDUL")) {
                berita.setJudul(record.get("JUDUL"));
            }
            // URL
            if (record.isMapped("URL")) {
                berita.setUrl(record.get("URL"));
            }
            // INFO_WHAT
            if (record.isMapped("INFO_WHAT")) {
                berita.setInfoWhat(record.get("INFO_WHAT"));
            }
            // INFO_WHERE
            if (record.isMapped("INFO_WHERE")) {
                berita.setInfoWhere(record.get("INFO_WHERE"));
            }
            // INFO_WHY
            if (record.isMapped("INFO_WHY")) {
                berita.setInfoWhy(record.get("INFO_WHY"));
            }
            // INFO_WHO
            if (record.isMapped("INFO_WHO")) {
                berita.setInfoWho(record.get("INFO_WHO"));
            }
            // INFO_WHEN
            if (record.isMapped("INFO_WHEN")) {
                berita.setInfoWhen(record.get("INFO_WHEN"));
            }
            // INFO_HOW
            if (record.isMapped("INFO_HOW")) {
                berita.setInfoHow(record.get("INFO_HOW"));
            }
            // KATEGORI/LABEL/KELAS
            if (record.isMapped("KATEGORI")) {
                berita.setKategori(record.get("KATEGORI"));
            }
            if (record.isMapped("LABEL")) {
                berita.setKategori(record.get("LABEL"));
            }
            if (record.isMapped("KELAS")) {
                berita.setKategori(record.get("KELAS"));
            }
            
            listBerita.add(berita);
        }
        parser.close();
        
        updateModel(listBerita);
    }
    
    /**
     * Memasukkan berita baru ke basis data.
     * @param berita Berita yang akan dimasukkan.
     * @return ID record baru di basis data. -1 jika terjadi kesalahan.
     */
    public int insertRecord(Berita berita) {
        int insertedId = -1;
        try {
            Connection connection = DriverManager.
                    getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO `artikel` () VALUES ()",
                            Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                insertedId =  key.getInt(1);
            }
            
            // succesively insert each attributes
            // HTML
            if (berita.getHtml() != null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `HTML` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getHtml());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // FULL_TEXT
            if (berita.getFullText()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `FULL_TEXT` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getFullText());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // TGL_TERBIT
            if (berita.getTanggalTerbit() != null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `TGL_TERBIT` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setDate(1, new Date(berita.getTanggalTerbit().getTime()));
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // TGL_CRAWL
            if (berita.getTanggalCrawl() != null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `TGL_CRAWL` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setDate(1, new Date(berita.getTanggalCrawl().getTime()));
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // JUDUL
            if (berita.getJudul() != null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `JUDUL` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getJudul());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // URL
            if (berita.getUrl() != null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `URL` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getUrl());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_WHAT
            if (berita.getInfoWhat()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_WHAT` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoWhat());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_WHERE
            if (berita.getInfoWhere()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_WHERE` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoWhere());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_WHY
            if (berita.getInfoWhy()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_WHY` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoWhy());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_WHO
            if (berita.getInfoWho()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_WHO` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoWho());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_WHEN
            if (berita.getInfoWhen()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_WHEN` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoWhen());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            // INFO_HOW
            if (berita.getInfoHow()!= null) {
                statement = connection.prepareStatement(
                        "UPDATE `artikel` SET `INFO_HOW` = ? WHERE `ID_ARTIKEL` = ?");
                statement.setString(1, berita.getInfoHow());
                statement.setInt(2, insertedId);
                statement.executeUpdate();
            }
            
            // Jika label diketahui, tambahkan
            if (berita.getKategori() != null) {
                // get ID_KELAS
                statement = connection.
                        prepareStatement("SELECT * FROM `kategori` WHERE `LABEL` LIKE ?");
                statement.setString(1, berita.getKategori());
                ResultSet kelas = statement.executeQuery();
                if (kelas.next()) {
                    int idKelas = kelas.getInt("ID_KELAS");
                    
                    // tambahkan relasi pada artikel_kategori_verified
                    statement = connection.prepareStatement("INSERT INTO `artikel_kategori_verified` (`ID_ARTIKEL`, `ID_KELAS`) VALUES (?, ?)");
                    statement.setInt(1, insertedId);
                    statement.setInt(2, idKelas);
                    statement.executeUpdate();
                }
            }
            
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Terjadi kesalahan pada saat memasukkan berita: " + e.getMessage());
            e.printStackTrace();
        }
        return insertedId;
    }
    
    /**
     * Mengeset classifier menjadi IBk/kNN.
     * K = 3, dan DistanceFunction = ManhattanDistance
     * @throws Exception 
     */
    private void initializeIBk() throws Exception {
        IBk knn = new IBk();
        knn.setKNN(3);
        LinearNNSearch lnn = new LinearNNSearch(data);
        lnn.setDistanceFunction(new ManhattanDistance(data));
        knn.setNearestNeighbourSearchAlgorithm(lnn);
        
        classifier = knn;
    }
    
    private void initializeRandomizableFilteredClassifier() {
        classifier = new RandomizableFilteredClassifier();
    }
    
    /**
     * Mengeset filter yang akan digunakan menjadi StringToWordVector
     */
    private void initializeStringToWordVector() {
        // StringToWordVector
        StringToWordVector stringToWordVector = new StringToWordVector();
        stringToWordVector.setAttributeIndices("1");
        stringToWordVector.setLowerCaseTokens(true);
        stringToWordVector.setMinTermFreq(STRING_TO_WORD_VECTOR_MIN_TERM_FREQ);
        stringToWordVector.setOutputWordCounts(true);
        stringToWordVector.setStopwords(new File("stopword.txt"));
        WordTokenizer tokenizer = new WordTokenizer();
        tokenizer.setDelimiters(STRING_TO_WORD_VECTOR_DELIMITER);
        stringToWordVector.setTokenizer(tokenizer);
        stringToWordVector.setWordsToKeep(STRING_TO_WORD_VECTOR_WORDS_TO_KEEP);
        
        filter = stringToWordVector;
    }
    
    /**
     * Memuat model dari file eksternal
     * @param filename file *.model yang akan dimuat
     */
    public void loadModel(String filename) {
        model = (FilteredClassifier) Debug.loadFromFile(filename);
    }
    
    /**
     * Menyimpan model saat ini ke file eksternal
     * @param filename file *.model yang akan disimpan
     */
    public void saveModel(String filename) {
        if (model != null) {
            Debug.saveToFile(filename, model);
        }
        else {
            System.out.println("Tidak dapat menyimpan model: model is null");
        }
    }
    
    /**
     * Mengklasifikasikan berita-berita yang ada pada fileInput, dan
     * mengeluarkan hasilnya pada fileOutput
     * @param fileInput Nama file dalam format CSV yang berisi daftar
     * judul dan isi berita
     * @param fileOutput Nama file dalam format CSV yang berisi hasil
     * klasifikasi fileInput berdasarkan model yang ada
     * @throws java.lang.Exception
     */
    public void classify(String fileInput, String fileOutput) throws Exception {
        Reader reader = new FileReader(fileInput);
        CSVParser parser = new CSVParser(reader, 
                CSVFormat.EXCEL.withHeader().withDelimiter(CSV_DELIMITER));
        
        // set header
        Map<String, Integer> headerMap = parser.getHeaderMap();
        List<String> header = new ArrayList<>();
        for (String s : headerMap.keySet()) {
            header.add(s);
        }
        if (!headerMap.containsKey("LABEL")) {
            header.add("LABEL");
            headerMap.put("LABEL", headerMap.size());
        }
        for (String s : headerMap.keySet()) {
            header.set(headerMap.get(s), s);
        }
        
        Writer writer = new FileWriter(fileOutput);
        CSVPrinter printer = new CSVPrinter(writer, 
                CSVFormat.EXCEL.withDelimiter(CSV_DELIMITER));
        printer.printRecord(header);
        
        for (CSVRecord record : parser) {
            // membuat berita dari record
            Berita b = new Berita();
            b.setFullText(record.get("FULL_TEXT"));
            b.setJudul(record.get("JUDUL"));
            
            // mengklasifikasikan berita
            String kategori = classify(b);
            
            // mengoutputkan hasilnya ke printer
            for (String s : headerMap.keySet()) {
                if (record.isMapped(s)) {
                    header.set(headerMap.get(s), record.get(s));
                }
            }
            header.set(headerMap.get("LABEL"), kategori);
            printer.printRecord(header);
        }
        writer.flush();
        writer.close();
        parser.close();
        printer.close();
    }
    
    /**
     * Menentukan kategori berita berdasarkan model yang telah ada
     * @param berita Berita yang akan diklasifikasikan
     * @return Indeks kategori berita
     * @throws java.lang.Exception
     */
    public String classify(Berita berita) throws Exception {
        if (model == null) {
            buildModel();
        }
        
        Instance instance = makeInstance(berita);
        instance.setDataset(data);
        
        double result = model.classifyInstance(instance);
        return data.classAttribute().value((int)result);
    }
    
    /**
     * Membuat instance dari berita
     * @param berita Berita yang akan dibuat instance-nya
     * @return hasil instance dari berita, setelah diberikan filter
     * @throws java.lang.Exception
     */
    private Instance makeInstance(Berita berita) throws Exception {
        Instance instance = new DenseInstance(2);
        
        instance.setValue(data.attribute("TEXT"), berita.getText());
        instance.setMissing(data.attribute("LABEL"));
        
        return instance;
    }

    /**
     * Set delimiter untuk file CSV
     * @param DELIMITER the CSV_DELIMITER to set
     */
    public void setCSVDelimiter(char DELIMITER) {
        this.CSV_DELIMITER = DELIMITER;
    }
}
