package com.gachi.guide_bench_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gachi.guide_bench_android.R;

public class LikeFragment extends Fragment {
    private static final String TAG = "CommunityRootFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.community_root_fragment, container, false);

        return view;
    }
}