package com.example.honghanh.hci_wiki.bookmarkpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.honghanh.hci_wiki.DrawerActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_BOOKMARK_PAGE;


public class BookmarkActivity extends DrawerActivity {

    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;
    @Bind(R.id.rcv_bookmark)
    RecyclerView rcvBookmark;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookmark;
    }

    @Override
    protected int getNavId() {
        return NAV_DRAWER_ID_BOOKMARK_PAGE;
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
        topBar.setTitle("Bookmark");

        rcvBookmark.setAdapter(new BookmarkAdapter(this));
        rcvBookmark.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvBookmark.setNestedScrollingEnabled(false);
    }

    private void initListener() {
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                openDrawer();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(BookmarkActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
