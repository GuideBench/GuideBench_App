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
import com.gachi.guide_bench_android.data.SeoulData
import com.gachi.guide_bench_android.data.SeoulInfoList
import com.gachi.guide_bench_android.data.benchListData
import com.gachi.guide_bench_android.data.qnaListData
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.view

import java.util.ArrayList

class SeoulListAdapter(var ctx: Context, val seoulListData: ArrayList<SeoulInfoList>) : RecyclerView.Adapter<SeoulListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ctx)
                .inflate(R.layout.activity_map_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return seoulListData.size
    }

    override fun onBindViewHolder(holder: SeoulListAdapter.ViewHolder, position: Int) {
        holder.txt_store_or_market.text = seoulListData[position].benchinfo_category
        holder.txt_store_name.text = seoulListData[position].benchinfo_name
        holder.txt_store_address.text = seoulListData[position].benchinfo_address

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_store_or_market = itemView.findViewById<View>(R.id.txt_store_or_market) as TextView
        val txt_store_name = itemView.findViewById<View>(R.id.txt_store_name) as TextView
        val txt_store_address = itemView.findViewById<View>(R.id.txt_store_address) as TextView

    }

}
