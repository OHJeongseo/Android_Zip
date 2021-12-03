package com.cookandroid.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);

        /* 직접풀어보기 10-1 추가된 엑티비티(third.xml 연결) */
        Button bThird= findViewById(R.id.btnThird01);
        bThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //현재(세컨드)엑티비티 종료-> 메인엑티비티가 나타난다
            }
        });
    }
}