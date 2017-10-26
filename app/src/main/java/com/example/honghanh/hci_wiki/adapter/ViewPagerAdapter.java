package com.example.honghanh.hci_wiki.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.honghanh.hci_wiki.fragments.BookmarkFragment;
import com.example.honghanh.hci_wiki.fragments.HistoryFragment;
import com.example.honghanh.hci_wiki.fragments.HomeFragment;
import com.example.honghanh.hci_wiki.fragments.PageFragment;
import com.example.honghanh.hci_wiki.fragments.SettingsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new HomeFragment();
            case 1: return new PageFragment();
            case 2: return new BookmarkFragment();
            case 3: return new HistoryFragment();
            case 4: return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
