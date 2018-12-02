package com.gachi.guide_bench_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


public class EventActivity extends AppCompatActivity {
    private ImageView img_event_back;
private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Back();
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.board_qna_recycler_view);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        QnAListAdapter qnaListAdapter = new QnAListAdapter(this);
//        mRecyclerView.setAdapter(qnaListAdapter);

    }

    public void Back(){
        img_event_back=(ImageView)findViewById(R.id.img_event_back);
        img_event_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

