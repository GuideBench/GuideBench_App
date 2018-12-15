package com.gachi.guide_bench_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gachi.guide_bench_android.EventLikeActivity;
import com.gachi.guide_bench_android.R;


public class LikeFragment extends Fragment {
    private TextView txt_board_event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_like, container, false);

        txt_board_event = (TextView) view.findViewById(R.id.txt_board_event);
        txt_board_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventLikeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}