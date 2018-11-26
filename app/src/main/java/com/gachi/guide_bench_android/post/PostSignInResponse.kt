package com.gachi.guide_bench_android.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostSignInResponse(
    @SerializedName("message")
    @Expose
    open var message : String?=null,
    val data : LoginData
)
data class LoginData(
        val user_idx : String
)
