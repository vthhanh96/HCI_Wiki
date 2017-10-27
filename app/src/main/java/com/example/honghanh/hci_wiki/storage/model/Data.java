package com.example.honghanh.hci_wiki.storage.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Data implements Serializable{

    private String title;
    private String image;
    private String content;

    public Data(String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public Data(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public Data(String title) {
        this.title = title;
    }

    public Data(JSONObject object) throws JSONException{
        if(object.has("title")) {
            this.title = object.getString("title");
        }
        if(object.has("image")) {
            this.title = object.getString("image");
        }
        if(object.has("content")) {
            this.title = object.getString("content");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
