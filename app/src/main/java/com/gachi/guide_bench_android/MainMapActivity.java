package com.gachi.guide_bench_android;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gachi.guide_bench_android.adapter.BenchListAdapter;
import com.gachi.guide_bench_android.data.benchAllInfoData;
import com.gachi.guide_bench_android.data.benchListData;
import com.gachi.guide_bench_android.get.GetBenchAllInfoResponse;
import com.gachi.guide_bench_android.get.GetBenchListResponse;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private ImageView img_map_back;
    private TextView txt_map_bench_name;
    private RecyclerView mRecyclerView;
    private ArrayList<benchListData> benchList;
    private BenchListAdapter Adapter;
    private ArrayList<String> bench_name = new ArrayList<String>();
    private ArrayList<Double> bench_latitude = new ArrayList<Double>();
    private ArrayList<Double> bench_longitude = new ArrayList<Double>();
    private ArrayList<String> id = new ArrayList<String>();
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBenchAllInfoResponse();
        setContentView(R.layout.activity_map);
        getMap();
        setOnBtnClickListener();
        setRecyclerView();
    }


    private void setRecyclerView() {
        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.map_store_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this) {
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
        };
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        //mRecyclerView.setHasFixedSize(true);
        benchList = new ArrayList<benchListData>();
        Adapter = new BenchListAdapter(this, benchList);
        mRecyclerView.setAdapter(Adapter);


    }

    private void getMap() {
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setOnBtnClickListener() {
        img_map_back = (ImageView) findViewById(R.id.img_map_back);
        img_map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getBenchListResponse() {
        Adapter.getBenchListData().clear(); //
        Adapter.notifyDataSetChanged();
        for (int i = 0; i < bench_name.size(); i++) {
            txt_map_bench_name = (TextView) findViewById(R.id.txt_map_bench_name);
            if (txt_map_bench_name.getText().toString().equals(bench_name.get(i))) {
                Call<GetBenchListResponse> getBenchListResponse = networkService.getBenchListResponse("application/json", id.get(i));
                getBenchListResponse.enqueue(new Callback<GetBenchListResponse>() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }

                    @Override
                    public void onResponse(Call<GetBenchListResponse> call, Response<GetBenchListResponse> response) {
                        if (response.isSuccessful()) {
                            ArrayList<benchListData> benchList = new ArrayList<benchListData>();
                            benchList = response.body().getData();
                            if (benchList.size() > 0) {
                                Log.v("벤치통신 성공! 벤치의 수 = ", String.valueOf(benchList.size()));
                                int position = Adapter.getItemCount();
                                Adapter.getBenchListData().addAll(benchList);
                                Adapter.notifyItemInserted(position);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetBenchListResponse> call, Throwable t) {
                        Log.e("Bench info loading fail", t.toString());
                    }
                });
            }
        }
    }
    private void getBenchAllInfoResponse() {

        Call<GetBenchAllInfoResponse> getBenchAllInfoResponse = networkService.getBenchAllInfoResponse("application/json");
        getBenchAllInfoResponse.enqueue(new Callback<GetBenchAllInfoResponse>() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onResponse(Call<GetBenchAllInfoResponse> call, Response<GetBenchAllInfoResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<benchAllInfoData> allbenchItems = new ArrayList<benchAllInfoData>();
                    allbenchItems = response.body().getData();
                    ArrayList<benchAllInfoData> benchAll = new ArrayList<>();
                    benchAll.addAll(allbenchItems);
                    Log.v("benchAll.size ", String.valueOf(benchAll.size()));

                    for(int i=0; i< benchAll.size() ;i++) {
                        id.add(i,benchAll.get(i).get_id());
                        bench_name.add(i,benchAll.get(i).getBench_name());
                        bench_latitude.add(i,benchAll.get(i).getBench_latitude());
                        bench_longitude.add(i,benchAll.get(i).getBench_longitude());
                        Log.v("bench_name",id.get(i));
                   }

                    Toast.makeText(getApplicationContext(), "모든벤치", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<GetBenchAllInfoResponse> call, Throwable t) {
                Log.e("모든벤치통신실패", t.toString());

            }
        });

    }

    @Override
    public void onMapReady(final GoogleMap map) {
        int size = bench_name.size();
        for (int idx = 0; idx < size; idx++) {

            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(bench_latitude.get(idx), bench_longitude.get(idx)))
                    .title(bench_name.get(idx)); // 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            map.addMarker(makerOptions);
        }

        map.setOnMarkerClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(bench_latitude.get(0), bench_longitude.get(0))));
        map.animateCamera(CameraUpdateFactory.zoomTo(9));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        txt_map_bench_name = (TextView) findViewById(R.id.txt_map_bench_name);
        txt_map_bench_name.setText(marker.getTitle());
//
//        ArrayList<benchListData> storeItems = new ArrayList<benchListData>();
//        storeItems.clear();//리스트 비우기
//        Adapter.notifyDataSetChanged();
        getBenchListResponse();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        ArrayList<benchListData> storeItems = new ArrayList<benchListData>();
//        storeItems.clear();//리스트 비우기
//        Adapter.notifyDataSetChanged();
//        getBenchListResponse(); //재통신
    }


}
