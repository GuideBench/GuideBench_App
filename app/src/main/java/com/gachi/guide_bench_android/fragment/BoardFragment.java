package com.gachi.guide_bench_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gachi.guide_bench_android.BoardActivity;
import com.gachi.guide_bench_android.EventActivity;
import com.gachi.guide_bench_android.QnAActivity;
import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.SeoulStoreActivity;

public class BoardFragment extends Fragment {
    private TextView txt_board_qna;
    private TextView txt_board_seoul_store;
    private TextView txt_board_event;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);
        txt_board_qna = (TextView)view.findViewById(R.id.txt_board_qna);
        txt_board_qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QnAActivity.class);
                startActivity(intent);
            }
        });
//        txt_board_seoul_store = (TextView)view.findViewById(R.id.txt_board_seoul_store);
//        txt_board_seoul_store.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SeoulStoreActivity.class);
//                startActivity(intent);
//            }
//        });
        txt_board_event = (TextView)view.findViewById(R.id.txt_board_event);
        txt_board_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}