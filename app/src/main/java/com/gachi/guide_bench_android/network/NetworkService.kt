package com.gachi.guide_bench_android.network

import com.gachi.guide_bench_android.post.PostSignInResponse
import com.gachi.guide_bench_android.post.PostSignUpResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {
    //회원가입
    @POST("/user/signup") // 어노테이션 (자바형식..? ), 타입형식("경로")
    fun PostSignUpResponse(
            @Header("Content-Type") content_type: String,
            @Body body : JsonObject //요청방식 : foam 형식 (param형식일 때: @Field("id") id: String)
    ) : Call<PostSignUpResponse> //리턴 타입
    //로그인
    @POST("/user/signin")
    fun PostSignInResponse(
            @Header("Content-Type") content_type: String,
            @Body body : JsonObject
    ) : Call<PostSignInResponse>
}
