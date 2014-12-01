/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myta.engine;

import myta.model.Berita;

/**
 *
 * @author Toshiba
 */
public class Test {
    public static void main(String[] args) throws Exception {
        LearningEngine e = new LearningEngine();
        e.buildModel();
        e.saveModel("imba.model");
//        e.loadModel("imba.model");
        
        Berita b = new Berita();
        b.setFullText("REPUBLIKA.CO.ID, JAKARTA -- Pelatih timnas U-23, Rahmad Darmawan, menyatakan segera merilis nama-nama pemain untuk mengikuti pemusatan latihan jelang Sea Games 2013 Myanmar. Rencananya, seleksi timnas akan berlangsung selama tiga bulan.\n" +
                "Rahmad berencana memanggil 60 pemain untuk mengikuti seleksi. Daftar nama pemain akan ia umumkan pada akhir April.\n" +
                "\"Rencananya, akhir bulan ini kami umumkan 60 nama pemain,\" kata RD ketika dihubungi wartawan, Kamis (18/4).\n" +
                "Meski begitu, RD belum mau memberi gambaran skuat U-23 yang akan bertanding di Sea Games tahun ini. Dia menegaskan skuat Garuda Muda akan diperkuat para pemain terbaik Indonesia Super League (ISL) dan Indonesia Premier League (IPL).\n" +
                "Rahmad masih melakukan komunikasi dengan Aji Santoso yang menjadi asistennya. Nantinya, 60 pemain tersebut akan dikerucutkan menjadi 30 pemain sebelum akhirnya mendapatkan 23 pemain inti untuk bertanding di Sea Games.\n" +
                "\"Bulan Agustus kami sudah harus menemukan skuat final,` ujarnya.");
        b.setJudul("RD Gelar Seleksi Timnas U-23 Tiga Bulan");
        b.setKategori("Olahraga");
//        e.insertRecord(b);
//        System.out.println("Kategori: " + e.classify(b));
        
        b.setFullText("Jakarta (ANTARA News) - Menteri BUMN Dahlan Iskan, Kamis (25/4) dijadwalkan bicara mengenai permasalahan alih daya (outsourcing) tenaga kerja, khususnya di lingkup kementerian yang dipimpinnya melalui sebuah seminar di Jakarta.\n" +
            "Ketua panitia seminar bertema \"Jaminan Kepastian Kerja dan Upaya Pemenuhan Hak Pekerja di BUMN\" itu Abdul Gofur di Jakarta, Kamis melam menjelaskan, kegiatan itu digagas Serikat Pekerja Antara (SPA) bersama para pihak yang tergabung dalam Gerakan Bersama Buruh dan Pekerja di BUMN (Geber BUMN) untuk berkontribusi dalam upaya mencari solusi atas masalah yang ada.\n" +
            "Didampingi Sekretaris Panitia Eri Wibowo, ia menjelaskan tema itu diangkat karena sejak Permenakertrans tentang pengaturan pelaksanaan pekerja alih daya ditandatangani Menakertrans Muhaimin Iskandar pada 14 November 2012, dan kemudian disahkan Kementerian Hukum dan HAM pada 19 November 2012, masalah ketenagakerjaan di banyak perusahaan pengguna tenaga pekerja alih daya tidak serta merta berhenti.\n" +
            "Bahkan, kata dia, masalah ketenagakerjaan yang terkait dengan para pekerja alih daya itu masih melilit sejumlah badan usaha milik negara (BUMN).\n" +
            "Di antara persoalan ketenagakerjaan di lingkungan BUMN yang mendapat perhatian media arus utama dan media sosial di Tanah Air adalah kasus pekerja alih daya di lingkungan PT PLN serta PT Telkom Tbk.\n" +
            "Dalam kasus pekerja alih daya PLN Kota Padang, Sumatera Barat, yang tergabung dalam Serikat Pekerja Listrik Padang, Pariaman, Painan, Mentawai, dan Kerinci (SPL P3 Mekar), dan kasus 378 pekerja anggota Serikat Pekerja PT Graha Sarana Duta (Sejagad) di Jakarta misalnya, terdapat kesamaan tuntutan keduanya.\n" +
            "Kendati berada di dua kota yang berbeda, para pekerja alih daya ini sama-sama mendambakan adanya jaminan kepastian kerja dan pemenuhan hak-hak normatif.\n" +
            "Dalam kasus pertama, diketahui dari laporan media bahwa para pekerja \"outsourcing\" PLN Kota Padang itu pernah memprotes rencana pengurangan gaji bulanan akibat pergantian perusahaan alih daya tempat mereka dipekerjakan yang selama ini memasok tenaga kerja bagi PLN Sumbar.\n" +
            "Sedangkan pada kasus kedua, ratusan pekerja alih daya anggota Sejagad bahkan menuntut status karyawan tetap PT Telkom sesuai dengan Pasal 59 UU Ketenagakerjaan No.13 Tahun 2003.\n" +
            "\"Berbagai upaya dilakukan para pihak untuk menyelesaikan masalah ketenagakerjaan termasuk melalui dialog dan perundingan yang menjadi basis hubungan industrial yang baik dan layak dirawat oleh manajemen dan kaum pekerja melalui serikat pekerja,\" katanya.\n" +
            "Sementara itu, Ketua SPA Rahmad Nasution menambahkan realitas itu mendorong SPA bersama para pihak yang tergabung dalam Geber BUMN untuk berkontribusi dalam upaya mencari solusi atas masalah yang ada.\n" +
            "Ia mengatakan, dengan keinginan kuat untuk ikut merawat semangat dialogis dalam mencari solusi atas masalah yang dihadapi para pekerja di BUMN, korporat yang merupakan salah satu penopang pembangunan ekonomi nasional, dilakukan prakarsa seminar sehari itu.\n" +
            "Melalui seminar itu, para pihak terkait seperti pejabat Kementerian BUMN, Kementerian Tenaga Kerja dan Transmigrasi, anggota DPR-RI, pelaku usaha di lingkungan BUMN, pengelola perusahaan penyedia jasa pekerja alih daya, dan aktivis serikat pekerja di BUMN, dapat duduk bersama dan berdialog untuk mendudukkan persoalan dan mencari solusi.\n" +
            "Sedangkan maksud dan tujuan seminar, yakni mempertemukan para pihak untuk berdialog serta berbagi pengetahuan dan pengalaman tentang signifikansi masalah dan memperkuat bangunan kepercayaan kolektif di antara para pihak terkait.\n" +
            "Kemudian, membantu meningkatkan pemahaman kalangan pemerintah dan nonpemerintah tentang signifikansi jaminan kepastian kerja dan pemenuhan hak normatif pekerja di BUMN untuk mewujudkan korporasi milik negara yang berdaya saing global.\n" +
            "Selain itu, juga untuk memunculkan strategi peningkatan pengawasan pemerintah dan penetapan standar regulasi di tingkat pusat dan daerah yang mendorong terwujudnya skema hubungan kerja yang mampu melindungi hak pekerja dan meningkatkan produktivitas mereka.\n" +
            "Narasumber lain yang dihadirkan pada diskusi yang akan dipandu moderator Ais dan Sabda Pranawa Jati itu, selain Menteri BUMN juga Dirjen PHI Kemenakertrans mewakili Menakertrans Muhaimin Iskandar, Ketua Komisi IX DPR-RI Ribka Tjiptaning, Direktur LBH Jakarta Mayong dan kalangan akademisi perguruan tinggi.");
        b.setJudul("Dahlan Iskan akan bicara \"outsourcing\" tenaga kerja");
//        System.out.println("Kategori: " + e.classify(b));
        
        e.classify("artikel.csv", "output.csv");
        // e.updateModel("artikel.csv");
        // e.saveModel("humbala.model");
    }
}
