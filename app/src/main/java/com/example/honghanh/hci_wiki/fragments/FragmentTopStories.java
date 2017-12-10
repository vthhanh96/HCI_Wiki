package com.example.honghanh.hci_wiki.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.honghanh.hci_wiki.R;

public class FragmentTopStories extends Fragment {

    private static final int FRAGMENT_ONE = 0;
    private static final int FRAGMENT_TWO = 1;
    private static final int FRAGMENT_THREE = 2;
    private static final int FRAGMENT_FOUR = 3;
    private static final int FRAGMENT_FIVE = 4;
    private static final String ARG_SUMMARY_HEADER_INDEX = "index";
    private int currentIndex = 0;

    public static FragmentTopStories newInstance(int index) {
        FragmentTopStories fragment = new FragmentTopStories();
        Bundle args = new Bundle();
        args.putInt(ARG_SUMMARY_HEADER_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentIndex = getArguments().getInt(ARG_SUMMARY_HEADER_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imgTopStory = (ImageView) view.findViewById(R.id.img_story);
        String uriImage = "";
        switch (currentIndex) {
            case FRAGMENT_ONE:
                imgTopStory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.book));
                break;
            case FRAGMENT_TWO:
                imgTopStory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.flower));
                break;
            case FRAGMENT_THREE:
                imgTopStory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.food));
                break;
            case FRAGMENT_FOUR:
                imgTopStory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.forest));
                break;
            case FRAGMENT_FIVE:
                imgTopStory.setImageDrawable(getContext().getResources().getDrawable(R.drawable.animal));
                break;
        }
    }
}
