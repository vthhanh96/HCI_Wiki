package com.example.honghanh.hci_wiki.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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

public class RecentStoriesAdapter extends RecyclerView.Adapter<RecentStoriesAdapter.RecentContentLeft> {
    private Context mContext;
    private List<Data> mList;

    public RecentStoriesAdapter(Context context) {
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
    public RecentContentLeft onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecentContentLeft(LayoutInflater.from(mContext).inflate(R.layout.item_recent_story_content_left, parent, false));
    }

    @Override
    public void onBindViewHolder(RecentContentLeft holder, int position) {
        holder.tvTitle.setText(mList.get(position).getTitle());
        holder.tvBriefContent.setText(mList.get(position).getContent());
        String uriImage = Constants.PATH_ASSETS_FILE + mList.get(position).getImage();
        Glide.with(mContext).load(Uri.parse(uriImage)).into(holder.imgRecent);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class RecentContentLeft extends RecyclerView.ViewHolder {
        ImageView imgRecent;
        TextView tvTitle;
        TextView tvBriefContent;
        TextView tvContinueReading;

        RecentContentLeft(View view) {
            super(view);
            imgRecent = view.findViewById(R.id.img_recent);
            tvTitle = view.findViewById(R.id.tv_title);
            tvBriefContent = view.findViewById(R.id.tv_brief_content);
            tvContinueReading = view.findViewById(R.id.tv_continue_reading);
        }
    }


}
