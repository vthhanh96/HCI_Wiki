package com.example.honghanh.hci_wiki.homepage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;
import com.example.honghanh.hci_wiki.storage.model.Story;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesHolder> {

    private List<Story> mList;
    private Context mContext;
    private OnItemClickListener mListener;

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public StoriesAdapter(List<Story> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public StoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoriesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_stories, parent, false));
    }

    @Override
    public void onBindViewHolder(StoriesHolder holder, final int position) {
        holder.tvTitle.setText(mList.get(position).getTitle());
        Glide.with(mContext).load(mList.get(position).getImage()).into(holder.imgStory);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class StoriesHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_story)
        ImageView imgStory;
        @Bind(R.id.tv_story_title)
        TextView tvTitle;

        public StoriesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
