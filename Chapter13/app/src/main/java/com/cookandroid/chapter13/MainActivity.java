package com.cookandroid.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
/* UI 스레드(여러 작업을 동시에 수행하기위해 사용하는 개념, 멀티스레드라고도 부른다): 
    화면의 위젯을 변경할때 사용한다 */
    TextView text01, text02;
    ProgressBar seek01, seek02;
    Button bStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("UI 스레드 테스트");
        text01= findViewById(R.id.text01);
        text02= findViewById(R.id.text02);
        seek01= findViewById(R.id.seek_ber01);
        seek02= findViewById(R.id.seek_ber02);
        bStart= findViewById(R.id.btnStart);

        //UI 스레드 시작
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        for(int i= seek01.getProgress();i<100;i+=2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seek01.setProgress(seek01.getProgress()+2);
                                    text01.setText("1번 진행률 :"+seek01.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(500);
                        }
                    }
                }.start();

                new Thread(){
                    public void run(){
                        for(int i= seek02.getProgress();i<100;i+=2){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    seek02.setProgress(seek02.getProgress()+2);
                                    text02.setText("2번 진행률 :"+seek02.getProgress()+"%");
                                }
                            });
                            SystemClock.sleep(500);
                        }
                    }
                }.start();
            }
        });

    }
}