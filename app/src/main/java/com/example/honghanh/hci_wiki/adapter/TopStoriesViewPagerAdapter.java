package com.example.honghanh.hci_wiki.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.honghanh.hci_wiki.fragments.FragmentTopStories;

public class TopStoriesViewPagerAdapter extends FragmentStatePagerAdapter {

    public TopStoriesViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentTopStories.newInstance(position);
    }

    @Override
    public int getCount() {
        return 5;
    }
}
