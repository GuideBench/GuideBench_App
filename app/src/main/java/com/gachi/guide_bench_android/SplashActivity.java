package com.gachi.guide_bench_android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.gachi.guide_bench_android.R;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();
            }
        }, 3500);// 3.5 초
    }
}

