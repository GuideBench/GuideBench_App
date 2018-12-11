package com.gachi.guide_bench_android.post

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PostLikeListResponse(
        @SerializedName("message")
        @Expose
        open var message : String?=null,
        var event_id : String,
        var benchinfo_id: String
)