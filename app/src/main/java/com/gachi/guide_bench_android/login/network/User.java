package com.gachi.guide_bench_android.login.network;

/**
 * Created by kwanwoo on 2017. 10. 17..
 */

public class User {
    String username;
    String id;
    String pw;

    public User(String username,String id, String pw) {
        this.username =username;
        this.id = id;
        this.pw = pw;
    }

    public String toString() {
        return String.format("USERNAME= %s \n ID = %s \n PW = %s ",username, id,pw);
    }
}