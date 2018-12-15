package com.gachi.guide_bench_android.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.gachi.guide_bench_android.R
import com.gachi.guide_bench_android.data.EventData
import com.gachi.guide_bench_android.network.ApplicationController
import com.gachi.guide_bench_android.network.NetworkService
import com.gachi.guide_bench_android.post.PostLikeListResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventLikeListAdapter(val ctx: Context, val eventList: ArrayList<EventData>) : RecyclerView.Adapter<EventLikeListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.activity_event_like_list, parent, false)
        return Holder(view)
    }
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text= eventList[position].event_title
        holder.content.text= eventList[position].event_content
//        holder.likeimg.setOnClickListener {
//            postLikeListResponse(ctx,position,holder)
//            if (eventList[position].like == true){
//                holder.likeimg.setImageResource(R.drawable.ic_likelist_unselected)
//                eventList[position].like = false
//            }else{
//                holder.likeimg.setImageResource(R.drawable.ic_likelist_actived)
//                eventList[position].like = true
//            }
//        }
            }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txt_event_list_title) as TextView
        val content: TextView = itemView.findViewById(R.id.txt_event_list_content) as TextView
//        val likeimg : ImageView = itemView.findViewById(R.id.img_event_like) as ImageView
    }

//
//    private fun postLikeListResponse(context : Context, position: Int,holder: Holder){
//        val event_id = eventList[position]._id
//        val event_name = eventList[position].event_title
//        var pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val user_id = pref.getString("input_idx", null) as String
//        val jsonObject: JSONObject = JSONObject()
//        jsonObject.put("event_id", event_id)
//        val gsonObject: JsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
//        val postLikeListResponse = networkService.postLikeListResponse("application/json", user_id ,gsonObject)
//        postLikeListResponse.enqueue(object : Callback<PostLikeListResponse> {
//            override fun onFailure(call: Call<PostLikeListResponse>, t: Throwable) {
//                Log.e("board list fail", t.toString())
//            }
//
//            override fun onResponse(call: Call<PostLikeListResponse>, response: Response<PostLikeListResponse>) {
//
//                if (response.isSuccessful){
//
//                    Toast.makeText(context, "'"+event_name+"'을(를) 좋아요한 이벤트 목록에 등록하였습니다!", Toast.LENGTH_SHORT).show()
//                    Log.v("좋아요누르기","통신성공!!")
//
//
//
//
//                }
//            }
//        })
//    }


}