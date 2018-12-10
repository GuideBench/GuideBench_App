package com.gachi.guide_bench_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gachi.guide_bench_android.adapter.SeoulListAdapter;
import com.gachi.guide_bench_android.data.SeoulInfoList;
import com.gachi.guide_bench_android.get.GetSeoulListResponse;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SeoulStoreActivity extends AppCompatActivity {
private ArrayList<SeoulInfoList> seoulItems;
private SeoulListAdapter Adapter;
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();
    private ImageView img_store_back;
private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Back();
        setRecyclerView();
        getSeoulListResponse();
    }

    private void setRecyclerView() {

        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.board_seoul_list_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this) {
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        //mRecyclerView.setHasFixedSize(true);
        seoulItems = new ArrayList<SeoulInfoList>();
        Adapter = new SeoulListAdapter(this, seoulItems);
        mRecyclerView.setAdapter(Adapter);
    }

    private void getSeoulListResponse() {

            Call<GetSeoulListResponse> getSeoulListResponse = networkService.getSeoulListResponse("application/json");
        getSeoulListResponse.enqueue(new Callback<GetSeoulListResponse>() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public void onResponse(Call<GetSeoulListResponse> call, Response<GetSeoulListResponse> response) {
                    if (response.isSuccessful()) {
                        ArrayList<SeoulInfoList> seoulList = new ArrayList<SeoulInfoList>();
                        seoulList = response.body().getData();
                        if (seoulList.size() > 0) {
                            Log.v("benchList size = ", String.valueOf(seoulList.size()));
                            int position = Adapter.getItemCount();
                            Adapter.getSeoulListData().addAll(seoulList);
                            Adapter.notifyItemInserted(position);
                            Toast.makeText(getApplicationContext(), "서울의 맛집과 시장", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GetSeoulListResponse> call, Throwable t) {
                    Log.e("seoul store fail", t.toString());

                }
            });
        }

    public void Back(){
        img_store_back=(ImageView)findViewById(R.id.img_store_back);
        img_store_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

