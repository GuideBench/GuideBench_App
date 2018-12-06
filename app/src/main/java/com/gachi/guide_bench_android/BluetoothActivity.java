package com.gachi.guide_bench_android;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gachi.guide_bench_android.R;

public class BluetoothActivity extends AppCompatActivity {   @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qnadetail);



    getDefaultAdapter();

}


    private void getDefaultAdapter(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null) {
            //장치가 블루투스를 지원하지 않는 경우.
        }

        else {
            // 장치가 블루투스를 지원하는 경우.
        }

    }
}