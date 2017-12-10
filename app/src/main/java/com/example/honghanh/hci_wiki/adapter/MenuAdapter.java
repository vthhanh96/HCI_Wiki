package com.example.honghanh.hci_wiki.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.ItemMenu;
import com.example.honghanh.hci_wiki.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder> {

    private Context mContext;
    private List<ItemMenu> mList;

    public MenuAdapter(Context context, List<ItemMenu> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuHolder(LayoutInflater.from(mContext).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        ItemMenu itemMenu = mList.get(position);
        holder.imgIcon.setImageDrawable(mContext.getResources().getDrawable(itemMenu.getIcon()));
        holder.tvText.setText(itemMenu.getText());
        holder.rltItemMenu.setBackgroundColor(itemMenu.isSelected() ? mContext.getResources().getColor(R.color.colorPrimaryDark) : mContext.getResources().getColor(R.color.colorPrimary));
        holder.vLine.setVisibility(itemMenu.isSelected() ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MenuHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.img_icon)
        ImageView imgIcon;
        @Bind(R.id.tv_text)
        TextView tvText;
        @Bind(R.id.rlt_item_menu)
        RelativeLayout rltItemMenu;
        @Bind(R.id.v_line)
        View vLine;

        public MenuHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
