package com.gachi.guide_bench_android;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gachi.guide_bench_android.adapter.EventLikeListAdapter;
import com.gachi.guide_bench_android.adapter.EventListAdapter;
import com.gachi.guide_bench_android.data.EventData;
import com.gachi.guide_bench_android.get.GetEventLikeResponse;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventLikeActivity extends AppCompatActivity {
    private static final String TAG = "EventLikeActivity";
    private ImageView img_event_like_back;
    private RecyclerView mRecyclerView;
    private ImageView img_event_like;
    private EventLikeListAdapter eventListAdapter;
    private ArrayList<EventData> eventList = new ArrayList<>();
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_like);
        getEventListResponse();
        SetRecyclerView();
        Back();
    }

    private void getEventListResponse() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_event_list, null);
        img_event_like = (ImageView)view.findViewById(R.id.img_event_like);
        img_event_like.setVisibility(View.GONE);

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String loginId = pref.getString("input_idx",null);
        Call<GetEventLikeResponse> getEventLikeResponse = networkService.getEventLikeResponse("application/json",loginId);
        getEventLikeResponse.enqueue(new Callback<GetEventLikeResponse>() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onResponse(Call<GetEventLikeResponse> call, Response<GetEventLikeResponse> response) {
                if (response.isSuccessful()) {
//                    Log.v("즐겨찾는 이벤트 페이지 message", response.body().getMessage().toString());

                    ArrayList<EventData> temp = new ArrayList<EventData>();
                    temp= response.body().getData();
                    if (temp.size() > 0) {
                        int position = eventListAdapter.getItemCount();
                        eventListAdapter.getEventList().addAll(temp);
                        eventListAdapter.notifyItemInserted(position);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetEventLikeResponse> call, Throwable t) {
                Log.e("event loading fail", t.toString());
            }
        });
    }



    private void SetRecyclerView() {
        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.event_like_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        mRecyclerView.setHasFixedSize(true);

        eventListAdapter = new EventLikeListAdapter(this, eventList);
        mRecyclerView.setAdapter(eventListAdapter);

    }

    public void Back() {
        img_event_like_back = (ImageView) findViewById(R.id.img_event_like_back);
        img_event_like_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

