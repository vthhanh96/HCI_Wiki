package com.example.honghanh.hci_wiki.storage.model;

import java.io.Serializable;

/**
 * Created by Hong Hanh on 12/15/2017.
 */

public class Topic implements Serializable {
    private String name;
    private int image;

    public Topic(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
