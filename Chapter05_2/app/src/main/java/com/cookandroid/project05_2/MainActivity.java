package com.cookandroid.project05_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*ch05_05 */
    EditText edit1, edit2;
    Button Add, Sub, Mul, Div;
    TextView tResult;
    String n1, n2;
    Integer result;
    Double dresult;
    Button[] nBtn= new Button[10]; //번호버튼 저장(배열)
    Integer[] nBtnIDs= { //번호버튼 ID 저장
            R.id.btnN0, R.id.btnN1, R.id.btnN2, R.id.btnN3, R.id.btnN4,
            R.id.btnN5, R.id.btnN6, R.id.btnN7, R.id.btnN8, R.id.btnN9
    };
    int i; //for 반복문변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_05);


        setTitle("테이블레이아웃 계산기");
        edit1= findViewById(R.id.Edit1);
        edit2= findViewById(R.id.Edit2);
        Add= findViewById(R.id.Add);
        Sub= findViewById(R.id.Sub);
        Mul= findViewById(R.id.Mul);
        Div= findViewById(R.id.Div);
        tResult= findViewById(R.id.textResult);

        Add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                n1= edit1.getText().toString();
                n2= edit2.getText().toString();
                result= Integer.parseInt(n1)+Integer.parseInt(n2);
                tResult.setText("계산결과 :"+result);
                return false;
            }
        });

        Sub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                n1= edit1.getText().toString();
                n2= edit2.getText().toString();
                result= Integer.parseInt(n1)-Integer.parseInt(n2);
                tResult.setText("계산결과 :"+result);
                return false;
            }
        });

        Mul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                n1= edit1.getText().toString();
                n2= edit2.getText().toString();
                result= Integer.parseInt(n1)*Integer.parseInt(n2);
                tResult.setText("계산결과 :"+result);
                return false;
            }
        });


        Div.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                n1= edit1.getText().toString();
                n2= edit2.getText().toString();
                dresult= Double.parseDouble(n1)/Double.parseDouble(n2);
                dresult = (int)(dresult*100)/100.0;
                tResult.setText("계산결과 :"+dresult);
                return false;
            }
        });

        //10개의 버튼이벤트를 한개씩 설정하기보다 배열을 사용하여 이벤트를 설정
        for(i=0;i<nBtnIDs.length;i++){
            nBtn[i]= findViewById(nBtnIDs[i]); //숫자버튼의 id값을 숫자버튼을 저장하는 배열에 넣는다
        }

        for(i=0;i<nBtnIDs.length;i++){
            final int index; //초기값을 지정하면 최종값으로 결정되기때문에 값을 변경할수없다-> 반복문이 실행되는동안 값을 변경하지못하도록 설정
            index= i;

            nBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edit1.isFocused()==true){ //edit1 선택될때 클릭한 번호버튼의 값을 edit 에 넣는다
                        n1= edit1.getText().toString()+nBtn[index].getText().toString(); //기존입력된 숫자와 선택된숫자를 더한다
                        edit1.setText(n1);
                    }else if(edit2.isFocused()==true){ //edit1 선택될때 클릭한 번호버튼의 값을 edit 에 넣는다
                        n2= edit2.getText().toString()+nBtn[index].getText().toString();
                        edit2.setText(n2);
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show(); //Toast 메세지 표시안됨
                    }

                }
            });
        }
        /*

        //바인딩
        int btnIdArr[]= {R.id.BtnNum0~BtnNum9}; //반복문 사용할 int 배열(버튼 id 배열 사용)

        Button btnArr[]= new Button[btnIdArr.length] //객체배열
        for(int i=0; i<btnArr.length; ++i){
            btnArr[i]= findViewById(btnIdArr[i]);
        }

        반복문 사용
        for(int i=0; i<btnArr.length; ++i){
            int finalI= i; //반복문 for 와 setOnClickListener 는 다른영역이기때문에 상수로 사용한다
            btnArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(edt1.isFocused())
                    {
                        edt1.setText(edt1.getText().toString()+ finalI);   //기존값+ 입력된 버튼 번호의 값(0~9)
                    }else if(edt2.isFocused())
                    {
                        edt2.setText(edt2.getText().toString()+ finalI);
                    }
                }
            });
        }



         */
    }
}