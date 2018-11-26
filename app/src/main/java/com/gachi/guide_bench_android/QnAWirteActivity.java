package com.gachi.guide_bench_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class QnAWirteActivity extends AppCompatActivity {
    private ImageView img_board_back;
    private Button btn_qna_write_complete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_write);

        img_board_back=(ImageView)findViewById(R.id.img_board_back);
        img_board_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_qna_write_complete=(Button)findViewById(R.id.btn_qna_write_complete);
        btn_qna_write_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish(); //원래는 서버로 보내야함.
            }
        });
    }
}
