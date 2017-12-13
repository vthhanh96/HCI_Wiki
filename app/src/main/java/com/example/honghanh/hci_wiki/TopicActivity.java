package com.example.honghanh.hci_wiki;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_TOPIC;

public class TopicActivity extends DrawerActivity {

    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topic;
    }

    @Override
    protected int getNavId() {
        return NAV_DRAWER_ID_TOPIC;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initData() {
        topBar.setImageViewLeft(CustomViewTopBar.LEFT_MENU);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH);
        topBar.setTitle("Topic");
    }

    private void initListener() {
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                openDrawer();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(TopicActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
