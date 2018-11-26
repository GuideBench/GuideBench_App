package com.gachi.guide_bench_android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gachi.guide_bench_android.R;

public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_main, container, false);
//
//        FragmentTransaction transaction = getFragmentManager()
//                .beginTransaction();
//        /*
//         * When this container fragment is created, we fill it with our first
//         * "real" fragment
//         */
//        transaction.replace(R.id.root_frame2, CommunityFragment.newInstance());
//
//        transaction.commit();

        return view;
    }
}
