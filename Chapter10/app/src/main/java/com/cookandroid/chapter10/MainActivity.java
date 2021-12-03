package com.cookandroid.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch10_6);

        /*ch10-7 레이팅바 증가, 감소시키기(ch10_6.xml 연결) */
        final RatingBar star01, star02, star03;
        Button bInsert, bDelete;

        star01= findViewById(R.id.rating01);
        star02= findViewById(R.id.rating02);
        star03= findViewById(R.id.rating03);
        bInsert= findViewById(R.id.btnInc);
        bDelete= findViewById(R.id.btnDec);

        bInsert.setOnClickListener(new View.OnClickListener() { //증가 버튼 클릭-> 레이팅바의 별을 증가시킨다
            @Override
            public void onClick(View v) {
                //레이팅바에 채워진 개수(getRating())와 레이팅바에 설정(getStepSize())된 stepSize 속성의 증가 크기를 더하여 별을 채운다
                star01.setRating(star01.getRating()+star01.getStepSize());
                star02.setRating(star02.getRating()+star02.getStepSize());
                star03.setRating(star03.getRating()+star03.getStepSize());
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() { //감소 버튼 클릭-> 레이팅바의 별을 감소시킨다
            @Override
            public void onClick(View v) {
                star01.setRating(star01.getRating()-star01.getStepSize());
                star02.setRating(star02.getRating()-star02.getStepSize());
                star03.setRating(star03.getRating()-star03.getStepSize());
            }
        });

        /*ch10-05 (activity_main.xml 연결, SecondActivity 사용) 
        Button bMain= findViewById(R.id.btnMain);
        bMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 생성, 생성자(첫번째 메인(현재)의 컨텍스트, 두번째 생성된 인텐트에 포함할 엑티비티.class)
                Intent intent= new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent); //-> 새로운 엑티비티 화면에 출력, 파라미터로 인텐트를 받는다
            }
        });*/
        
        /*직접풀어보기 10-1 라디오 버튼으로 선택된 엑티비티가 나오도록 설정한다
        Button bMain= findViewById(R.id.btnMain);
        RadioButton aSecond= findViewById(R.id.Second);
        RadioButton aThird= findViewById(R.id.Third);

        bMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSecond.isChecked()){ //라디오버튼 클릭 여부확인 라디오버튼에 따른 엑티비티를 다르게 화면에 출력한다
                    intent= new Intent(MainActivity.this, SecondActivity.class);
                }else{
                    intent= new Intent(MainActivity.this, ThirdActivity.class);
                }
                startActivity(intent); //-> 새로운 엑티비티 화면에 출력, 파라미터로 인텐트를 받는다
            }
        });*/
    }
}