package com.gachi.guide_bench_android.network

import com.gachi.guide_bench_android.get.GetBenchListResponse
import com.gachi.guide_bench_android.get.GetEventLIstResponse
import com.gachi.guide_bench_android.get.GetQnaListResponse
import com.gachi.guide_bench_android.get.GetSeoulListResponse
import com.gachi.guide_bench_android.post.LoginData
import com.gachi.guide_bench_android.post.PostQnASubmitResponse
import com.gachi.guide_bench_android.post.PostSignInResponse
import com.gachi.guide_bench_android.post.PostSignUpResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    //회원가입
    @POST("/user/signup") // 어노테이션 (자바형식..? ), 타입형식("경로")
    fun PostSignUpResponse(
            @Header("Content-Type") content_type: String,
            @Body body: JsonObject //요청방식 : foam 형식 (param형식일 때: @Field("id") id: String)
    ): Call<PostSignUpResponse> //리턴 타입

    //로그인
    @POST("/user/signin")
    fun PostSignInResponse(
            @Header("Content-Type") content_type: String,
            @Body body: JsonObject
    ): Call<PostSignInResponse>

    //질문 등록
    @POST("/qna/question")
    fun PostQnASubmitResponse(
            @Header("Content-Type") content_type: String,
            @Header("user_id") user_id: String,
            @Body body: JsonObject
    ): Call<PostQnASubmitResponse>

    //이벤트 리스트 불러오기
    @GET("/event/getevent")
    fun getEventListResponse(
            @Header("Content-Type") content_type: String
    ): Call<GetEventLIstResponse>

    //qna리스트 불러오기
    @GET("/qna/getqna")
    fun getQnaListResponse(
            @Header("Content-Type") content_type: String
    ) : Call<GetQnaListResponse>

    //벤치 이름위치리스트 가져오기
    @GET("bench/{bench_id}/getInfo")
    fun getBenchListResponse(
            @Header("Content-Type") content_type: String,
            @Path("bench_id") bench_id: String
    ) : Call<GetBenchListResponse>

    //서울의 모든 맛집과 시장 불러오기
    @GET("/bench/getAllinfo")
    fun getSeoulListResponse(
            @Header("Content-Type") content_type: String
    ) : Call<GetSeoulListResponse>
}
