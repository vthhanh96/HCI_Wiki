package com.example.honghanh.hci_wiki;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.honghanh.hci_wiki.adapter.RecentStoriesAdapter;
import com.example.honghanh.hci_wiki.adapter.TopStoriesViewPagerAdapter;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_HOME_PAGE;

public class MainActivity extends DrawerActivity {

    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;

    @Bind(R.id.vp_top_stories)
    ViewPager vpTopStories;

    @Bind(R.id.indicator)
    CircleIndicator indicator;

    @Bind(R.id.rcv_recent)
    RecyclerView rcvRecents;

    @Bind(R.id.scv_container)
    NestedScrollView scvContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getNavId() {
        return NAV_DRAWER_ID_HOME_PAGE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                        .setDefaultFontPath("fonts/century_school_book-regular.otf")
//                        .setFontAttrId(R.attr.fontPath)
//                        .build());
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

        vpTopStories.setAdapter(new TopStoriesViewPagerAdapter(getSupportFragmentManager()));
        indicator.setViewPager(vpTopStories);

        rcvRecents.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvRecents.setHasFixedSize(true);
        rcvRecents.setAdapter(new RecentStoriesAdapter(this));
    }

    private void initListener() {
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                openDrawer();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        scvContainer.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d("scrollY", scrollY + "");
                topBar.setTransparentBackground(scrollY < 390);
            }
        });
    }
}
