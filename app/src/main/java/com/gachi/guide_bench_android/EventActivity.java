package com.gachi.guide_bench_android;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gachi.guide_bench_android.adapter.EventListAdapter;
import com.gachi.guide_bench_android.data.EventData;
import com.gachi.guide_bench_android.get.GetEventLIstResponse;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EventActivity extends AppCompatActivity {
    private ImageView img_event_back;
    private RecyclerView mRecyclerView;
    private EventListAdapter eventListAdapter;
    private ArrayList<EventData> eventList = new ArrayList<>();
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        SetRecyclerView();
        Back();
        getEventListResponse();
    }

    private void getEventListResponse() {
        Call<GetEventLIstResponse> getEventListResponse = networkService.getEventListResponse("application/json");
        getEventListResponse.enqueue(new Callback<GetEventLIstResponse>() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onResponse(Call<GetEventLIstResponse> call, Response<GetEventLIstResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<EventData> temp = new ArrayList<EventData>();
                    temp= response.body().message;
                    if (temp.size() > 0) {
                        int position = eventListAdapter.getItemCount();
                        eventListAdapter.getEventList().addAll(temp);
                        eventListAdapter.notifyItemInserted(position);
                        Toast.makeText(getApplicationContext(), "이벤트가 불러졌습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetEventLIstResponse> call, Throwable t) {
                Log.e("event loading fail", t.toString());
            }
        });
    }


    private void SetRecyclerView() {
        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.event_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        mRecyclerView.setHasFixedSize(true);

        eventListAdapter = new EventListAdapter(this, eventList);
        mRecyclerView.setAdapter(eventListAdapter);

    }

    public void Back() {
        img_event_back = (ImageView) findViewById(R.id.img_event_back);
        img_event_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

