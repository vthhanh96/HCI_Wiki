package com.example.honghanh.hci_wiki.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.DetailsActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.SearchActivity;
import com.example.honghanh.hci_wiki.adapter.RecentSearchAdapter;
import com.example.honghanh.hci_wiki.storage.model.Data;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;


public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvSearch = (TextView) view.findViewById(R.id.tv_search);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                getContext().startActivity(intent);
            }
        });

        final GridView grvRecentSearch = (GridView) view.findViewById(R.id.grv_recent_search);
        grvRecentSearch.setAdapter(new RecentSearchAdapter(getContext()));
        grvRecentSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(KEY_CONTENT_DATA, (Data) grvRecentSearch.getItemAtPosition(position));
                getContext().startActivity(intent);
            }
        });
    }
}
