package com.gachi.guide_bench_android.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.gachi.guide_bench_android.R
import com.gachi.guide_bench_android.data.benchAllInfoData
import com.gachi.guide_bench_android.data.benchListData
import com.gachi.guide_bench_android.data.qnaListData
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.view

import java.util.ArrayList

class BenchAllInfoAdapter(var ctx: Context, val benchAllInfoData: ArrayList<benchAllInfoData>) : RecyclerView.Adapter<BenchAllInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ctx)
                .inflate(R.layout.allbench, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return benchAllInfoData.size
    }

    override fun onBindViewHolder(holder: BenchAllInfoAdapter.ViewHolder, position: Int) {
        holder.bench_name.text= benchAllInfoData[position].bench_name
        holder.bench_address.text = benchAllInfoData[position].bench_address
        holder.bench_latitude.text = benchAllInfoData[position].bench_latitude
        holder.bench_longitude.text = benchAllInfoData[position].bench_longitude

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bench_name = itemView.findViewById<View>(R.id.bench_name) as TextView
        val bench_address = itemView.findViewById<View>(R.id.bench_address) as TextView
        val bench_latitude = itemView.findViewById<View>(R.id.bench_latitude) as TextView
        val bench_longitude = itemView.findViewById<View>(R.id.bench_longitude) as TextView

    }

}
