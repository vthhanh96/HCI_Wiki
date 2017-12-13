package com.example.honghanh.hci_wiki;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.adapter.MenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_BOOKMARK_PAGE;
import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_HISTORY_PAGE;
import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_HOME_PAGE;
import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_PROFILE;
import static com.example.honghanh.hci_wiki.Constants.NAV_DRAWER_ID_SETTINGS_PAGE;

public abstract class DrawerActivity extends AppCompatActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @Bind(R.id.rv_menu)
    RecyclerView rvMenu;

    private MenuAdapter mAdapter;
    private List<ItemMenu> mMenuList;
    private static final int DRAWER_LAUNCH_DELAY = 50;

    abstract protected int getLayoutId();

    abstract protected int getNavId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mMenuList = new ArrayList<>();
        setUpListDrawer();
        setUpNavDrawer();
    }

    private void setUpListDrawer() {
        mMenuList.clear();
        mMenuList.add(new ItemMenu(NAV_DRAWER_ID_HOME_PAGE, "Home", R.drawable.ic_home, getNavId() == NAV_DRAWER_ID_HOME_PAGE));
        mMenuList.add(new ItemMenu(NAV_DRAWER_ID_BOOKMARK_PAGE, "Bookmark", R.drawable.ic_bookmark, getNavId() == NAV_DRAWER_ID_BOOKMARK_PAGE));
        mMenuList.add(new ItemMenu(NAV_DRAWER_ID_HISTORY_PAGE, "History", R.drawable.ic_history, getNavId() == NAV_DRAWER_ID_HISTORY_PAGE));
        mMenuList.add(new ItemMenu(NAV_DRAWER_ID_SETTINGS_PAGE, "Settings", R.drawable.ic_settings, getNavId() == NAV_DRAWER_ID_SETTINGS_PAGE));
        mMenuList.add(new ItemMenu(NAV_DRAWER_ID_PROFILE, "Profile", R.drawable.ic_settings, getNavId() == NAV_DRAWER_ID_PROFILE));
    }

    private void setUpNavDrawer() {
        if(drawerLayout == null) return;

        mAdapter = new MenuAdapter(this, mMenuList);
        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMenu.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MenuAdapter.OnItemMenuClick() {
            @Override
            public void onItemClick(int key) {
                switch (key) {
                    case NAV_DRAWER_ID_HOME_PAGE:
                        break;
                    case NAV_DRAWER_ID_BOOKMARK_PAGE:
                        break;
                    case NAV_DRAWER_ID_HISTORY_PAGE:
                        break;
                    case NAV_DRAWER_ID_SETTINGS_PAGE:
                        break;
                    case NAV_DRAWER_ID_PROFILE:
                        Intent intent = new Intent(DrawerActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected boolean isDrawerOpen() {
        return drawerLayout != null && drawerLayout.isDrawerOpen(Gravity.START);
    }

    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(Gravity.START);
        }
    }

    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(Gravity.START);
        }
    }

    protected void lockDrawer() {
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    protected void unLockDrawer() {
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
}
