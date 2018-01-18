package com.example.honghanh.hci_wiki.searchpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honghanh.hci_wiki.detailpage.DetailsActivity;
import com.example.honghanh.hci_wiki.R;
import com.example.honghanh.hci_wiki.storage.PreferManager;
import com.example.honghanh.hci_wiki.storage.model.Data;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.honghanh.hci_wiki.Constants.KEY_CONTENT_DATA;

public class SearchActivity extends AppCompatActivity {

    //top bar
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.img_del_search)
    ImageView imgDelete;

    // recent search
    @Bind(R.id.recent_search_container)
    CardView recentSearchContainer;
    @Bind(R.id.tv_no_recent_search)
    TextView tvNoRecentSearch;
    @Bind(R.id.rcv_recent_search)
    RecyclerView rcvRecentSearch;

    //trending search
    @Bind(R.id.trending_search_container)
    CardView trendingSearchContainer;
    @Bind(R.id.tv_javascript)
    TextView tvJavascript;
    @Bind(R.id.tv_c_sharp)
    TextView tvCSharp;
    @Bind(R.id.tv_c_plus_plus)
    TextView tvCPlusPlus;
    @Bind(R.id.tv_java)
    TextView tvJava;
    @Bind(R.id.tv_html)
    TextView tvHTML;
    @Bind(R.id.tv_css)
    TextView tvCSS;

    //results
    @Bind(R.id.results_container)
    CardView resultsContainer;
    @Bind(R.id.tv_no_results)
    TextView tvNoResults;
    @Bind(R.id.rcv_results)
    RecyclerView rcvResults;

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void initData() {
        addData();
        mListHistory = PreferManager.getInstance(this).getListHistory();
        mListAllData = PreferManager.getInstance(this).getListData();
        mListSearchData = new ArrayList<>();

        if(mListHistory.size() == 0) {
            tvNoRecentSearch.setVisibility(View.VISIBLE);
            rcvRecentSearch.setVisibility(View.GONE);
        } else {
            rcvRecentSearch.setVisibility(View.VISIBLE);
            tvNoRecentSearch.setVisibility(View.GONE);
        }
        recentSearchContainer.setVisibility(View.VISIBLE);
        trendingSearchContainer.setVisibility(View.VISIBLE);

        historyAdapter = new HistoryAdapter(this, mListHistory);
        rcvRecentSearch.setAdapter(historyAdapter);
        rcvRecentSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        dataSearchAdapter = new DataSearchAdapter(this, mListSearchData);
        rcvResults.setAdapter(dataSearchAdapter);
        rcvResults.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
                edtSearch.setSelection(edtSearch.getText().length());
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

        tvJavascript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("Javascript");
            }
        });

        tvCSharp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("C#");
            }
        });

        tvCPlusPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("C++");
            }
        });

        tvJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("Java");
            }
        });

        tvHTML.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("HTML5");
            }
        });

        tvCSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSearch.setText("CSS");
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
        if(mListSearchData.size() == 0) return;
        updateListHistory();
        if(mListSearchData.get(0) != null) {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(KEY_CONTENT_DATA, mListSearchData.get(0));
            startActivity(intent);
        }
    }

    private void updateListHistory() {
        PreferManager.getInstance(this).saveListHistory(mListHistory);
    }

    private void updateUI() {
        if(edtSearch.getText().toString().equals("")) {
            imgDelete.setVisibility(View.INVISIBLE);
            recentSearchContainer.setVisibility(View.VISIBLE);
            trendingSearchContainer.setVisibility(View.VISIBLE);
            resultsContainer.setVisibility(View.GONE);
            if (mListHistory.size() > 0) {
                rcvRecentSearch.setVisibility(View.VISIBLE);
                tvNoRecentSearch.setVisibility(View.GONE);
            } else {
                tvNoRecentSearch.setVisibility(View.VISIBLE);
                rcvRecentSearch.setVisibility(View.GONE);
            }
        } else {
            imgDelete.setVisibility(View.VISIBLE);
            recentSearchContainer.setVisibility(View.GONE);
            trendingSearchContainer.setVisibility(View.GONE);
            resultsContainer.setVisibility(View.VISIBLE);
            if(mListSearchData.size() > 0) {
                rcvResults.setVisibility(View.VISIBLE);
                tvNoResults.setVisibility(View.GONE);
            } else {
                rcvResults.setVisibility(View.GONE);
                tvNoResults.setVisibility(View.VISIBLE);
            }
        }

        historyAdapter.notifyDataSetChanged();
        dataSearchAdapter.notifyDataSetChanged();
    }

    private void addData() {
        mListAllData = new ArrayList<>();

        mListAllData.add(new Data("Animal", "animal.jpg", getString(R.string.animal_content)));
        mListAllData.add(new Data("Book", "book.jpg", getString(R.string.book_content)));
        mListAllData.add(new Data("Flower", "flower.jpg", getString(R.string.flower_content)));
        mListAllData.add(new Data("Food", "food.jpg", getString(R.string.food_content)));
        mListAllData.add(new Data("Forest", "forest.jpg", getString(R.string.forest_content)));
        mListAllData.add(new Data("Galaxy", "galaxy.jpg", getString(R.string.galaxy_content)));

        PreferManager.getInstance(this).saveListData(mListAllData);
    }
}
