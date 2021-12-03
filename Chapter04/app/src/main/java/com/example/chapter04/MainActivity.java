package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* ex04_19.xml 전역변수사용(멤버변수)- 1.객체를 만든다
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView textResult;
    String num1, num2;
    Integer result;*/

    // ex04_28.xml
    TextView text1, text2;
    CheckBox checkBox;
    RadioGroup rGroup;
    RadioButton Dog, Cat, Rabbit;
    Button btnEv;
    ImageView imgPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.ex04_28);

        /*
        //TextView(ex04_16_17_18.xml) 속성 변경
        TextView tv1, tv2, tv3;
        tv1= findViewById(R.id.textView1);
        tv2= findViewById(R.id.textView2);
        tv3= findViewById(R.id.textView3);

        //속성 직접설정
        tv1.setText("안녕하세요");
        tv1.setTextColor(Color.RED);
        tv2.setTextSize(30);
        tv2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        tv3.setText("가나다라마바사아자차카타파하가나다라마바사아자차카타파하");
        tv3.setSingleLine();
         */

        /*초간단 계산기(ex04_19.xml) //2. xml객체(엘리먼트)를 java객체(변수)에 연결한다
        setTitle("초간단 계산기");
        edit1= (EditText) findViewById(R.id.Edit1); //바인딩(엘레먼트->자바변수 대입)
        edit2= (EditText) findViewById(R.id.Edit2);

        btnAdd= (Button) findViewById(R.id.BtnAdd);
        btnSub= (Button) findViewById(R.id.BtnSub);
        btnMul= (Button) findViewById(R.id.BtnMul);
        btnDiv= (Button) findViewById(R.id.BtnDiv);
        textResult= (TextView) findViewById(R.id.TextResult);

        //3.java객체에 효과(메소드나 멤버변수 사용)를 준다
        //4.java객체에 이벤트 처리를 한다
        //4-1. 이벤트 리스너 객체를 만든다
        class MyListener implement  View.OnTouchListener{

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)+Integer.parseInt(num2); //문자열-> 정수변환
                textResult.setText("계산결과:"+result.toString()); //정수-> 문자열 변환
                return false;
            }

        }
        MyListener list1= new MyListener();

        //4-2.java객체에 리스너객체를 등록(리스너 객체사용)한다.
        //btnAdd.setOnTouchListener(list1);
        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)+Integer.parseInt(num2); //문자열-> 정수변환
                textResult.setText("계산결과:"+result.toString()); //정수-> 문자열 변환
                return false;
            }
        });

        btnSub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)-Integer.parseInt(num2); //문자열-> 정수변환
                textResult.setText("계산결과:"+result.toString()); //정수-> 문자열 변환
                return false;
            }
        });

        btnMul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)*Integer.parseInt(num2); //문자열-> 정수변환
                textResult.setText("계산결과:"+result.toString()); //정수-> 문자열 변환
                return false;
            }
        });


        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                num1= edit1.getText().toString();
                num2= edit2.getText().toString();
                result= Integer.parseInt(num1)/Integer.parseInt(num2); //문자열-> 정수변환
                textResult.setText("계산결과:"+result.toString()); //정수-> 문자열 변환
                return false;
            }
        });
        */


        //ex04_28.xml, 객체
        setTitle("애완동물 사진보기");

        text1= findViewById(R.id.Text1);
        checkBox= findViewById(R.id.Check);

        text2= findViewById(R.id.Text2);
        rGroup= findViewById(R.id.Rgroup1);
        Dog= findViewById(R.id.radioDog);
        Cat= findViewById(R.id.radioCat);
        Rabbit= findViewById(R.id.radioRabbit);

        btnEv= findViewById(R.id.btnEv);
        imgPet= findViewById(R.id.imgPet);

        //체크박스(상태) 이벤트 리스너, 체크버튼을 누르면 보이지않도록 설정한 엘레먼트를 보여주도록 재정의(오버라이딩)
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()==true){ //체크 확인
                    text2.setVisibility(View.VISIBLE);
                    rGroup.setVisibility(View.VISIBLE);
                    btnEv.setVisibility(View.VISIBLE);
                    imgPet.setVisibility(View.VISIBLE);
                }else{ //체크 미확인
                    text2.setVisibility(View.INVISIBLE);
                    rGroup.setVisibility(View.INVISIBLE);
                    btnEv.setVisibility(View.INVISIBLE);
                    imgPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        //라디오 버튼에 정의된 동물을 선택하고 버튼을 누르면 동물에 대한 이미지를 출력하는 이벤트를 재정의
        btnEv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택한 라디오 버튼에 따른 스위치 조건문을 정의
                switch (rGroup.getCheckedRadioButtonId()){  //레이아웃.xml의 id값을 가져와서 확인한다
                    case R.id.radioDog:
                        imgPet.setImageResource(R.drawable.dog2);
                        break;
                    case R.id.radioCat:
                        imgPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.radioRabbit:
                        imgPet.setImageResource(R.drawable.rabbit);
                        break;
                    default: //선택된 버튼이 없을때 Toast-msg를 보여준다
                        Toast.makeText(getApplicationContext(),
                                "동물은 먼저 선택하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}