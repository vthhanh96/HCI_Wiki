package com.example.honghanh.hci_wiki.homepage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private List<News> listNews;
    private Context mContext;
    private OnItemNewsClick mListener;

    interface OnItemNewsClick {
        void onItemClick(int position);
    }

    public void setOnItemNewsClickListener(OnItemNewsClick listener) {
        mListener = listener;
    }

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
        listNews =  new ArrayList<>();
        listNews.add(new News(R.drawable.george_weah, "George Weah (hình) đắc cử Tổng thống Liberia."));
        listNews.add(new News(R.drawable.peru_pedro_pablo_kuczynski, "Tổng thống Peru Pedro Pablo Kuczynski tuyên bố xá tội cho cựu tổng thống lưu vong Alberto Fujimori."));
        listNews.add(new News(R.drawable.tembin, "Bão Tembin tràn qua Philippines làm ít nhất 257 người thiệt mạng và nhiều người khác mất tích."));
        listNews.add(new News(R.drawable.carles_puigdemont, "Đảng đấu tranh đòi ly khai của thủ hiến lưu vong Carles Puigdemont chiếm đa số tại cuộc bầu cử Quốc hội Catalunya."));
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, final int position) {
        holder.tvNews.setText(listNews.get(position).getNews());
        Glide.with(mContext).load(listNews.get(position).getImage()).into(holder.imgNews);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.img_news)
        ImageView imgNews;
        @Bind(R.id.tv_news)
        TextView tvNews;

        public NewsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
