/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myta.model;

import java.util.Date;

/**
 *
 * @author Toshiba
 */
public class Berita {
    private Integer id;
    private String html;
    private String fullText;
    private Date tanggalTerbit;
    private Date tanggalCrawl;
    private String judul;
    private String url;
    private String infoWhat;
    private String infoWhere;
    private String infoWhy;
    private String infoWho;
    private String infoWhen;
    private String infoHow;
    private String kategori;
    
    public Berita() {
        id = null;
        html = null;
        fullText = null;
        tanggalTerbit = null;
        tanggalCrawl = null;
        judul = null;
        url = null;
        infoWhat = null;
        infoWhere = null;
        infoWhy = null;
        infoWho = null;
        infoWhen = null;
        infoHow = null;
        kategori = null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * @return the fullText
     */
    public String getFullText() {
        return fullText;
    }

    /**
     * @param fullText the fullText to set
     */
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    /**
     * @return the tanggalTerbit
     */
    public Date getTanggalTerbit() {
        return tanggalTerbit;
    }

    /**
     * @param tanggalTerbit the tanggalTerbit to set
     */
    public void setTanggalTerbit(Date tanggalTerbit) {
        this.tanggalTerbit = tanggalTerbit;
    }

    /**
     * @return the tanggalCrawl
     */
    public Date getTanggalCrawl() {
        return tanggalCrawl;
    }

    /**
     * @param tanggalCrawl the tanggalCrawl to set
     */
    public void setTanggalCrawl(Date tanggalCrawl) {
        this.tanggalCrawl = tanggalCrawl;
    }

    /**
     * @return the judul
     */
    public String getJudul() {
        return judul;
    }

    /**
     * @param judul the judul to set
     */
    public void setJudul(String judul) {
        this.judul = judul;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the infoWhat
     */
    public String getInfoWhat() {
        return infoWhat;
    }

    /**
     * @param infoWhat the infoWhat to set
     */
    public void setInfoWhat(String infoWhat) {
        this.infoWhat = infoWhat;
    }

    /**
     * @return the infoWhere
     */
    public String getInfoWhere() {
        return infoWhere;
    }

    /**
     * @param infoWhere the infoWhere to set
     */
    public void setInfoWhere(String infoWhere) {
        this.infoWhere = infoWhere;
    }

    /**
     * @return the infoWhy
     */
    public String getInfoWhy() {
        return infoWhy;
    }

    /**
     * @param infoWhy the infoWhy to set
     */
    public void setInfoWhy(String infoWhy) {
        this.infoWhy = infoWhy;
    }

    /**
     * @return the infoWho
     */
    public String getInfoWho() {
        return infoWho;
    }

    /**
     * @param infoWho the infoWho to set
     */
    public void setInfoWho(String infoWho) {
        this.infoWho = infoWho;
    }

    /**
     * @return the infoWhen
     */
    public String getInfoWhen() {
        return infoWhen;
    }

    /**
     * @param infoWhen the infoWhen to set
     */
    public void setInfoWhen(String infoWhen) {
        this.infoWhen = infoWhen;
    }

    /**
     * @return the infoHow
     */
    public String getInfoHow() {
        return infoHow;
    }

    /**
     * @param infoHow the infoHow to set
     */
    public void setInfoHow(String infoHow) {
        this.infoHow = infoHow;
    }

    /**
     * @return the kategori
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    /**
     * Mendapatkan text yang akan digunakan sebagai input
     * classifier. Heuristik: Judul + Judul + FullText
     * @return 
     */
    public String getText() {
        return (judul == null ? "" : judul) + " " +
                (judul == null ? "" : judul) + " " + 
                (fullText == null ? "" : fullText);
    }
    
    
}
