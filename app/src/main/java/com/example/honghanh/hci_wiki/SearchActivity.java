package com.example.honghanh.hci_wiki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.adapter.DataSearchAdapter;
import com.example.honghanh.hci_wiki.adapter.HistoryAdapter;
import com.example.honghanh.hci_wiki.storage.PreferManager;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.edt_search)
    EditText edtSearch;

    @Bind(R.id.img_back)
    ImageView imgBack;

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
    private List<Data> mListAllData;
    private List<Data> mListSearchData;

    private HistoryAdapter historyAdapter;
    private DataSearchAdapter dataSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initData() {
        addData();
        mListHistory = PreferManager.getInstance(this).getListHistory();
        mListAllData = PreferManager.getInstance(this).getListData();
        mListSearchData = new ArrayList<>();

        if(mListHistory.size() == 0) {
            llNoHistory.setVisibility(View.VISIBLE);
        } else {
            llHistory.setVisibility(View.VISIBLE);
        }

        historyAdapter = new HistoryAdapter(this, mListHistory);
        rcvHistory.setAdapter(historyAdapter);
        rcvHistory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        dataSearchAdapter = new DataSearchAdapter(this, mListSearchData);
        rcvSearchData.setAdapter(dataSearchAdapter);
        rcvSearchData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE && !edtSearch.getText().toString().equals("")) {
                    mListHistory.add(edtSearch.getText().toString());
                    onSearch();
                    return true;
                }
                return false;
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
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
                updateListHistory();
                updateUI();
            }

            @Override
            public void onItemClick(int position) {
                edtSearch.setText(mListHistory.get(position));
            }
        });

        dataSearchAdapter.setIDataSearchListener(new DataSearchAdapter.IDataSearchListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                intent.putExtra(KEY_CONTENT_DATA, mListSearchData.get(position));
                startActivity(intent);
            }
        });
    }

    private void updateListSearchData(String s) {
        mListSearchData.clear();
        for (Data data : mListAllData) {
            if(data.getTitle().toLowerCase().contains(s.toLowerCase())) {
                mListSearchData.add(data);
            }
        }
        updateUI();
    }

    private void onSearch() {
        updateListHistory();
        if(mListSearchData.get(0) != null) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(KEY_CONTENT_DATA, mListSearchData.get(0));
            startActivity(intent);
        }
        finish();
    }

    private void updateListHistory() {
        PreferManager.getInstance(this).saveListHistory(mListHistory);
    }

    private void updateUI() {
        if(edtSearch.getText().toString().equals("")) {
            rcvSearchData.setVisibility(View.GONE);
            llNoResults.setVisibility(View.GONE);
            if (mListHistory.size() > 0) {
                llHistory.setVisibility(View.VISIBLE);
                llNoHistory.setVisibility(View.GONE);
            } else {
                llNoHistory.setVisibility(View.VISIBLE);
                llHistory.setVisibility(View.GONE);
            }
        } else {
            llNoHistory.setVisibility(View.GONE);
            llHistory.setVisibility(View.GONE);
            if(mListSearchData.size() > 0) {
                rcvSearchData.setVisibility(View.VISIBLE);
                llNoResults.setVisibility(View.GONE);
            } else {
                rcvSearchData.setVisibility(View.GONE);
                llNoResults.setVisibility(View.VISIBLE);
            }
        }

        historyAdapter.notifyDataSetChanged();
        dataSearchAdapter.notifyDataSetChanged();
    }

    private void addData() {
        mListAllData = new ArrayList<>();

        mListAllData.add(new Data("Animal", "animal.jpg", getString(R.string.animal_content)));
        mListAllData.add(new Data("Book", "book.jpg", ""));
        mListAllData.add(new Data("Flower", "flower.jpg", ""));
        mListAllData.add(new Data("Food", "food.jpg", ""));
        mListAllData.add(new Data("Forest", "forest.jpg", ""));
        mListAllData.add(new Data("Forest", "forest.jpeg", ""));
        mListAllData.add(new Data("Galaxy", "galaxy.jpg", ""));
        mListAllData.add(new Data("HTML", "html.jpg", ""));
        mListAllData.add(new Data("HTML5", "html_1.jpg", ""));
        mListAllData.add(new Data("HTML động", "html_2.jpg", ""));
        mListAllData.add(new Data("Lịch sử HTML", "html_3.png", ""));

        PreferManager.getInstance(this).saveListData(mListAllData);
    }
}
