package com.example.honghanh.hci_wiki.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.Constants;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.util.ArrayList;
import java.util.List;

public class RecentSearchAdapter extends BaseAdapter {

    private List<Data> mList;
    private Context mContext;

    public RecentSearchAdapter(Context context) {
        mList = new ArrayList<>();
        mList.add(new Data("Book", "book.jpg", context.getString(R.string.book_content)));
        mList.add(new Data("Flower", "flower.jpg", context.getString(R.string.flower_content)));
        mList.add(new Data("Food", "food.jpg", context.getString(R.string.food_content)));
        mList.add(new Data("Forest", "forest.jpg", context.getString(R.string.forest_content)));
        mList.add(new Data("Animal", "animal.jpg", context.getString(R.string.animal_content)));
        mList.add(new Data("Galaxy", "galaxy.jpg", context.getString(R.string.galaxy_content)));

        mContext = context;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecentSearchHolder viewHolder;
        if(convertView == null) {
            viewHolder = new RecentSearchHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recent_search, parent, false);
            viewHolder.imgRecentSearch = (ImageView) convertView.findViewById(R.id.img_recent_search);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title_recent_search);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (RecentSearchHolder) convertView.getTag();
        }

        Data data = mList.get(position);
        viewHolder.tvTitle.setText(data.getTitle());

        String uriImage = Constants.PATH_ASSETS_FILE + data.getImage();
        Glide.with(mContext).load(Uri.parse(uriImage)).into(viewHolder.imgRecentSearch);

        return convertView;
    }

    class RecentSearchHolder {
        ImageView imgRecentSearch;
        TextView tvTitle;
    }
}
