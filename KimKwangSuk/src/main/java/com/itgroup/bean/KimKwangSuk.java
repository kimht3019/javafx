package com.itgroup.bean;

// 김광석 프로젝트를 의미하는 자바 Bean 클래스
public class KimKwangSuk {
    private int tracknumber;
    private String title;
    private String genre;
    private String lyrics;
    private String explanation;
    private String image;
    private String oldData;
    private String newData;

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }
//6) getter,setter,toString만들기

    public KimKwangSuk(int tracknumber, String title, String genre, String lyrics, String explanation, String image) {
        this.tracknumber = tracknumber;
        this.title = title;
        this.genre = genre;
        this.lyrics = lyrics;
        this.explanation = explanation;
        this.image = image;
    }

    public KimKwangSuk() {

    }

    public int getTracknumber() {
        return tracknumber;
    }

    public void setTracknumber(int tracknumber) {
        this.tracknumber = tracknumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "KimKwangSuk{" +
                "tracknumber=" + tracknumber +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", explanation='" + explanation + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public void setSSN(int ssn) {
    }

}
