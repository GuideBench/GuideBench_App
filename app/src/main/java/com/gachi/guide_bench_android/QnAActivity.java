package com.gachi.guide_bench_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


public class QnAActivity extends AppCompatActivity {
private ImageView img_board_back;
private ImageView img_qna_write;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        img_board_back=(ImageView)findViewById(R.id.img_board_back);
        img_board_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_qna_write=(ImageView)findViewById(R.id.img_qna_write);
        img_qna_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QnAActivity.this,QnAWirteActivity.class);
                startActivity(intent);
            }
        });
    }
}

