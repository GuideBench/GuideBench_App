package com.gachi.guide_bench_android.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gachi.guide_bench_android.R
import com.gachi.guide_bench_android.data.EventData

class EventListAdapter(val ctx: Context, val eventList: ArrayList<EventData>) :
        RecyclerView.Adapter<EventListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.activity_event_list, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text= eventList[position].title
        holder.content.text= eventList[position].content
            }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txt_event_list_title) as TextView
        val content: TextView = itemView.findViewById(R.id.txt_event_list_content) as TextView
    }
}