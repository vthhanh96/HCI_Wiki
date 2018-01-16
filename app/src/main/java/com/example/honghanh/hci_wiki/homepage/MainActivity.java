package com.example.honghanh.hci_wiki.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.DrawerActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.newsDetailPage.NewsDetailActivity;
import com.example.honghanh.hci_wiki.storage.model.Story;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_HOME_PAGE;

public class MainActivity extends DrawerActivity {

    @Bind(R.id.img_menu)
    ImageView imgMenu;
    @Bind(R.id.tv_search)
    TextView tvSearch;

    @Bind(R.id.cv_news)
    CardView cvNews;
    @Bind(R.id.cv_trends)
    CardView cvTrends;
    @Bind(R.id.cv_continue_reading)
    CardView cvContinueReading;

    @Bind(R.id.rcv_news)
    RecyclerView rcvNews;
    @Bind(R.id.rcv_trending)
    RecyclerView rcvTrending;

    private NewsAdapter mNewsAdapter;

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

        if (savedInstanceState == null) {
            ViewTreeObserver observer = cvNews.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    cvNews.getViewTreeObserver().removeOnPreDrawListener(this);
                    runEnterAnimation();
                    return true;
                }
            });
        }

        initData();
        initListener();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initData() {

        mNewsAdapter = new NewsAdapter(this);

        rcvNews.setAdapter(mNewsAdapter);
        rcvNews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcvNews.setNestedScrollingEnabled(false);

        List<Story> listTrending = new ArrayList<>();
        listTrending.add(new Story("Gerge Weah", R.drawable.george_weah));
        listTrending.add(new Story("Peru Pedro Pablo Kuczynski", R.drawable.peru_pedro_pablo_kuczynski));
        listTrending.add(new Story("Tembin", R.drawable.tembin));
        listTrending.add(new Story("Carles Puigdemont", R.drawable.carles_puigdemont));
        listTrending.add(new Story("Technology", R.drawable.technology));

        rcvTrending.setAdapter(new StoriesAdapter(listTrending, this));
        rcvTrending.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvNews.setNestedScrollingEnabled(true);
    }

    private void initListener() {
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer();
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        mNewsAdapter.setOnItemNewsClickListener(new NewsAdapter.OnItemNewsClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void runEnterAnimation() {
        Animation animNewsCard = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        animNewsCard.setDuration(200);
        animNewsCard.setInterpolator(this, android.R.anim.linear_interpolator);
        animNewsCard.setFillAfter(true);
        animNewsCard.setStartOffset(250);

        final Animation animTrendsCard = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        animTrendsCard.setDuration(200);
        animTrendsCard.setInterpolator(this, android.R.anim.linear_interpolator);
        animTrendsCard.setFillAfter(true);
        animTrendsCard.setStartOffset(300);

        animNewsCard.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cvNews.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                cvTrends.startAnimation(animTrendsCard);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animTrendsCard.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                cvTrends.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                cvContinueReading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        cvTrends.startAnimation(animTrendsCard);
        cvNews.startAnimation(animNewsCard);
    }
}
