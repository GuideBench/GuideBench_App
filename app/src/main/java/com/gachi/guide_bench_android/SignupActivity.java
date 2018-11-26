package com.gachi.guide_bench_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;
import com.gachi.guide_bench_android.post.PostSignUpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private NetworkService networkService=  ApplicationController.Companion.getInstance().getNetworkService();


    private static final String TAG = "GuideBench";
    private Button btn_signup_submit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setOnBtnClickListerner();
}

    private void setOnBtnClickListerner() {
        btn_signup_submit=(Button)findViewById(R.id.btn_signup_submit);
        btn_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSignUpResponseData();

            }
        });

    }

    private void getSignUpResponseData(){

        EditText edit_signup_id = (EditText) findViewById(R.id.edit_signup_id);
        EditText edit_signup_pw = (EditText) findViewById(R.id.edit_signup_pw);
        EditText edit_signup_username= (EditText) findViewById(R.id.edit_signup_username);

        String input_id = edit_signup_id.getText().toString();
        String input_pw = edit_signup_pw.getText().toString();
        String input_name = edit_signup_username.getText().toString();

        JSONObject JsonObject = new JSONObject();
        try {
            JsonObject.put("id",input_id);
            JsonObject.put("pw",input_pw);
            JsonObject.put("name",input_name);
        } catch (JSONException e) {
            Log.e(TAG, "JSONEXception");
        }

        JsonObject gsonObject = (JsonObject) (new JsonParser()).parse(JsonObject.toString());

        Call<PostSignUpResponse> postSignUpResponse = networkService.PostSignUpResponse("application/json", gsonObject);
        postSignUpResponse.enqueue(new Callback<PostSignUpResponse>() {
            @Override
            public void onFailure(Call<PostSignUpResponse> call, Throwable t) {
                Log.e("Sign up fail",t.toString());
            }

            @Override
            public void onResponse(Call<PostSignUpResponse> call, Response<PostSignUpResponse> response) {
                if (response.isSuccessful()) {
                    Log.v("check process2", "check process2!!!");

                    Log.v("회원가입 페이지 message", response.body().getMessage().toString());

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    }

