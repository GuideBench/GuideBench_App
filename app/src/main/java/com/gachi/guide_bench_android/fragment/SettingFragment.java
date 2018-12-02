package com.gachi.guide_bench_android.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gachi.guide_bench_android.LoginActivity;
import com.gachi.guide_bench_android.R;

import static android.content.Context.MODE_PRIVATE;


public class SettingFragment extends Fragment {
private TextView txt_logout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        txt_logout= (TextView) view.findViewById(R.id.txt_logout);
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref =getActivity().getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("input_id");
                editor.commit();
                Log.v("로그아웃 message","로그아웃!");
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}

