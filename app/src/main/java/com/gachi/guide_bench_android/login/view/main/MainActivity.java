package com.gachi.guide_bench_android.login.view.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.login.view.main.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter adapter;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0)
            getFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tablayout, viewPager 적용
        adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    }
