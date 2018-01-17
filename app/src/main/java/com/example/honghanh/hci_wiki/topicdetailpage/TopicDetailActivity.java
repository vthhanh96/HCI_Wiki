package com.example.honghanh.hci_wiki.topicdetailpage;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.bookmarkpage.BookmarkActivity;
import com.example.honghanh.hci_wiki.detailpage.DetailsActivity;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.storage.model.Data;
import com.example.honghanh.hci_wiki.widgets.CustomViewTopBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.GONE;
import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;
import static com.example.honghanh.hci_wiki.Constants.PACKAGE;

public class TopicDetailActivity extends AppCompatActivity {
    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();

    @Bind(R.id.img_anim)
    ImageView imgAnim;

    @Bind(R.id.img_topic_detail)
    ImageView imgTopic;
    @Bind(R.id.tv_topic)
    TextView tvTopic;
    @Bind(R.id.top_bar)
    CustomViewTopBar topBar;
    @Bind(R.id.scv_container)
    NestedScrollView scvContainer;
    @Bind(R.id.rcv_topic)
    RecyclerView rcvTopic;
    @Bind(R.id.rlt_parent)
    RelativeLayout rltParent;

    private int imageTop;
    private int imageLeft;
    private int orientation;
    private int imageId;
    private String desc;

    private int leftDelta;
    private int topDelta;
    private ColorDrawable bgColor;

    private RecentStoriesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imageTop = bundle.getInt(PACKAGE + ".top");
            imageLeft = bundle.getInt(PACKAGE + ".left");
            orientation = bundle.getInt(PACKAGE + ".orientation");
            imageId = bundle.getInt(PACKAGE + ".resourceId");
            desc = bundle.getString(PACKAGE + ".description");
        }

        Glide.with(this).load(imageId).into(imgAnim);
        bgColor = new ColorDrawable(getResources().getColor(R.color.default_color));
        rltParent.setBackground(bgColor);

        if (savedInstanceState == null) {
            ViewTreeObserver observer = imgAnim.getViewTreeObserver();
            observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    imgAnim.getViewTreeObserver().removeOnPreDrawListener(this);

                    int[] location = new int[2];
                    imgAnim.getLocationOnScreen(location);

                    leftDelta = imageLeft - location[0];
                    topDelta = imageTop - location[1];

                    runEnterAnimation();
                    return true;
                }
            });
        }

        topBar.setImageViewLeft(CustomViewTopBar.LEFT_BACK);
        topBar.setImageViewRight(CustomViewTopBar.DRAWABLE_SEARCH);
        topBar.setTitle(desc);
        topBar.setOnLeftRightClickListener(new CustomViewTopBar.OnLeftRightClickListener() {
            @Override
            public void onLeftClick() {
                onBackPressed();
            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(TopicDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        tvTopic.setText(desc);

        mAdapter = new RecentStoriesAdapter(this);

        rcvTopic.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvTopic.setHasFixedSize(true);
        rcvTopic.setAdapter(mAdapter);
        rcvTopic.setNestedScrollingEnabled(false);

        mAdapter.setOnItemClickListener(new RecentStoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TopicDetailActivity.this, DetailsActivity.class);
                Data data = new Data("Book", "book.jpg", getString(R.string.book_content));
                intent.putExtra(KEY_CONTENT_DATA, data);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void runEnterAnimation() {
        final long duration = 500;

        imgAnim.setPivotX(0);
        imgAnim.setPivotY(0);
        imgAnim.setTranslationX(leftDelta);
        imgAnim.setTranslationY(topDelta);

        imgAnim.animate().setDuration(duration)
                .translationX(0)
                .translationY(0)
                .setInterpolator(sDecelerator)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(TopicDetailActivity.this).load(imageId).into(imgTopic);
                        imgAnim.setVisibility(GONE);
                        setVisibleAllView();
                    }
                });

        ObjectAnimator bgAnim = ObjectAnimator.ofInt(bgColor, "alpha", 0, 255);
        bgAnim.setDuration(duration);
        bgAnim.start();
    }

    private void runExitAnimation(final Runnable endAction) {
        int[] location = new int[2];
        imgTopic.getLocationOnScreen(location);
        if(location[1] > 0) {
            imgAnim.setTop(location[1]);
            imgAnim.setLeft(location[0]);
            imgAnim.setVisibility(View.VISIBLE);
        }

        final long duration = 500;
        final boolean fadeOut;
        if(getResources().getConfiguration().orientation != orientation) {
            imgAnim.setPivotX(imgAnim.getWidth() / 2);
            imgAnim.setPivotY(imgAnim.getHeight() / 2);
            leftDelta = 0;
            topDelta = 0;
            fadeOut = true;
        } else {
            fadeOut = false;
        }

        setGoneAllView();
        imgAnim.animate().setDuration(duration)
                .translationX(leftDelta)
                .translationY(topDelta)
                .withEndAction(endAction);
        if(fadeOut) {
            imgAnim.animate().alpha(0);
        }

        ObjectAnimator bgAnim = ObjectAnimator.ofInt(bgColor, "alpha", 0);
        bgAnim.setDuration(duration);
        bgAnim.start();
    }

    private void setVisibleAllView() {
        scvContainer.setVisibility(View.VISIBLE);
    }

    private void setGoneAllView() {
        scvContainer.setVisibility(GONE);
    }

    @Override
    public void onBackPressed() {
        runExitAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
