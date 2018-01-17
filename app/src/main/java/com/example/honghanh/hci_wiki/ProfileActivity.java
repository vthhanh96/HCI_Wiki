package com.example.honghanh.hci_wiki;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.example.honghanh.hci_wiki.bookmarkpage.BookmarkActivity;
import com.example.honghanh.hci_wiki.historypage.HistoryActivity;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_PROFILE;


public class ProfileActivity extends DrawerActivity {
    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;

    @Bind(R.id.ll_bookmark)
    LinearLayout llBookmark;

    @Bind(R.id.ll_history)
    LinearLayout llHistory;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getNavId() {
        return NAV_DRAWER_ID_PROFILE;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initData() {
        topBar.setImageViewLeft(CustomViewTopBar.LEFT_MENU);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH);
        topBar.setTitle("Profile");
    }

    private void initListener() {
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                openDrawer();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(ProfileActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        llBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, BookmarkActivity.class);
                startActivity(intent);
            }
        });

        llHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
