package com.gachi.guide_bench_android;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gachi.guide_bench_android.adapter.BenchAllInfoAdapter;
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
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private MarkerOptions markerOptions;
    private MarkerOptions markerOptions2;
    private static String BOMOONBENCHID = "5c0e393e01ec6a4eeb6cfb70";
    private static String NAKSANBENCHID = "5c0e396b01ec6a4eeb6cfb71";
    private ImageView img_map_back;
    private TextView txt_map_bench_name;
    private RecyclerView mRecyclerView;
    private RecyclerView RecyclerView2;
    private ArrayList<benchListData> benchList;
    private BenchListAdapter Adapter;
    private BenchAllInfoAdapter Adapter2;
    public Integer markersize;
    private ArrayList<benchAllInfoData> allbenchItems;
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getMap();
        setOnBtnClickListener();
        setRecyclerView();
        getBenchAllInfoResponse();
    }


    private void setRecyclerView() {


        RecyclerView2 = (RecyclerView) findViewById(R.id.map_store_recycler_view2);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this) {
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
        };
        RecyclerView2.setLayoutManager(mLayoutManager2);
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
        allbenchItems= new ArrayList<benchAllInfoData>();
        Adapter = new BenchListAdapter(this, benchList);
        Adapter2 = new BenchAllInfoAdapter(this,allbenchItems);
        mRecyclerView.setAdapter(Adapter);
        RecyclerView2.setAdapter(Adapter2);


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

        txt_map_bench_name = (TextView) findViewById(R.id.txt_map_bench_name);
        if (txt_map_bench_name.getText().toString().equals("낙산벤치")) {
            Call<GetBenchListResponse> getBenchListResponse = networkService.getBenchListResponse("application/json", NAKSANBENCHID);
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
                        Toast.makeText(getApplicationContext(), "낙산벤치의 정보가 불러와졌다", Toast.LENGTH_LONG).show();
                        if (benchList.size() > 0) {
                            Log.v("benchList size = ", String.valueOf(benchList.size()));
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
        } else if (txt_map_bench_name.getText().toString().equals("보문사벤치")) {
            Call<GetBenchListResponse> getBenchListResponse = networkService.getBenchListResponse("application/json", BOMOONBENCHID);
            getBenchListResponse.enqueue(new Callback<GetBenchListResponse>() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }

                @Override
                public void onResponse(Call<GetBenchListResponse> call, Response<GetBenchListResponse> response) {
                    if (response.isSuccessful()) {
                        benchList = new ArrayList<benchListData>();
                        benchList = response.body().getData();
                        Toast.makeText(getApplicationContext(), "보문사벤치의 정보가 불러와졌다", Toast.LENGTH_LONG).show();
                        if (benchList.size() > 0) {
                            Log.v("benchList size = ", String.valueOf(benchList.size()));
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
        } else {
            Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getApplicationContext(), "모든벤치", Toast.LENGTH_LONG).show();
                    if (allbenchItems.size() > 0) {
                        int position = Adapter2.getItemCount();
                        Adapter2.getBenchAllInfoData().addAll(allbenchItems);
                        Adapter2.notifyItemInserted(position);

                        Log.v("메롱2", String.valueOf(Adapter2.getBenchAllInfoData().size()));

                    }
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
        LatLng BOMOONBENCH = new LatLng(37.58, 127.97);
        LatLng NAKSANBENCH = new LatLng(37.58, 128.00);
        int position = Adapter2.getItemCount();
        int size = Adapter2.getBenchAllInfoData().size();

//        markerOptions = new MarkerOptions();
//        markerOptions.position(BOMOONBENCH)
//                .title("보문사벤치")
//                .snippet("보문사에 위치한 벤치");
//
//        markerOptions2 = new MarkerOptions();
//        markerOptions2.position(NAKSANBENCH)
//                .title("낙산벤치")
//                .snippet("낙산에 위치한 벤치");
        for (int idx = 0; idx <=size; idx++) {

            Log.v("메롱", String.valueOf(size));
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(57 + idx, 126.92723))
                    .title("마커" + idx); // 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            map.addMarker(makerOptions);
        }

//        map.addMarker(markerOptions);
//        map.addMarker(markerOptions2);
        map.setOnMarkerClickListener(this);
//        map.moveCamera(CameraUpdateFactory.newLatLng(BOMOONBENCH));
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));
        map.animateCamera(CameraUpdateFactory.zoomTo(13));
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
