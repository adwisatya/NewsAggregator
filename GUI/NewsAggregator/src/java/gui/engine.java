/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
/**
 *
 * @author adwisatya
 * Bagian ini tolong diganti jadi engine yang Zaky buat. Buat static aja biar enggak perlu new.
 */
public class engine {
	public static String getLabel(String input){
		return "Pendidikan";
	}
	public static String checkMulti(String namafile){
		return "xxx";
	}
	public static String getKonten(String link){
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
	public static String getKontenTempo(String link){
		String konten = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.title();
		}catch(IOException e){
			e.printStackTrace();
		}
		return konten;
	}
	
	public static String getKontenMetro(String link){
		String konten = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			//konten = doc.title();
			Document document = Jsoup.parse(doc.html());
			//Elements elements = document.select("div.info.demo");
			//Element element =  document.select("div.info").first();
			konten = document.text();
		}catch(IOException e){
			e.printStackTrace();
		}
		return konten;
	}
	public static String getKontenRepublika(String link){
		String konten = null;
		Document doc;
		try{
			doc = Jsoup.connect(link).get();
			konten = doc.title();
		}catch(IOException e){
			e.printStackTrace();
		}
		return konten;
	}
	public static String getKontenOkezone(String link){
		String konten = null;
		Document doc;
		try{
			//doc = Jsoup.connect(link).get();
			//Elements elements =  doc.select(".col-md-9");
			//konten = doc.text();
			String html = Jsoup.connect(link).get().html();
			//String html = "<div class=\"col-md-9 p-nol\">fdoo</div>";
			Document document = Jsoup.parse(html);
			Elements elements = document.select("div.col-md-9.p-nol");
			konten =  elements.text();
		}catch(Exception e){
			e.printStackTrace();
		}
		return konten;
	}
	public static String getTitleAnything(String link){
		// selain sumber berita yang ada di database cuma bakal diambil title nya.
		return "aDssdasdsa";
	}
}
