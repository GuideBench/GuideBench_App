package com.gachi.guide_bench_android.network

import com.gachi.guide_bench_android.post.PostSignUpResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {
    //회원가입
    @POST("/users") // 어노테이션 (자바형식..? ), 타입형식("경로")
    fun PostSignUpResponse(
            @Header("Content-Type") content_type: String,
            @Body() body : JsonObject //요청방식 : foam 형식 (param형식일 때: @Field("id") id: String)
    ) : Call<PostSignUpResponse> //리턴 타입
}
