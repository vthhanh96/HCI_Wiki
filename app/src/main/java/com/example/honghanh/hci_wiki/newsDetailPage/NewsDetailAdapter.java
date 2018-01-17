package com.example.honghanh.hci_wiki.newsDetailPage;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.deanwild.flowtextview.FlowTextView;

public class NewsDetailAdapter extends RecyclerView.Adapter<NewsDetailAdapter.NewsDetailHolder> {

    private Context mContext;
    private List<Data> mList;

    public NewsDetailAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
        mList.add(new Data("George Weah", "george_weah", mContext.getString(R.string.george_weah_short_content)));
        mList.add(new Data("George Weah", "george_weah", mContext.getString(R.string.george_weah_short_content)));
        mList.add(new Data("George Weah", "george_weah", mContext.getString(R.string.george_weah_short_content)));
    }


    @Override
    public NewsDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsDetailHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsDetailHolder holder, int position) {
        holder.tvTitle.setText(mList.get(position).getTitle());
        Spanned content = Html.fromHtml(mList.get(position).getContent());
        holder.tvShortContent.setText(content);
        holder.tvShortContent.setTextSize(30);
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Maitree-Regular.ttf");
        holder.tvShortContent.setTypeface(typeface);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class NewsDetailHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.img_news_detail)
        ImageView imgNewsDetail;
        @Bind(R.id.tv_short_content)
        FlowTextView tvShortContent;
        @Bind(R.id.tv_read_more)
        TextView tvReadMore;

        public NewsDetailHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
