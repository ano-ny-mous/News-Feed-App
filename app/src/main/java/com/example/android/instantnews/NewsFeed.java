package com.example.android.instantnews;

public class NewsFeed {
    private String titltOfArtical;
    private String sectionName;
    private String date;
    private String url;
    private String authorName;
    static int dataValue = 0;

    public NewsFeed() {

    }

    public NewsFeed(String titltOfArtical, String sectionName, String date, String url , String authorName) {
        this.titltOfArtical = titltOfArtical;
        this.sectionName = sectionName;
        this.date = date;
        this.url = url;
        this.authorName =authorName;
        this.dataValue = 1;
    }

    public String getTitltOfArtical() {
        return titltOfArtical;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getDataValue() {
        return dataValue;
    }
}
