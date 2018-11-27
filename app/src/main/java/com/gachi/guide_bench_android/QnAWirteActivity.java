package com.gachi.guide_bench_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;
import com.gachi.guide_bench_android.post.PostQnASubmitResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QnAWirteActivity extends AppCompatActivity {
    private static final String TAG = "QnAWirteActivity";
    private ImageView img_board_back;
    private Button btn_qna_write_complete;
    private EditText edit_qna_write_name;
    private EditText edit_qna_write_title;
    private EditText edit_qna_write_content;

    private NetworkService networkService=  ApplicationController.Companion.getInstance().getNetworkService();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_write);

        Back();
        setOnBtnClickListerner();

    }


    private void setOnBtnClickListerner() {
        btn_qna_write_complete=(Button)findViewById(R.id.btn_qna_write_complete);
        btn_qna_write_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQnAWriteResponseData();
            }
        });
    }

    private void getQnAWriteResponseData() {
        edit_qna_write_name = (EditText) findViewById(R.id.edit_qna_write_name);
        edit_qna_write_title = (EditText) findViewById(R.id.edit_qna_write_title);
        edit_qna_write_content = (EditText) findViewById(R.id.edit_qna_write_content);
        String input_name = edit_qna_write_name.getText().toString();
        String input_title = edit_qna_write_title.getText().toString();
        String input_content = edit_qna_write_content.getText().toString();
        JSONObject JsonObject = new JSONObject();
        try {
            JsonObject.put("title", input_title);
            JsonObject.put("content", input_content);
            JsonObject.put("name", input_name);
        } catch (JSONException e) {
            Log.e(TAG, "JSONEXception");
        }

        JsonObject gsonObject = (JsonObject) (new JsonParser()).parse(JsonObject.toString());

        Call<PostQnASubmitResponse> postQnASubmitResponse = networkService.PostQnASubmitResponse("application/json", gsonObject);
        postQnASubmitResponse.enqueue(new Callback<PostQnASubmitResponse>() {

                @Override
                public void onFailure (Call < PostQnASubmitResponse > call, Throwable t){
                Log.e("Write QnA fail..", t.toString());
            }

                @Override
                public void onResponse
                (Call < PostQnASubmitResponse > call, Response < PostQnASubmitResponse > response){
                if (response.isSuccessful()) {
                    Log.v("check qna ok", "check qna ok!!!");

                    Log.v("qna 작성 액티비티 message", response.body().getMessage().toString());
                    Intent intent = new Intent(QnAWirteActivity.this,QnAActivity.class);
                    startActivity(intent);

                    finish();
                }
            }

        });
    }

    private void Back(){

        img_board_back=(ImageView)findViewById(R.id.img_board_back);
        img_board_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
