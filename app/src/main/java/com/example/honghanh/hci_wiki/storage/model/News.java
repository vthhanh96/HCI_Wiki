package com.example.honghanh.hci_wiki.storage.model;

import java.io.Serializable;

public class News implements Serializable{
    private int image;
    private String news;

    public News(int image, String news) {
        this.image = image;
        this.news = news;
    }

    public News() {

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
