package com.gachi.guide_bench_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.SignupActivity;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;
import com.gachi.guide_bench_android.post.PostSignInResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    Button b_act_login_longin;
    Button b_act_login_signup;
    private NetworkService networkService=  ApplicationController.Companion.getInstance().getNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startActivity(new Intent(this, SplashActivity.class));
        setOnBtnClickListerner();
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


}