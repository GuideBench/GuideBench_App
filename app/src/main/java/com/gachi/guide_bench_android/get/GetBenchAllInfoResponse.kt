package com.gachi.guide_bench_android.get

import com.gachi.guide_bench_android.data.benchAllInfoData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetBenchAllInfoResponse(
        @SerializedName("message")
        @Expose
        @JvmField
        open var message :  String? = null,
var data : ArrayList<benchAllInfoData>

)