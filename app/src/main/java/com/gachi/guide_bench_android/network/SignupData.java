package com.gachi.guide_bench_android.network;

import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class SignupData extends PostRequest {
    public SignupData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
        try {
            url = new URL("http://13.124.180.79:3000/user/signup");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
