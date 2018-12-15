package com.gachi.guide_bench_android.network

import com.gachi.guide_bench_android.get.*
import com.gachi.guide_bench_android.post.*
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

    //즐겨찾기 리스트
    @POST("/bookmark")
    fun postLikeListResponse(
            @Header("Content-Type") content_type: String,
            @Header("user_id") user_id: String,
            @Body body: JsonObject
    ) : Call<PostLikeListResponse>

    //즐겨찾는 이벤트 불러오기
    @GET("/bookmark/event")
    fun getEventLikeResponse(
            @Header("Content-Type") content_type: String,
            @Header("user_id") user_id: String
    ) : Call<GetEventLikeResponse>

    //모든 벤치 정보 불러오기 (For MapActivity)
    @GET("/bench/getAllinfo")
    fun getBenchAllInfoResponse(
            @Header("Content-Type") content_type: String
    ) : Call<GetBenchAllInfoResponse>
}
