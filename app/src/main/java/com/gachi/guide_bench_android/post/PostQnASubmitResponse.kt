package com.gachi.guide_bench_android.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PostQnASubmitResponse(
        @SerializedName("message")
        @Expose
        open var message : String?=null,
        var content : String,
        var title : String
)