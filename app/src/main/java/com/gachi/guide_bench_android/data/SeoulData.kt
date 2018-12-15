package com.gachi.guide_bench_android.data

data class SeoulInfoList(
        val id : String,
        val data : BenchData
)
data class BenchData(
        val benchinfo_category: String,
        val benchinfo_name: String,
        val benchinfo_address: String
)
