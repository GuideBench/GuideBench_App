package com.gachi.guide_bench_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.gachi.guide_bench_android.MainMapActivity;
import com.gachi.guide_bench_android.R;

public class MainFragment extends Fragment {
    private RelativeLayout rl_main_go_map_img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rl_main_go_map_img = (RelativeLayout)view.findViewById(R.id.rl_main_go_map_img);
        rl_main_go_map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainMapActivity.class);
                startActivity(intent);
            }
        });

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
