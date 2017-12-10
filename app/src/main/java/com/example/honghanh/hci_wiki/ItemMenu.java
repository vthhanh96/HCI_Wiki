package com.example.honghanh.hci_wiki;

public class ItemMenu {

    private String text;
    private int icon;
    private boolean isSelected;

    public ItemMenu(String text, int icon, boolean isSelected) {
        this.text = text;
        this.icon = icon;
        this.isSelected = isSelected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
