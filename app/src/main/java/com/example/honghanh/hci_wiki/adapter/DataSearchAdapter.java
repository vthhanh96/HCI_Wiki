package com.example.honghanh.hci_wiki.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DataSearchAdapter extends RecyclerView.Adapter<DataSearchAdapter.DataSearchViewHolder> {

    private Context mContext;
    private List<Data> mList;

    public DataSearchAdapter(Context context, List<Data> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public DataSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataSearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_data_search, parent, false));
    }

    @Override
    public void onBindViewHolder(DataSearchViewHolder holder, final int position) {
        Data data = mList.get(position);
        holder.tvData.setText(data.getTitle());

        try {
            InputStream inputStream = mContext.getAssets().open(data.getImage());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                holder.vImage.setBackground(Drawable.createFromStream(inputStream, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(getItemCount() - 1 == position) {
            holder.vMargin.setVisibility(View.GONE);
        } else {
            holder.vMargin.setVisibility(View.VISIBLE);
        }

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

    class DataSearchViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_data)
        TextView tvData;
        @Bind(R.id.view_img_data)
        View vImage;
        @Bind(R.id.view_margin)
        View vMargin;

        DataSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private IDataSearchListener mListener;

    public void setIDataSearchListener(IDataSearchListener listener) {
        this.mListener = listener;
    }

    public interface IDataSearchListener {
        public void onItemClick(int position);
    }
}
