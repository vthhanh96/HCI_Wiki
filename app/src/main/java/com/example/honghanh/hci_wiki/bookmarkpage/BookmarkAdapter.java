package com.example.honghanh.hci_wiki.bookmarkpage;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.Constants;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkHolder>{

    private List<Data> mList;
    private Context mContext;

    public BookmarkAdapter(Context context) {
        mList = new ArrayList<>();
        mList.add(new Data("Book", "book.jpg", context.getString(R.string.book_content)));
        mList.add(new Data("Flower", "flower.jpg", context.getString(R.string.flower_content)));
        mList.add(new Data("Food", "food.jpg", context.getString(R.string.food_content)));
        mList.add(new Data("Forest", "forest.jpg", context.getString(R.string.forest_content)));
        mList.add(new Data("Animal", "animal.jpg", context.getString(R.string.animal_content)));
        mList.add(new Data("Galaxy", "galaxy.jpg", context.getString(R.string.galaxy_content)));
        mList.add(new Data("Book", "book.jpg", context.getString(R.string.book_content)));
        mList.add(new Data("Flower", "flower.jpg", context.getString(R.string.flower_content)));
        mList.add(new Data("Food", "food.jpg", context.getString(R.string.food_content)));
        mList.add(new Data("Forest", "forest.jpg", context.getString(R.string.forest_content)));
        mList.add(new Data("Animal", "animal.jpg", context.getString(R.string.animal_content)));
        mList.add(new Data("Galaxy", "galaxy.jpg", context.getString(R.string.galaxy_content)));

        mContext = context;
    }


    @Override
    public BookmarkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookmarkHolder(LayoutInflater.from(mContext).inflate(R.layout.item_bookmark, parent, false));
    }

    @Override
    public void onBindViewHolder(BookmarkHolder holder, int position) {
        Data data = mList.get(position);

        holder.tvTitle.setText(data.getTitle());
        holder.tvBriefContent.setText(data.getContent());

        String uriImage = Constants.PATH_ASSETS_FILE + data.getImage();
        Glide.with(mContext).load(Uri.parse(uriImage)).into(holder.imgBookmark);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class BookmarkHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.img_bookmark)
        ImageView imgBookmark;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_brief_content)
        TextView tvBriefContent;

        public BookmarkHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
