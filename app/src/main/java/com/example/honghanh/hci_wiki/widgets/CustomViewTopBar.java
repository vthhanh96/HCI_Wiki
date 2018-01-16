package com.example.honghanh.hci_wiki.widgets;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomViewTopBar extends RelativeLayout implements View.OnClickListener{

    public static final int LEFT_MENU = 0;
    public static final int LEFT_BACK = 1;

    public static final int DRAWABLE_SEARCH = R.drawable.ic_search_menu;

    @Bind(R.id.rlt_top_bar)
    RelativeLayout rltTopBar;

    @Bind(R.id.imgLeft)
    ImageView imgLeft;

    @Bind(R.id.imgRight)
    ImageView imgRight;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    private OnLeftRightClickListener leftRightClickListener;

    public void setOnLeftRightClickListener(OnLeftRightClickListener listener) {
        this.leftRightClickListener = listener;
    }

    public interface OnLeftRightClickListener {
        void onLeftClick();
        void onRightClick();
    }

    public CustomViewTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_custom_topbar,
                this,
                false
        );
        ButterKnife.bind(this, view);

        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
        this.addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLeft:
                leftRightClickListener.onLeftClick();
                break;
            case R.id.imgRight:
                leftRightClickListener.onRightClick();
                break;
        }
    }

    public void setImageViewLeft(int type) {
        if (type == LEFT_MENU) {
            imgLeft.setImageResource(R.drawable.ic_menu);
        } else {
            imgLeft.setImageResource(R.drawable.ic_back);
        }
        imgLeft.setVisibility(VISIBLE);
    }

    public void setImageViewRight(int one) {
        imgRight.setVisibility(VISIBLE);
        imgRight.setImageResource(one);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
        tvTitle.setVisibility(VISIBLE);
    }

    public void setTransparentBackground(boolean isTransparent) {
        if(isTransparent) {
            rltTopBar.setBackgroundColor(Color.TRANSPARENT);
        } else {
            rltTopBar.setBackgroundColor(this.getResources().getColor(R.color.white));
        }
    }
}
