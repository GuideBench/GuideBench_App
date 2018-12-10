package com.gachi.guide_bench_android.data

data class SeoulData(
        val id: String,
        val bench_name: String,
        val bench_latitude: String,
        val bench_longitude: String,
        val bench_address: String,
        val bench_info: SeoulInfoList
)

data class SeoulInfoList(
        val benchinfo_category: String,
        val benchinfo_name: String,
        val benchinfo_address: String,
        val id : String
)

