package com.example.chapter_04_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //ch_04_3.xml
    EditText edit1, edit2;
    Button Add, Sub, Mul, Div, Rem;
    TextView tResult;

    //ch_04_4.xml
    TextView text1, text2;
    Switch switch1;
    RadioGroup rgroup;
    RadioButton kitkat, lollipop, jellybean;
    ImageView imgView;
    Button end, restart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch_04_4);


        /*ch_04_3.xml, 초간단계산기(수정)
        setTitle("초간단 계산기(수정)");
        //숫자 입력
        edit1= findViewById(R.id.Edit1);
        edit2= findViewById(R.id.Edit2);
        //계산기 정의 변수
        Add= findViewById(R.id.BtnAdd);
        Sub= findViewById(R.id.BtnSub);
        Mul= findViewById(R.id.BtnMul);
        Div= findViewById(R.id.BtnDiv);
        Rem= findViewById(R.id.BtnRem);
        //결과 출력
        tResult= findViewById(R.id.TextResult);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= edit1.getText().toString();
                String str2= edit2.getText().toString();

                if(str1.equals("")||str2.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Integer result= Integer.parseInt(str1)+Integer.parseInt(str2);
                    tResult.setText("계산결과:"+result);
                }

            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= edit1.getText().toString();
                String str2= edit2.getText().toString();

                if(str1.equals("")||str2.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Double result= Double.parseDouble(str1)-Double.parseDouble(str2);
                    tResult.setText("계산결과:"+result);
                }
            }
        });

        Mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= edit1.getText().toString();
                String str2= edit2.getText().toString();

                if(str1.equals("")||str2.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Double result= Double.parseDouble(str1)*Double.parseDouble(str2);
                    tResult.setText("계산결과:"+result);
                }
            }
        });

        Div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= edit1.getText().toString();
                String str2= edit2.getText().toString();

                if(str1.equals("")||str2.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if(str2.equals("0")){
                        Toast.makeText(getApplicationContext(),"0으로 나눌수없습니다!",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Double num1= Double.parseDouble(str1);
                        Double num2= Double.parseDouble(str2);
                        Double result= num1/num2;
                        result = (int)(result*100)/100.0; //소수점 둘째자리까지 출력
                        tResult.setText("계산결과:"+result);
                    }

                }
            }
        });

        Rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1= edit1.getText().toString();
                String str2= edit2.getText().toString();

                if(str1.equals("")||str2.equals("")){
                    Toast.makeText(getApplicationContext(),"숫자를 입력하세요",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Double result= Double.parseDouble(str1)%Double.parseDouble(str2);
                    tResult.setText("계산결과:"+result);
                }
            }
        });*/

        //ch_04_4.xml
        setTitle("안드로이드 사진보기");
        text1= findViewById(R.id.Text1);
        switch1= findViewById(R.id.switch1);

        text2= findViewById(R.id.Aversion);
        rgroup= findViewById(R.id.Rgroup1);
        kitkat= findViewById(R.id.KitKat);
        lollipop= findViewById(R.id.Lollipop);
        jellybean= findViewById(R.id.Jellybean);
        imgView= findViewById(R.id.imgView);

        end= findViewById(R.id.btnEnd);
        restart= findViewById(R.id.btnReStart);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()== true){ //스위치 on
                    text2.setVisibility(View.VISIBLE);
                    rgroup.setVisibility(View.VISIBLE);
                    imgView.setVisibility(View.VISIBLE);
                    end.setVisibility(View.VISIBLE);
                    restart.setVisibility(View.VISIBLE);
                }else{ //스위치 off
                    text2.setVisibility(View.INVISIBLE);
                    rgroup.setVisibility(View.INVISIBLE);
                    imgView.setVisibility(View.INVISIBLE);
                    end.setVisibility(View.INVISIBLE);
                    restart.setVisibility(View.INVISIBLE);
                }
            }
        });

        //안드로이드 버전 라디오 버튼 클릭시 버전에 따른 이미지 출력
        kitkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.kitkat);
            }
        });

        lollipop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.lollipop);
            }
        });

        jellybean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView.setImageResource(R.drawable.api43);
            }
        });


        end.setOnClickListener(new View.OnClickListener() { //종료버튼
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() { //다시시작버튼
            @Override
            public void onClick(View v) {
                text2.setVisibility(View.INVISIBLE);
                rgroup.setVisibility(View.INVISIBLE);
                imgView.setVisibility(View.INVISIBLE);
                end.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.INVISIBLE);

                switch1.setChecked(false);
                rgroup.clearCheck();
                imgView.setImageResource(0);
            }
        });


    }
}