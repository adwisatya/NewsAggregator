/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import java.io.IOException;
import myta.engine.LearningEngine;
import myta.model.Berita;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author adwisatya
 * Bagian ini tolong diganti jadi engine yang Zaky buat. Buat static aja biar enggak perlu new.
 */
public class Engine {
	public static String getLabel(String judul, String konten) throws Exception{
		Berita berita = new Berita();
		berita.setFullText(konten);
		berita.setJudul(judul);
		return LearningEngine.getInstance().classify(berita);
	}
	public static String checkMulti(String namafile) throws Exception{
		// TODO: Ganti path yang bener
		String output = "D:/output.csv";
		LearningEngine.getInstance().classify(namafile, output);
		return output;
	}
	public static String getKonten(String link) throws Exception{
		String text=null;
		if(link.contains("tempo.co/")){
			//getKontenTempo(String link);
		}else if(link.contains("metrotvnews.com/")){
			text = getKontenMetro(link);
		}else if(link.contains("republika.co.id/")){
			//getKontenRepublika(String link);
		}else if (link.contains("okezone.com/")){
			text = getKontenOkezone(link);
		}else{
			text =  getTitleAnything(link);
		}
		return text;
	}
	public static String getKontenTempo(String link) throws Exception{
		String konten = null;
		String judul = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.text();
			judul =  doc.title();
		}catch(IOException e){
			e.printStackTrace();
		}
		Berita berita = new Berita();
		berita.setFullText(konten);
		berita.setJudul(judul);
		return LearningEngine.getInstance().classify(berita);
	}
	
	public static String getKontenMetro(String link) throws Exception{
		String konten = null;
		String judul = null;

		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			//konten = doc.title();
			Document document = Jsoup.parse(doc.html());
			//Elements elements = document.select("div.info.demo");
			//Element element =  document.select("div.info").first();
			judul = document.title();
			konten = document.text();
		}catch(IOException e){
			e.printStackTrace();
		}
		Berita berita = new Berita();
		berita.setFullText(konten);
		berita.setJudul(judul);
		return LearningEngine.getInstance().classify(berita);
	}
	public static String getKontenRepublika(String link) throws Exception{
		String konten = null;
		String judul = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.title();
			judul = doc.text();
		}catch(IOException e){
			e.printStackTrace();
		}
		Berita berita = new Berita();
		berita.setFullText(konten);
		berita.setJudul(judul);
		return LearningEngine.getInstance().classify(berita);
	}
	public static String getKontenOkezone(String link) throws Exception{
		String konten = null;
		String judul = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.title();
			judul = doc.text();
		}catch(IOException e){
			e.printStackTrace();
		}
		Berita berita = new Berita();
		berita.setFullText(konten);
		berita.setJudul(judul);
		return LearningEngine.getInstance().classify(berita);	
	}
	public static String getTitleAnything(String link){
		// selain sumber berita yang ada di database cuma bakal diambil title nya.
		return "aDssdasdsa";
	}
	public static String getJudul(String link){
		String judul = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			judul = doc.text();
		}catch(IOException e){
			e.printStackTrace();
		}	
		return judul;
	}
	public static String getKontenX(String link){
		String konten = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.text();
		}catch(IOException e){
			e.printStackTrace();
		}	
		return konten;
	}
}
