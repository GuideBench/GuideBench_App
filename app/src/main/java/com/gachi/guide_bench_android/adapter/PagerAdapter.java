package com.gachi.guide_bench_android.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.gachi.guide_bench_android.fragment.BoardFragment;
import com.gachi.guide_bench_android.fragment.LikeFragment;
import com.gachi.guide_bench_android.fragment.MainFragment;
import com.gachi.guide_bench_android.fragment.SettingFragment;


public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MainFragment();
                case 1:
                    return new LikeFragment();
                case 2:
                    return new BoardFragment();
                case 3:
                    return new SettingFragment();
                default:
                    return null;
            }
        }
}
