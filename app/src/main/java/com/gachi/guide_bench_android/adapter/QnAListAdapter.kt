package com.gachi.guide_bench_android.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.gachi.guide_bench_android.R
import com.gachi.guide_bench_android.data.qnaListData
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.view

import java.util.ArrayList

class QnAListAdapter( var ctx: Context, var qnaListData: ArrayList<qnaListData>) : RecyclerView.Adapter<QnAListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(ctx)
                .inflate(R.layout.activity_qna_list, viewGroup, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return qnaListData.size
    }

    override fun onBindViewHolder(holder: QnAListAdapter.ViewHolder, position: Int) {
        holder.txt_qna_list_title.text = qnaListData[position].question_title
        holder.txt_qna_list_username.text = qnaListData[position].question_name
        holder.txt_qna_list_num.text = (position+1).toString()
        holder.txt_qna_my_question.text = qnaListData[position].question_content
        holder.txt_qna_answer.text = qnaListData[position].answer_content
        holder.mView.setOnClickListener{
            Log.v("dd", position.toString())
            if(holder.rl_qna_detail.visibility == View.VISIBLE ) {
                holder.rl_qna_detail.visibility = View.GONE
            }else{
                holder.rl_qna_detail.visibility = View.VISIBLE
            }
        }

    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_qna_list_title: TextView = itemView.findViewById(R.id.txt_qna_list_title) as TextView
        val txt_qna_list_username: TextView = itemView.findViewById(R.id.txt_qna_list_username) as TextView
        val txt_qna_list_num: TextView = itemView.findViewById(R.id.txt_qna_list_num) as TextView
        val txt_qna_my_question: TextView = itemView.findViewById(R.id.txt_qna_my_question) as TextView
        val txt_qna_answer: TextView = itemView.findViewById(R.id.txt_qna_answer) as TextView
val rl_qna_detail : RelativeLayout = itemView.findViewById(R.id.rl_qna_detail) as RelativeLayout

 val mView : View = itemView
    }
}
