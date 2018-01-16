package com.example.honghanh.hci_wiki.storage.model;

import java.io.Serializable;

public class Story implements Serializable {
    private String title;
    private int image;

    public Story(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
