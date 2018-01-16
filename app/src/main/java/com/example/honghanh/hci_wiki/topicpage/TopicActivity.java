package com.example.honghanh.hci_wiki.topicpage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridView;

import com.example.honghanh.hci_wiki.DrawerActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.topicdetailpage.TopicDetailActivity;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.storage.model.Topic;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_TOPIC;
import static com.example.honghanh.hci_wiki.Constants.PACKAGE;

public class TopicActivity extends DrawerActivity {

    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;

    @Bind(R.id.grv_topic)
    GridView grvTopic;

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initData() {
        topBar.setImageViewLeft(CustomViewTopBar.LEFT_MENU);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH);
        topBar.setTitle("Topic");

        TopicAdapter adapter = new TopicAdapter(this);
        grvTopic.setAdapter(adapter);
        adapter.setOnItemTopicClickListener(new TopicAdapter.OnItemTopicClickListener() {
            @Override
            public void onItemClick(Topic topic, View view) {
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                int orientation = getResources().getConfiguration().orientation;
                Intent intent = new Intent(TopicActivity.this, TopicDetailActivity.class);
                intent.putExtra(PACKAGE + ".orientation", orientation)
                        .putExtra(PACKAGE + ".resourceId", topic.getImage())
                        .putExtra(PACKAGE + ".left", location[0])
                        .putExtra(PACKAGE + ".top", location[1])
                        .putExtra(PACKAGE + ".width", view.getWidth())
                        .putExtra(PACKAGE + ".height", view.getHeight())
                        .putExtra(PACKAGE + ".description", topic.getName());
                startActivity(intent);
                overridePendingTransition(0, 0);
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
                Intent intent = new Intent(TopicActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
