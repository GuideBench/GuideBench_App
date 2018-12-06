package com.gachi.guide_bench_android;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.gachi.guide_bench_android.R;

public class BluetoothActivity extends AppCompatActivity {
    private ImageView img_bluetooth_back;
   static int REQUEST_ENABLE_BT = 1004;
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bluetooth);



        checkBlueTooth();
        Back();

}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1004){
            if(resultCode == RESULT_OK) {
                // 블루투스가 활성 상태로 변경됨
                Toast.makeText(getApplicationContext(),"블루투스가 활성화 되었습니다.",Toast.LENGTH_LONG).show();
                finish();
            }

            else if(resultCode == RESULT_CANCELED) {
                // 블루투스가 비활성 상태임
                finish();  //  어플리케이션 종료

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void Back(){
        img_bluetooth_back = (ImageView) findViewById(R.id.img_bluetooth_back);
        img_bluetooth_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkBlueTooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null) {
            //장치가 블루투스를 지원하지 않는 경우.
            finish();
        }

        else {
            // 장치가 블루투스를 지원하는 경우.
            if(!mBluetoothAdapter.isEnabled()) {

                // 블루투스를 지원하지만 비활성 상태인 경우
                // 블루투스를 활성 상태로 바꾸기 위해 사용자 동의 요첨
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

            }
            else {
                // 블루투스를 지원하며 활성 상태인 경우
                // 페어링된 기기 목록을 보여주고 연결할 장치를 선택.
            }

        }

    }

}