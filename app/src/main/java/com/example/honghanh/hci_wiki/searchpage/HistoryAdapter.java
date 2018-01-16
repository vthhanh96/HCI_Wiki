package com.example.honghanh.hci_wiki.searchpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{

    private List<String> mList;
    private Context mContext;

    public HistoryAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_history_search, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, final int position) {
        holder.tvSearchHistory.setText(mList.get(position));

        holder.vDeleteSearchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    mListener.onDeleteItemHistory(position);
                }
            }
        });

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

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_search_history)
        TextView tvSearchHistory;

        @Bind(R.id.view_delete_search_history)
        View vDeleteSearchHistory;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private IHistoryLisener mListener;
    public void setIHistoryListener(IHistoryLisener listener) {
        mListener = listener;
    }

    public interface IHistoryLisener {
        void onDeleteItemHistory(int position);
        void onItemClick(int position);
    }
}
