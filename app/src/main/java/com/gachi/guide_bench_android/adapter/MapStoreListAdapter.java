package com.gachi.guide_bench_android.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.gachi.guide_bench_android.R;
import com.gachi.guide_bench_android.model.MapStoreListData;

import java.util.ArrayList;

public class MapStoreListAdapter extends RecyclerView.Adapter<MapStoreListAdapter.ViewHolder> {
    ArrayList<MapStoreListData> storeItems;
    private Context context;
    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public MapStoreListAdapter(ArrayList<MapStoreListData> storeItems, Context context) {
        super();
        this.context = context;
        this.storeItems = storeItems;
    }

    @NonNull
    @Override
    public MapStoreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_map_list, parent, false);
        MapStoreListAdapter.ViewHolder viewHolder = new MapStoreListAdapter.ViewHolder(v);
        return viewHolder;
    }

    // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(final MapStoreListAdapter.ViewHolder viewHolder, int position) {
////        Log.v("communityFriendsList", storeItems.size() + " ");
//        viewHolder.txt_store_or_market.setText(storeItems.get(position).getStoretype());
//        viewHolder.txt_store_name.setText(storeItems.get(position).getStorename());
//        viewHolder.txt_store_address.setText(storeItems.get(position).getStoreaddress());
//        setAnimation(holder.imageView, position);

    }


    @Override
    public int getItemCount() {
        return 6;
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_store_name;
        public TextView txt_store_or_market;
        public TextView txt_store_address;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_store_or_market = (TextView) itemView.findViewById(R.id.txt_store_or_market);
            txt_store_name = (TextView) itemView.findViewById(R.id.txt_store_name);
            txt_store_address = (TextView) itemView.findViewById(R.id.txt_store_address);

        }
    }
}
