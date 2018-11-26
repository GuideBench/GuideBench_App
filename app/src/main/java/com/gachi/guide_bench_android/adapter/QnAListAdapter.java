package com.gachi.guide_bench_android.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gachi.guide_bench_android.QnAActivity;
import com.gachi.guide_bench_android.R;

import java.util.ArrayList;

public class QnAListAdapter extends RecyclerView.Adapter<QnAListAdapter.ViewHolder> {
    Context context;

    public QnAListAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public QnAListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_qna_list, viewGroup, false);
        QnAListAdapter.ViewHolder viewHolder = new QnAListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QnAListAdapter.ViewHolder holder, int position) {
        final int pos = position;

     //   Log.v("communityFriendsList", cheerupMsgDataList.size() + " ");
    //    viewHolder.cheerup_msg_date.setText(cheerupMsgDataList.get(i).getFeeling_at());
     //   viewHolder.cheerup_msg_txt.setText(cheerupMsgDataList.get(i).getComment());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_qna_list_num;
        public TextView txt_qna_list_username;
        public TextView txt_qna_list_title;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_qna_list_num = (TextView) itemView.findViewById(R.id.txt_qna_list_num);
            txt_qna_list_username = (TextView)itemView.findViewById(R.id.txt_qna_list_username);
            txt_qna_list_title = (TextView) itemView.findViewById(R.id.txt_qna_list_title);

        }


    }
}
