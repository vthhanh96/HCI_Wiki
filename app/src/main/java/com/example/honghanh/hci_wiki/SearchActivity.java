package com.example.honghanh.hci_wiki;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.honghanh.hci_wiki.adapter.HistoryAdapter;
import com.example.honghanh.hci_wiki.storage.PreferManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.edt_search)
    EditText edtSearch;

    @Bind(R.id.view_back)
    View vBack;

    @Bind(R.id.img_delete_search)
    ImageView imgDelete;

    @Bind(R.id.ll_no_history_data)
    LinearLayout llNoHistory;

    @Bind(R.id.ll_history)
    LinearLayout llHistory;

    @Bind(R.id.rcv_history)
    RecyclerView rcvHistory;

    @Bind(R.id.rcv_search_data)
    RecyclerView rcvSearchData;

    @Bind(R.id.ll_no_results)
    LinearLayout llNoResults;

    private List<String> mListHistory;

    private HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initData() {
        mListHistory = PreferManager.getInstance(this).getListHistory();
        if(mListHistory.size() == 0) {
            llNoHistory.setVisibility(View.VISIBLE);
        } else {
            llHistory.setVisibility(View.VISIBLE);
        }

        historyAdapter = new HistoryAdapter(this, mListHistory);
        rcvHistory.setAdapter(historyAdapter);
        rcvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initListener() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateListSearchData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("");
            }
        });

        historyAdapter.setIHistoryListener(new HistoryAdapter.IHistoryLisener() {
            @Override
            public void onDeleteItemHistory(int position) {
                mListHistory.remove(position);
                historyAdapter.notifyDataSetChanged();
                updateListHistory();
            }

            @Override
            public void onItemClick(int position) {
                edtSearch.setText(mListHistory.get(position));
            }
        });
    }

    private void updateListSearchData(String s) {

    }

    private void onSearch() {
        updateListHistory();
    }

    private void updateListHistory() {
        PreferManager.getInstance(this).saveListHistory(mListHistory);
    }

    private void updateUI() {
        if(mListHistory.size() > 0) {
            llHistory.setVisibility(View.VISIBLE);
            llNoHistory.setVisibility(View.GONE);
        } else {
            llNoHistory.setVisibility(View.VISIBLE);
            llHistory.setVisibility(View.GONE);
        }
    }
}
