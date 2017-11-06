package com.example.honghanh.hci_wiki.widgets;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface RecentsAdapter {
    String getTitle(int position);
    View getView(int position);
    Drawable getIcon(int position);
    int getHeaderColor(int position);

    int getCount();
}
