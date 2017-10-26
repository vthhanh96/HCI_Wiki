package com.example.honghanh.hci_wiki;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.honghanh.hci_wiki.adapter.ViewPagerAdapter;
import com.example.honghanh.hci_wiki.widgets.NonSwipeViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NonSwipeViewPager vpContainer = (NonSwipeViewPager) findViewById(R.id.vp_container);
        vpContainer.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        vpContainer.setCurrentItem(0, false);
                        break;
                    case R.id.action_page:
                        vpContainer.setCurrentItem(1, false);
                        break;
                    case R.id.action_bookmark:
                        vpContainer.setCurrentItem(2, false);
                        break;
                    case R.id.action_history:
                        vpContainer.setCurrentItem(3, false);
                        break;
                    case R.id.action_settings:
                        vpContainer.setCurrentItem(4, false);
                        break;
                }
                return true;
            }
        });
    }
}
