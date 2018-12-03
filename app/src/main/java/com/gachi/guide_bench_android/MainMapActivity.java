package com.gachi.guide_bench_android;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gachi.guide_bench_android.adapter.MapStoreListAdapter;
import com.gachi.guide_bench_android.model.MapStoreListData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private ImageView img_map_back;
    private TextView txt_map_bench_name;
    private RecyclerView mRecyclerView;
    private MapStoreListAdapter Adapter;
    private ArrayList<MapStoreListData> storeItems;

    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getMap();
        setRecyclerView();
        makeStoreList();
    }

    private void makeStoreList() {
       storeItems = new ArrayList<MapStoreListData>();
        storeItems.add(new MapStoreListData("광장시장", "시장", "서울특별시 종로구 종로4가 창경궁로 88"));
        storeItems.add(new MapStoreListData("낙산냉면", "맛집", "서울특별시 종로구 창신1동 81-8"));
        storeItems.add(new MapStoreListData("광장시장", "시장", "서울특별시 종로구 종로4가 창경궁로 88"));
    }

    private void setRecyclerView() {

        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.map_store_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
            };
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        mRecyclerView.setHasFixedSize(true);

        Adapter = new MapStoreListAdapter(storeItems, this);
        mRecyclerView.setAdapter(Adapter);
        setOnBtnClickListener();

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


    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng MYBENCH = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(MYBENCH);
        markerOptions.title("낙산공원 벤치");
        markerOptions.snippet("나와 제일 가까운 벤치");
        map.addMarker(markerOptions);
        map.setOnMarkerClickListener(this);
        map.moveCamera(CameraUpdateFactory.newLatLng(MYBENCH));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));

    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        txt_map_bench_name = (TextView) findViewById(R.id.txt_map_bench_name);
        txt_map_bench_name.setText(marker.getTitle());
        return true;
    }
}
