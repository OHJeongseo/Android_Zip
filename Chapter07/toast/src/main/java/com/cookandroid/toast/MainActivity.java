package com.cookandroid.toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
    /*토스트: 화면에 잠깐 나타났다 사라지는 메시지 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_12);

        /*ch 7-13*/
        setTitle("토스트 연습");
        final Button btn01= findViewById(R.id.btn01);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //토스트 메세지 생성
                Toast toastM= Toast.makeText(MainActivity.this, "토스트 연습", Toast.LENGTH_SHORT);

                //임의의 위치를 구한다
                Display display= ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int xOffset= (int)(Math.random()*display.getWidth());
                int yOffset= (int)(Math.random()*display.getHeight());

                //설정한 위치에 토스트메세지를 출력한다
                toastM.setGravity(Gravity.TOP|Gravity.LEFT, xOffset, yOffset);
                toastM.show();
            }
        });
    }
}