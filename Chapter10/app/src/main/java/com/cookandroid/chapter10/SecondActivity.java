package com.cookandroid.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        /*ch10-3~ ch10-4(second.xml 연결) */
        Button bSecond= findViewById(R.id.btnSecond01);
        bSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //현재(세컨드)엑티비티 종료-> 메인엑티비티가 나타난다
            }
        });
    }
}