package com.gachi.guide_bench_android.login.network;

import android.app.Activity;
import android.widget.EditText;

import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.login.network.PostRequest;

import java.net.MalformedURLException;
import java.net.URL;

public class SignupData extends PostRequest {
    public SignupData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        String defaultUrl = "http://Server-IP-Address:80";
        try {
            url = new URL(defaultUrl + "/user/signup");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
