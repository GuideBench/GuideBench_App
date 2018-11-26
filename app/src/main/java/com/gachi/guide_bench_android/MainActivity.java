package com.gachi.guide_bench_android;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gachi.guide_bench_android.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private PagerAdapter adapter;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;

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

        final ImageView mainIcon = new ImageView(this);
        mainIcon.setImageResource(R.drawable.ic_main_unselected);
        mainIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView likeIcon = new ImageView(this);
        likeIcon.setImageResource(R.drawable.ic_likelist_unselected);
        likeIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView boardIcon = new ImageView(this);
        boardIcon.setImageResource(R.drawable.ic_board_unselected);
        boardIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView settingIcon = new ImageView(this);
        settingIcon.setImageResource(R.drawable.ic_settings_unselected);
        settingIcon.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //tablayout icon 선택시
        final ImageView mainIconAct = new ImageView(this);
        mainIconAct.setImageResource(R.drawable.ic_main_active);
        mainIconAct.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView likeIconAct = new ImageView(this);
        likeIconAct.setImageResource(R.drawable.ic_likelist_active);
        likeIconAct.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView boardIconAct = new ImageView(this);
        boardIconAct.setImageResource(R.drawable.ic_board_active);
        boardIconAct.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        final ImageView settingIconAct = new ImageView(this);
        settingIconAct.setImageResource(R.drawable.ic_settings_active);
        settingIconAct.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        tabLayout.getTabAt(0).setCustomView(mainIconAct);
        tabLayout.getTabAt(1).setCustomView(likeIcon);
        tabLayout.getTabAt(2).setCustomView(boardIcon);
        tabLayout.getTabAt(3).setCustomView(settingIcon);

        //탭선택시
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView iv = (ImageView) tab.getCustomView();
                switch (tab.getPosition()) {
                    case 0:
                        iv.setImageResource(R.drawable.ic_main_active);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.ic_likelist_active);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.ic_board_active);
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.ic_settings_active);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {
                ImageView iv = (ImageView) tab1.getCustomView();
                switch (tab1.getPosition()) {
                    case 0:
                        iv.setImageResource(R.drawable.ic_main_unselected);
                        break;
                    case 1:
                        iv.setImageResource(R.drawable.ic_likelist_unselected);
                        break;
                    case 2:
                        iv.setImageResource(R.drawable.ic_board_unselected);
                        break;
                    case 3:
                        iv.setImageResource(R.drawable.ic_settings_unselected);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab2) {
                Log.d("reselected tap", String.valueOf(tab2.getPosition()));
            }
        });


    }
    public void inVisibleTabLayout() {
        ((ViewGroup) tabLayout).setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0)
            getFragmentManager().popBackStack();
        else {
            super.onBackPressed();
        }

    }

    public void visibleTabLayout() {
        ((ViewGroup) tabLayout).setVisibility(View.VISIBLE);
    }
}

