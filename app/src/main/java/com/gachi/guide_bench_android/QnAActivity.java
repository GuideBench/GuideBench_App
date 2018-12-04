package com.gachi.guide_bench_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gachi.guide_bench_android.adapter.QnAListAdapter;
import com.gachi.guide_bench_android.data.qnaListData;
import com.gachi.guide_bench_android.get.GetQnaListResponse;
import com.gachi.guide_bench_android.network.ApplicationController;
import com.gachi.guide_bench_android.network.NetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QnAActivity extends AppCompatActivity {
    private ImageView img_board_back;
private ImageView img_qna_write;
    private RecyclerView mRecyclerView;
    private QnAListAdapter qnaListAdapter;
    private ArrayList<qnaListData> qnaListData = new ArrayList<qnaListData>();
    private NetworkService networkService = ApplicationController.Companion.getInstance().getNetworkService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna);

        getQnaListResponse();
        SetRecyclerView();
        Back();


        img_qna_write=(ImageView)findViewById(R.id.img_qna_write);
        img_qna_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QnAActivity.this,QnAWirteActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getQnaListResponse() {
        Call<GetQnaListResponse> getQnaListResponse = networkService.getQnaListResponse("application/json");
        getQnaListResponse.enqueue(new Callback<GetQnaListResponse>() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void onResponse(Call<GetQnaListResponse> call, Response<GetQnaListResponse> response) {
                if (response.isSuccessful()) {
                    ArrayList<qnaListData> temp = new ArrayList<qnaListData>();
                    temp= response.body().getData();
                    if (temp.size() > 0) {
                        int position = qnaListAdapter.getItemCount();
                        qnaListAdapter.getQnaListData().addAll(temp);
                        qnaListAdapter.notifyItemInserted(position);
                        Toast.makeText(getApplicationContext(), "질문이 불러와졌다..", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetQnaListResponse> call, Throwable t) {
                Log.e("questions loading fail", t.toString());
            }
        });
    }


    private void SetRecyclerView() {
        //리사이클러뷰 설정하기
        mRecyclerView = (RecyclerView) findViewById(R.id.board_qna_recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //크기가 변하지 않는다면 true로 설정하기
        mRecyclerView.setHasFixedSize(true);

        qnaListAdapter = new QnAListAdapter(this, qnaListData);
        mRecyclerView.setAdapter(qnaListAdapter);

    }


    public void Back(){
        img_board_back=(ImageView)findViewById(R.id.img_board_back);
        img_board_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

