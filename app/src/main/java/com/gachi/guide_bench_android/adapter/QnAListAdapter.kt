package com.gachi.guide_bench_android.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.gachi.guide_bench_android.R
import com.gachi.guide_bench_android.data.qnaListData

import java.util.ArrayList

class QnAListAdapter( var ctx: Context, var qnaListData: ArrayList<qnaListData>) : RecyclerView.Adapter<QnAListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): QnAListAdapter.ViewHolder {
        val v = LayoutInflater.from(ctx)
                .inflate(R.layout.activity_qna_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return qnaListData.size
    }

    override fun onBindViewHolder(holder: QnAListAdapter.ViewHolder, position: Int) {
        holder.txt_qna_list_title.text = qnaListData[position].title

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_qna_list_title: TextView = itemView.findViewById(R.id.txt_qna_list_title) as TextView
   //     val txt_qna_list_username: TextView = itemView.findViewById(R.id.txt_qna_list_username) as TextView
    //    val txt_qna_list_num: TextView = itemView.findViewById(R.id.txt_qna_list_num) as TextView



    }
}
