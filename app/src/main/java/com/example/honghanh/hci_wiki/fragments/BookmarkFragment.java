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

import com.example.honghanh.hci_wiki.DetailsActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.adapter.BookmarkAdapter;
import com.example.honghanh.hci_wiki.storage.model.Data;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;


public class BookmarkFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final GridView grvBookmark = (GridView) view.findViewById(R.id.grv_bookmark);
        grvBookmark.setAdapter(new BookmarkAdapter(getContext()));
        grvBookmark.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra(KEY_CONTENT_DATA, (Data) grvBookmark.getItemAtPosition(position));
                getContext().startActivity(intent);
            }
        });
    }
}
