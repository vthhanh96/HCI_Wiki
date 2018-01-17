package com.example.honghanh.hci_wiki.historypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.honghanh.hci_wiki.DrawerActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.storage.model.History;
import com.example.honghanh.hci_wiki.storage.model.Story;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_HISTORY_PAGE;

public class HistoryActivity extends DrawerActivity {

    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;
    @Bind(R.id.rcv_history)
    RecyclerView rcvHistory;

    private HistoryAdapter mAdapter;
    private List<History> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    protected int getNavId() {
        return NAV_DRAWER_ID_HISTORY_PAGE;
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
        topBar.setTitle("History");

        List<Story> storiesYesterday = new ArrayList<>();
        storiesYesterday.add(new Story("Dessert", R.drawable.dessert));
        storiesYesterday.add(new Story("Dessert", R.drawable.dessert));
        storiesYesterday.add(new Story("Dessert", R.drawable.dessert));
        storiesYesterday.add(new Story("Dessert", R.drawable.dessert));
        storiesYesterday.add(new Story("Dessert", R.drawable.dessert));

        List<Story> storiesToday = new ArrayList<>();
        storiesToday.add(new Story("Technology", R.drawable.technology));
        storiesToday.add(new Story("Technology", R.drawable.technology));
        storiesToday.add(new Story("Technology", R.drawable.technology));
        storiesToday.add(new Story("Technology", R.drawable.technology));

        mList = new ArrayList<>();
        mList.add(new History("Today - Wednesday, 17 January 2018", storiesToday));
        mList.add(new History("Yesterday - Tuesday, 16 January 2017", storiesYesterday));

        mAdapter = new HistoryAdapter(this, mList);
        rcvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvHistory.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemDeleteClick(int headerPosition, int itemPosition) {
                mAdapter.removeItem(headerPosition, itemPosition);
            }

        });
    }

    private void initListener() {
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                openDrawer();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(HistoryActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
