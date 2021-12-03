package com.cookandroid.chapter06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Chronometer chr;
    Button bStart, bEnd;
    RadioButton rCal, rTime;
    CalendarView calView;
    DatePicker dPicker;
    TimePicker tPicker;
    TextView tYear, tMonth, tDay, tHour, tMinute;
    int selectY, selectMo, selectD, selectH, selectMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch06_02);
        /* 실습하기 6_1(ch06_03~ch06_09)
        setTitle("시간 예약");
         //타이틀바에 이미지 넣기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);
         
         //바인딩
        bStart= findViewById(R.id.btnStart);
        bEnd= findViewById(R.id.btnEnd);
        chr= findViewById(R.id.chronometer01);
        rCal= findViewById(R.id.rdoCal);
        rTime= findViewById(R.id.rdoTime);
        tPicker= findViewById(R.id.timePicker01);
        calView= findViewById(R.id.calendarView01);
        tYear= findViewById(R.id.textYear);
        tMonth= findViewById(R.id.textMonth);
        tDay= findViewById(R.id.textDay);
        tHour= findViewById(R.id.textHour);
        tMinute= findViewById(R.id.textMinte);

         //버튼이벤트 설정을 정의하기위해 첫화면에서 보이지않도록 설정한다
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);


         //라디오 버튼 이벤트(날짜, 시간)
        rCal.setOnClickListener(new View.OnClickListener() { //컬린더뷰 
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE);
            }
        });
        rTime.setOnClickListener(new View.OnClickListener() { //타임파커
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                calView.setVisibility(View.INVISIBLE);
            }
        });

         //버튼 이벤트(예약시작,예약종료)
        bStart.setOnClickListener(new View.OnClickListener() { //예약시작, 타이머 설정
            @Override
            public void onClick(View v) {
                chr.setBase(SystemClock.elapsedRealtime()); //크로노미터 초기화
                chr.start(); 
                chr.setTextColor(Color.RED);
            }
        });
        bEnd.setOnClickListener(new View.OnClickListener() { //예약종료
            @Override
            public void onClick(View v) {
                chr.stop();
                chr.setTextColor(Color.BLUE);

                //년,월,일,시,분-> 컬런더뷰와 타임파커에서 값을 가져온다
                tYear.setText(Integer.toString(selectY));
                tMonth.setText(Integer.toString(selectMo));
                tDay.setText(Integer.toString(selectD));
                tHour.setText(Integer.toString(tPicker.getHour()));
                tMinute.setText(Integer.toString(tPicker.getMinute()));
            }
        });

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             //클릭할때(날짜)마다 선택된 값을 변수(미리 만든 연,월, 일)에 저장
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectY= year;
                selectMo= month+1;
                selectD= dayOfMonth;
            }
        }); */

        /*직접 풀어보기 6-1
        setTitle("시간 예약");
        //타이틀바에 이미지 넣기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);

        //바인딩
        bStart= findViewById(R.id.btnStart);
        bEnd= findViewById(R.id.btnEnd);
        chr= findViewById(R.id.chronometer01);
        rCal= findViewById(R.id.rdoCal);
        rTime= findViewById(R.id.rdoTime);
        tPicker= findViewById(R.id.timePicker01);
        dPicker= findViewById(R.id.datePicker);
        tYear= findViewById(R.id.textYear);
        tMonth= findViewById(R.id.textMonth);
        tDay= findViewById(R.id.textDay);
        tHour= findViewById(R.id.textHour);
        tMinute= findViewById(R.id.textMinte);

        //크로노미터를 클릭하기전까지 보여지지않도록 설정한다
        tPicker.setVisibility(View.INVISIBLE);
        dPicker.setVisibility(View.INVISIBLE);
        rCal.setVisibility(View.INVISIBLE);
        rTime.setVisibility(View.INVISIBLE);

        //라디오 버튼 이벤트(날짜, 시간)
        rCal.setOnClickListener(new View.OnClickListener() { //데이트 피커
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.INVISIBLE);
                dPicker.setVisibility(View.VISIBLE);
            }
        });
        rTime.setOnClickListener(new View.OnClickListener() { //타임 피커
            @Override
            public void onClick(View v) {
                tPicker.setVisibility(View.VISIBLE);
                dPicker.setVisibility(View.INVISIBLE);
            }
        });



        //크로노미터 클릭 (이벤트 정의)
        chr.setOnClickListener(new View.OnClickListener() { //예약시작
            @Override
            public void onClick(View v) {
                chr.setBase(SystemClock.elapsedRealtime());
                chr.start();
                chr.setTextColor(Color.RED);
                rCal.setVisibility(View.VISIBLE);
                rTime.setVisibility(View.VISIBLE);
            }
        });
        // 0000(년) 텍스트뷰 클릭
        tYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                chr.stop();
                chr.setTextColor(Color.BLUE);

                //(년,월,일)-> dPicker (시,분)-> tPicker
                tYear.setText(Integer.toString(dPicker.getYear()));
                tMonth.setText(Integer.toString(dPicker.getMonth()+1));
                tDay.setText(Integer.toString(dPicker.getDayOfMonth()));
                tHour.setText(Integer.toString(tPicker.getHour()));
                tMinute.setText(Integer.toString(tPicker.getMinute()));

                //라디오버튼과 피커를 보이지않도록 설정
                tPicker.setVisibility(View.INVISIBLE);
                dPicker.setVisibility(View.INVISIBLE);
                rCal.setVisibility(View.INVISIBLE);
                rTime.setVisibility(View.INVISIBLE);
                
                return false;
            }
        });*/


        /*ch06-11(ch06-10과 연결)
        String[] items= {"CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends","Fringe","Lost"}; //자동완성될 단어
        //자동완성텍스트뷰
        AutoCompleteTextView auto= findViewById(R.id.autoCompleteText);
         //어댑터: 어댑터뷰(자동완성텍스트뷰, 멀티자동완성텍스트뷰)와 데이터(items)를 연결, (this(위치),1줄의 디자인(심플사용), 데이터(n개))
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        auto.setAdapter(adapter); //어댑터뷰+어댑터
        //멀티자동완성텍스트뷰
        MultiAutoCompleteTextView multi= findViewById(R.id.MultiAuto);
         //쉼표로 구분하기 위한 객체를 생성
        MultiAutoCompleteTextView.CommaTokenizer commaTokenizer= new MultiAutoCompleteTextView.CommaTokenizer();
        multi.setTokenizer(commaTokenizer);
        multi.setAdapter(adapter); //어댑터뷰+어댑터
        */

        
        /*ch06-16 뷰플리퍼(ch06-15와 연결)
        Button bPrev, bNext;
        final ViewFlipper vFlipper; //내부클래스안에서 변수에 접근하기 위한 약속된 문법
        
        bPrev= findViewById(R.id.bPrev);
        bNext= findViewById(R.id.bNext);
        vFlipper= findViewById(R.id.vFlipper);
        
        bPrev.setOnClickListener(new View.OnClickListener() { //이전버튼 이벤트 정의
            @Override
            public void onClick(View v) {
                vFlipper.showPrevious();
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() { //다음버튼 이벤트 정의
            @Override
            public void onClick(View v) {
                vFlipper.showNext();
            }
        });*/

        /*직접 풀어보기 6-2 */
        Button bStart, bStop;
        final ViewFlipper vFlipper; //내부클래스안에서 변수에 접근하기 위한 약속된 문법

        bStart= findViewById(R.id.bStart);
        bStop= findViewById(R.id.bStop);
        vFlipper= findViewById(R.id.vFlipper);

        bStart.setOnClickListener(new View.OnClickListener() { //사진보기 버튼 이벤트 정의
            @Override
            public void onClick(View v) {
                vFlipper.setFlipInterval(3000); //화면 넘김 간격 3초 설정
                vFlipper.startFlipping(); //화면 넘김 메소드
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() { //사진보기 정지 이벤트 정의
            @Override
            public void onClick(View v) {
                vFlipper.stopFlipping(); //화면 정지 메소드
            }
        });


    }
}