package com.gachi.guide_bench_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gachi.guide_bench_android.QnAActivity;
import com.gachi.guide_bench_android.R;

public class BoardFragment extends Fragment {
    private TextView txt_board_qna;

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
        return view;
    }
}