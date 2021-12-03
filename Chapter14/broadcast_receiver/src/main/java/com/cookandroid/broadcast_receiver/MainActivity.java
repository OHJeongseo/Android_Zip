package com.cookandroid.broadcast_receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/* 브로드캐스트 리시버
    실습14-2 베터리 상태 체크앱 만들기 */
    ImageView iBattery;
    EditText eBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //-> 액티비티가 생성될때 바인딩
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*실습14-2 베터리 상태 체크앱 만들기 */
        setTitle("베터리 상태체크");
        iBattery= findViewById(R.id.iBattery);
        eBattery= findViewById(R.id.eBattery);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter= new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter); //브로드캐스트 리시버 등록
    }

    //브로드캐스트 리시버 객체 생성하고 정의
    BroadcastReceiver br= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=  intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){ //베터리 변경여부
                int remain= intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0); //베터리 값을 가져와서 저장
                eBattery.setText("현재 충전량 :"+remain+"%\n"); //베터리값 출력
                Toast.makeText(getApplicationContext(), "현재충전량 :"+remain, Toast.LENGTH_SHORT).show();
                
                //베터리 값에 따라 이미지 변경
                if(remain>= 90)
                    iBattery.setImageResource(R.drawable.battery_100);
                else if(remain>= 70)
                    iBattery.setImageResource(R.drawable.battery_80);
                else if(remain>= 50)
                    iBattery.setImageResource(R.drawable.battery_60);
                else if(remain>= 10)
                    iBattery.setImageResource(R.drawable.battery_20);
                else
                    iBattery.setImageResource(R.drawable.battery_0);

                //어뎁터,USB 연결 확인
                int plug= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug){
                    case 0:
                        eBattery.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        eBattery.append("전원 연결 : 어뎁터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        eBattery.append("전원 연결: USB 연결됨");
                        break;
                }
            }
        }
    };
}