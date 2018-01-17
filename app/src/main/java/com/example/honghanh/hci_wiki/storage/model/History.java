package com.example.honghanh.hci_wiki.storage.model;

import java.io.Serializable;
import java.util.List;

public class History implements Serializable {
    private String date;
    private List<Story> listHistory;

    public History(String date, List<Story> listHistory) {
        this.date = date;
        this.listHistory = listHistory;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Story> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<Story> listHistory) {
        this.listHistory = listHistory;
    }
}
