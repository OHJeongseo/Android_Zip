package com.example.chapter05;

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
    /*ch05_14~17 */
    EditText edit1, edit2;
    Button Add, Sub, Mul, Div;
    TextView tResult;
    String n1, n2;
    Integer result;
    Button[] nBtn= new Button[10]; //번호버튼 저장(배열)
    Integer[] nBtnIDs= { //번호버튼 ID 저장
            R.id.btnN0, R.id.btnN1, R.id.btnN2, R.id.btnN3, R.id.btnN4,
            R.id.btnN5, R.id.btnN6, R.id.btnN7, R.id.btnN8, R.id.btnN9
    };
    int i; //for 반복문변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch05_04_2);

        /*java 코드로 layout 작성  ch05_8~9
        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams( //옵션
          LinearLayout.LayoutParams.MATCH_PARENT, //layout_width= "match_parent"
                LinearLayout.LayoutParams.MATCH_PARENT //layout_height= "match_parent"
        );
        LinearLayout baseLayout= new LinearLayout(this); //리니어레이아웃 생성, 엑티비티 설정->this
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(0,255,0));
        setContentView(baseLayout,params); //레이아웃이름(생성된), 초기옵션

        //버튼생성->글자설정(옵션)->색상설정(옵션)->레이아웃(생성된) 추가
        Button btn= new Button(this); //엑티비티 설정->this
        btn.setText("버튼입니다");
        btn.setBackgroundColor(Color.MAGENTA);
        baseLayout.addView(btn);

        //버튼(생성된 버튼의) 이벤트 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "코드로 생성한 버튼입니다", Toast.LENGTH_SHORT).show();
            }
        }); */

        /*ch05_14~17
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
                result= Integer.parseInt(n1)/Integer.parseInt(n2);
                tResult.setText("계산결과 :"+result);
                return false;
            }
        });

        //10개의 버튼이벤트를 한개씩 설정하기보다 배열을 사용하여 이벤트를 설정
        for(i=0;i<nBtnIDs.length;i++){
            nBtn[i]= findViewById(nBtnIDs[i]); //숫자버튼의 id값을 숫자버튼을 저장하는 배열에 넣는다
        }

        for(i=0;i<nBtnIDs.length;i++){
            final int index; //초기값을 지정하면 최종값으로 결정되기때문에 값을 변경할수없다
            index= i;

            nBtn[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edit1.isFocused()==true){ //edit1 선택될때 클릭한 번호버튼의 값을 edit 에 넣는다
                        n1= edit1.getText().toString()+nBtn[index].getText().toString();
                        edit1.setText(n1);
                    }else if(edit2.isFocused()==true){ //edit1 선택될때 클릭한 번호버튼의 값을 edit 에 넣는다
                        n2= edit2.getText().toString()+nBtn[index].getText().toString();
                        edit2.setText(n2);
                    }else{
                        Toast.makeText(getApplicationContext(),
                                "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show(); //Toast 메세지 버그발생
                    }

                }
            });
        }

         */

        /*직접 풀어보기 5-3
        //java 코드로 작성
        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams( //옵션
          LinearLayout.LayoutParams.MATCH_PARENT, //layout_width= "match_parent"
                LinearLayout.LayoutParams.MATCH_PARENT //layout_height= "match_parent"
        );
        LinearLayout baseLayout= new LinearLayout(this); //리니어레이아웃 생성, 엑티비티 설정->this
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        baseLayout.setBackgroundColor(Color.rgb(0,255,0));
        setContentView(baseLayout,params); //레이아웃이름(생성된), 초기옵션

        //에디트 텍스트 생성-> 옵션설정
        EditText edit= new EditText(this); //엑티비티 설정->this
        edit.setHint("입력");
        edit.setTextSize(30);
        edit.setBackgroundColor(Color.rgb(255,255,255));
        baseLayout.addView(edit);


        //버튼생성->글자설정(옵션)->색상설정(옵션)->레이아웃(생성된) 추가
        Button btn= new Button(this); //엑티비티 설정->this
        btn.setText("버튼입니다");
        btn.setBackgroundColor(Color.MAGENTA);
        baseLayout.addView(btn);

        //텍스트뷰 생성-> 옵션설정
        TextView text= new TextView(this); //엑티비티 설정->this
        text.setText("입력한 결과");
        text.setTextSize(30);
        text.setBackgroundColor(Color.rgb(255,255,255));
        baseLayout.addView(text);

        //버튼(생성된 버튼의) 이벤트 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"코드로 생성한 버튼입니다", Toast.LENGTH_SHORT).show();
                text.setText(edit.getText().toString());
            }
        });
        */
    }
}