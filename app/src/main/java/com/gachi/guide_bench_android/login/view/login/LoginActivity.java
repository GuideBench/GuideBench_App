package com.gachi.guide_bench_android.login.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gachi.guide_bench_android.R;

public class LoginActivity extends AppCompatActivity {
    Button b_act_login_longin;
    Button b_act_login_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b_act_login_longin = findViewById(R.id.b_act_login);
        b_act_login_longin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

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

        startActivity(new Intent(this, SplashActivity.class));

    }

}