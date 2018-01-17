package com.example.honghanh.hci_wiki.historypage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.History;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryAdapter extends SectioningAdapter {
    private Context mContext;
    private List<History> mList = new ArrayList<>();

    public HistoryAdapter(Context mContext, List<History> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    public void removeItem(int headerPosition, int itemPosition) {
//        notifySectionItemRemoved(headerPosition, itemPosition);
//
//        Log.d("HistoryAdapter", itemPosition + "");
        mList.get(headerPosition).getListHistory().remove(itemPosition);
        if(mList.get(headerPosition).getListHistory().size() == 0) {
            mList.remove(headerPosition);
        }
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerUserType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history_header, parent, false);
        return new HistoryHeaderViewHolder(view);
    }


    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history_item, parent, false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderViewHolder viewHolder, final int sectionIndex, int headerUserType) {
        History item = mList.get(sectionIndex);
        HistoryHeaderViewHolder holder = (HistoryHeaderViewHolder) viewHolder;
        holder.tvHistoryHeader.setText(item.getDate());
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, final int sectionIndex, final int itemIndex, int itemUserType) {
        History item = mList.get(sectionIndex);
        HistoryItemViewHolder holder = (HistoryItemViewHolder) viewHolder;
        holder.tvTitle.setText(item.getListHistory().get(itemIndex).getTitle());
        Glide.with(mContext).load(item.getListHistory().get(itemIndex).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onItemClick(itemIndex);
                }
            }
        });

        holder.imgDelHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null) {
                    mCallback.onItemDeleteClick(sectionIndex, itemIndex);
                }
            }
        });
    }

    @Override
    public int getNumberOfSections() {
        return mList.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mList.get(sectionIndex).getListHistory().size();
    }

    @Override
    public int getSectionHeaderUserType(int sectionIndex) {
        return 1;
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    class HistoryHeaderViewHolder extends HeaderViewHolder {

        @Bind(R.id.tv_history_header)
        TextView tvHistoryHeader;

        HistoryHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HistoryItemViewHolder extends ItemViewHolder {
        @Bind(R.id.img_history)
        ImageView imgHistory;
        @Bind(R.id.tv_history_title)
        TextView tvTitle;
        @Bind(R.id.img_del_history)
        ImageView imgDelHistory;

        HistoryItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClickListener mCallback;

    public void setOnItemClickListener(OnItemClickListener callback) {
        this.mCallback = callback;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemDeleteClick(int headerPosition, int itemPosition);
    }
}
