package com.example.honghanh.hci_wiki.newsDetailPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.detailpage.DetailsActivity;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.storage.model.Data;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;

public class NewsDetailActivity extends AppCompatActivity {

    @Bind(R.id.rcv_news_detail)
    RecyclerView rcvNewsDetail;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Bind(R.id.img_back)
    ImageView imgBack;

    @Bind(R.id.img_search)
    ImageView imgSearch;

    private NewsDetailAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initData() {

        mAdapter = new NewsDetailAdapter(this);

        rcvNewsDetail.setAdapter(mAdapter);
        rcvNewsDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvNewsDetail.setHasFixedSize(true);
    }

    private void initListener() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        mAdapter.setOnItemCLickListener(new NewsDetailAdapter.OnItemCLickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(NewsDetailActivity.this, DetailsActivity.class);
                Data data = new Data("Book", "book.jpg", getString(R.string.book_content));
                intent.putExtra(KEY_CONTENT_DATA, data);
                startActivity(intent);
            }
        });
    }
}
