package com.example.honghanh.hci_wiki.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.Constants;
import com.example.honghanh.hci_wiki.detailpage.DetailsActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.model.Data;
import com.example.honghanh.hci_wiki.widgets.RecentsAdapter;
import com.example.honghanh.hci_wiki.widgets.RecentsList;

import java.util.ArrayList;
import java.util.List;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;


public class PageFragment extends Fragment {

    private List<Data> mList;

    private LinearLayout llNoPages;
    private RecentsList recentsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        initList();

        llNoPages = (LinearLayout) view.findViewById(R.id.ll_no_pages);

        recentsList = (RecentsList) view.findViewById(R.id.recents);
        recentsList.setAdapter(new RecentsAdapter() {
            @Override
            public String getTitle(int position) {
                return mList.get(position).getTitle();
            }

            @Override
            public View getView(int position) {
                ImageView iv = new ImageView(getActivity());
                String uriImage = Constants.PATH_ASSETS_FILE + mList.get(position).getImage();
                Glide.with(getContext()).load(Uri.parse(uriImage)).into(iv);
                iv.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
                iv.setBackgroundColor(0xffffffff);
                return iv;
            }

            @Override
            public Drawable getIcon(int position) {
                return getResources().getDrawable(R.drawable.ic_delete_dark);
            }

            @Override
            public int getHeaderColor(int position) {
                return 0xffffffff;
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });

        recentsList.setOnItemClickListener(new RecentsList.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(KEY_CONTENT_DATA, mList.get(i));
                getContext().startActivity(intent);
            }

            @Override
            public void onDeleteItemClick(int position) {
                mList.remove(position);
                updateUI();
            }
        });

        return view;
    }

    private void initList() {
        mList = new ArrayList<>();
        mList.add(new Data("Book", "book.jpg", getString(R.string.book_content)));
        mList.add(new Data("Flower", "flower.jpg", getString(R.string.flower_content)));
        mList.add(new Data("Food", "food.jpg", getString(R.string.food_content)));
        mList.add(new Data("Forest", "forest.jpg", getString(R.string.forest_content)));
        mList.add(new Data("Animal", "animal.jpg", getString(R.string.animal_content)));
        mList.add(new Data("Galaxy", "galaxy.jpg", getString(R.string.galaxy_content)));
    }

    private void updateUI() {
        if(mList.size() > 0) {
            llNoPages.setVisibility(View.GONE);
            recentsList.setVisibility(View.VISIBLE);
        } else {
            llNoPages.setVisibility(View.VISIBLE);
            recentsList.setVisibility(View.GONE);
        }
    }
}
