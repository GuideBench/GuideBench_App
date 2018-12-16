package com.gachi.guide_bench_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;
import com.gachi.guide_bench_android.post.LoginData;
import com.gachi.guide_bench_android.post.PostSignInResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public static LoginData temp1;
    public String input_idx;
    Button b_act_login_longin;
    Button b_act_login_signup;
    private NetworkService networkService=  ApplicationController.Companion.getInstance().getNetworkService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(this, SplashActivity.class));
        setOnBtnClickListerner();
        getID();
    }

    private void setOnBtnClickListerner (){

        b_act_login_longin = findViewById(R.id.b_act_login);
        b_act_login_longin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSignInResponseData();
            }

        });

        b_act_login_signup = findViewById(R.id.b_act_login_signup);
        b_act_login_signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }

        });

    }
    private void getSignInResponseData(){
        EditText et_act_login_idEdit=(EditText) findViewById(R.id.et_act_login_idEdit);
        EditText et_act_login_pwEdit=(EditText) findViewById(R.id.et_act_login_pwEdit);
        String input_id= (String)et_act_login_idEdit.getText().toString();
        String input_pw=(String)et_act_login_pwEdit.getText().toString();

        JSONObject JsonObject=new JSONObject();
        try{
            JsonObject.put("id",input_id);
            JsonObject.put("pw",input_pw);
        }catch (JSONException e){
            Log.e(TAG,"JSONEXception");
        }

       JsonObject gsonObject = (JsonObject) (new JsonParser()).parse(JsonObject.toString());

        Call<PostSignInResponse> postSignInResponse = networkService.PostSignInResponse("application/json",gsonObject);
        postSignInResponse.enqueue(new Callback<PostSignInResponse>(){
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onResponse(Call<PostSignInResponse> call, Response<PostSignInResponse> response) {

                if(response.isSuccessful()) {
                    Log.v("로그인 페이지 message", response.body().getMessage().toString());
                   temp1 = response.body().getData();
                   input_idx= temp1.get_id();
                    Log.v("user_idx =",input_idx);
                   saveID(input_idx);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                }else{
                    TextView txt_login_id_err=(TextView) findViewById(R.id.txt_login_id_err);
                    txt_login_id_err.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<PostSignInResponse> call, Throwable t) {
                Log.e("Login fail",t.toString());
            }
        });
    }



        // 값 불러오기
        public String getID(){
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            String loginId = pref.getString("input_idx",null);
            //pref.getString("input_idx", input_idx);
            if (loginId != null){
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "환영합니다", Toast.LENGTH_LONG).show();
            }
            return loginId;
        }

        // 값 저장하기
        private void saveID(String input_idx){
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("input_idx", input_idx);
            editor.commit();
        }

        // 값(Key Data) 삭제하기
        public void removePreferences(){
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.remove("input_idx");
            editor.commit();
        }

        // 값(ALL Data) 삭제하기
        private void removeAllPreferences(){
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
        }


    }

