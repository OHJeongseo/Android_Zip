package com.cookandroid.chapter14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/*서비스: 화면(엑티비티)없이 동작하는 프로그램, 데몬 또는 백그라운드 프로세스라고도 한다 */
    Intent intent; //MusicService 를 가져올 인텍트 변수 선언
    Button bStart, bStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 실습 14-1 서비스 테스트앱 만들기(ch14-1~ ch14-5) */
        setTitle("서비스 테스트 예제");
        //intent 생성하여 MusicService.class 를 넣는다(이동할 클래스 설정)
        intent= new Intent(this, MusicService.class);
        bStart= findViewById(R.id.btnStart);
        bStop= findViewById(R.id.btnStop);

        bStart.setOnClickListener(new View.OnClickListener() { //음악서비스 시작 버튼
            @Override
            public void onClick(View v) {
                startService(intent); //인텍트 시작(MusicService.onCreate()+onStartCommand() 실행)
                android.util.Log.i("서비스 테스트", "startService()");  //로그 남겨 해당 메소드 실행확인
                Toast.makeText(getApplicationContext(),"startService()",Toast.LENGTH_SHORT).show(); //토스트 메세지
                finish(); //엑티비티 종료
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() { //음악서비스 중지 버튼
            @Override
            public void onClick(View v) {
                stopService(intent); //인텍트 종료(MusicService.onDestroy() 실행)
                android.util.Log.i("서비스 테스트", "stopService()");
                Toast.makeText(getApplicationContext(),"stopService()",Toast.LENGTH_SHORT).show();
            }
        });
    }
}