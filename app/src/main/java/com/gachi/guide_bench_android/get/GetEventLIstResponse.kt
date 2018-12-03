package com.gachi.guide_bench_android.get

import com.gachi.guide_bench_android.data.EventData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetEventLIstResponse(
        @SerializedName("message")
        @Expose
        @JvmField
        open var message : ArrayList<EventData>
)
