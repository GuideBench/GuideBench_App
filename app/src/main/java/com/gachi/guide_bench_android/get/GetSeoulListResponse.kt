package com.gachi.guide_bench_android.get

import com.gachi.guide_bench_android.data.SeoulData
import com.gachi.guide_bench_android.data.SeoulInfoList
import com.gachi.guide_bench_android.data.benchListData
import com.gachi.guide_bench_android.data.qnaListData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetSeoulListResponse(
        @SerializedName("message")
        @Expose
        @JvmField
        open var message :  String? = null,
var data : ArrayList<SeoulInfoList>

)