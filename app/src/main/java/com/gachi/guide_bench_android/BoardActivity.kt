package com.gachi.guide_bench_android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.gachi.guide_bench_android.R.layout.activity_event
import com.gachi.guide_bench_android.adapter.EventListAdapter
import com.gachi.guide_bench_android.data.EventData
import com.gachi.guide_bench_android.get.GetEventLIstResponse
import kotlinx.android.synthetic.main.activity_event.*
import com.gachi.guide_bench_android.network.ApplicationController
import com.gachi.guide_bench_android.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardActivity : AppCompatActivity() {
    val WRITE_ACTIVITY_REQUEST_CODE = 1000
    lateinit var eventListAdapter: EventListAdapter
    val eventList: ArrayList<EventData> by lazy { ArrayList<EventData>() }
    val networkService: NetworkService by lazy { ApplicationController.instance.networkService }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_event)
        setRecyclerView()
        getEventListResponse()
        Back()

    }

    private fun setRecyclerView() {
        eventListAdapter = EventListAdapter(this, eventList)
        event_recycler_view.adapter = eventListAdapter
        event_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    fun Back() {
        img_event_back.setOnClickListener { finish() }
    }
    private fun getEventListResponse() {
        val getEventListResponse = networkService.getEventListResponse("application/json")
        getEventListResponse.enqueue(object : Callback<GetEventLIstResponse> {
            override fun onFailure(call: Call<GetEventLIstResponse>, t: Throwable) {
                Log.e("board list fail", t.toString())
            }

            override fun onResponse(call: Call<GetEventLIstResponse>, response: Response<GetEventLIstResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "이벤트가 불러졌습니다.", Toast.LENGTH_LONG).show()
                    val temp: ArrayList<EventData> = response.body()!!.message

                    if (temp.size > 0) {
                        val position = eventListAdapter.itemCount
                        eventListAdapter.eventList.addAll(temp)
                        eventListAdapter.notifyItemInserted(position)
                    }
                }
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == WRITE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                eventListAdapter.eventList.clear() //리스트 비우기
                eventListAdapter.notifyDataSetChanged()
                getEventListResponse() //재통신
            }
        }
    }

}

