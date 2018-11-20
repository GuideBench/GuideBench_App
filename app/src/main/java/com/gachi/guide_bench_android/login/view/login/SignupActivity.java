package com.gachi.guide_bench_android.login.view.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.login.network.SignupData;
import com.gachi.guide_bench_android.login.view.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends Activity {

    final static String TAG = "AndroidNodeJS";
    final static String defaultUrl = "http://Server-IP-Address:80";
    private Button btn_signup_submit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Button btn_signup_submit=(Button)findViewById(R.id.btn_signup_submit);
        btn_signup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit_signup_id = (EditText) findViewById(R.id.edit_signup_id);
                EditText edit_signup_pw = (EditText) findViewById(R.id.edit_signup_pw);
                EditText edit_signup_username= (EditText) findViewById(R.id.edit_signup_username);

                JSONObject postDataParam = new JSONObject();
                try{
                    postDataParam.put("user",edit_signup_username.getText().toString());
                    postDataParam.put("id",edit_signup_id.getText().toString());
                    postDataParam.put("pw",edit_signup_pw.getText().toString());
                }catch (JSONException e){
                    Log.e(TAG,"JSONEXception");
                }
                new SignupData(SignupActivity.this).execute(postDataParam);


                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
}
}
