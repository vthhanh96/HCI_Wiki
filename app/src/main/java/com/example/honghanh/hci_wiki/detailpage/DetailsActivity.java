package com.example.honghanh.hci_wiki.detailpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.searchpage.SearchActivity;
import com.example.honghanh.hci_wiki.storage.model.Data;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;

public class DetailsActivity extends AppCompatActivity implements OnMenuItemClickListener{

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.img_data)
    ImageView imgData;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.fab_bookmark)
    FloatingActionButton fabBookmark;

    private Data data;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setUpToolbar();

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                } else if (toolbar.getBackground() != null) {
                    toolbar.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });

        initData();
        initMenuMore();
        initListener();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if(bar == null) {
            return;
        }

        bar.setHomeAsUpIndicator(R.drawable.ic_back);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
    }

    private void initData() {
        if (getIntent().getSerializableExtra(KEY_CONTENT_DATA) != null) {
            data = (Data) getIntent().getSerializableExtra(KEY_CONTENT_DATA);
        }

        if (data != null) {
            collapsingToolbar.setTitle(" ");
            tvContent.setText(data.getContent());
            try {
                InputStream inputStream = getAssets().open(data.getImage());
                imgData.setImageDrawable(Drawable.createFromStream(inputStream, null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initMenuMore() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.dimen_56dp));
        menuParams.setMenuObjects(getMenuObject());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
    }

    private List<MenuObject> getMenuObject() {
        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.ic_delete);
        close.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject fontSize = new MenuObject("Font size");
        fontSize.setResource(R.drawable.ic_font_size);
        fontSize.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject bookmark = new MenuObject("Bookmark");
        bookmark.setResource(R.drawable.ic_bookmark);
        bookmark.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject share = new MenuObject("Share");
        share.setResource(R.drawable.ic_share);
        share.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject newTab = new MenuObject("New tab");
        newTab.setResource(R.drawable.ic_new_tab);
        newTab.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject addHome = new MenuObject("Add to home");
        addHome.setResource(R.drawable.ic_add_to_home);
        addHome.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        MenuObject history = new MenuObject("History");
        history.setResource(R.drawable.ic_history);
        history.setBgColor(getResources().getColor(R.color.colorPrimaryDark));

        menuObjects.add(close);
        menuObjects.add(fontSize);
        menuObjects.add(bookmark);
        menuObjects.add(share);
        menuObjects.add(newTab);
        menuObjects.add(addHome);
        menuObjects.add(history);

        return menuObjects;
    }

    private void initListener() {
        fabBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSaved = !isSaved;
                fabBookmark.setImageResource(isSaved ? R.drawable.ic_bookmark_saved : R.drawable.ic_bookmark_not_save);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_search:
                Intent intent = new Intent(DetailsActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_more:
                mMenuDialogFragment.show(getSupportFragmentManager(), ContextMenuDialogFragment.TAG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
