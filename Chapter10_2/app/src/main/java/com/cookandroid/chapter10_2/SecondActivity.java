package com.cookandroid.chapter10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
 /* 양방향 엑티비티_ 직접 풀어보기 10-3 */
    int result_value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        //MainActivity 에서 넘긴 데이터를 가져온다
        setTitle("계산 엑티비티");
        Intent in_intent= getIntent();
        final int Result= in_intent.getIntExtra("Result",0);
        switch (Result){ //가져온 Result 값에 따라 다르게 계산하여 결과값 변수(result_value)에 넣고
            case 0:
                result_value=  in_intent.getIntExtra("num1",0)+
                        in_intent.getIntExtra("num2",0);
                break;
            case 1:
                result_value=  in_intent.getIntExtra("num1",0)-
                        in_intent.getIntExtra("num2",0);
                break;
            case 2:
                result_value=  in_intent.getIntExtra("num1",0)*
                        in_intent.getIntExtra("num2",0);
                break;
            case 3:
                result_value=  in_intent.getIntExtra("num1",0)/
                        in_intent.getIntExtra("num2",0);
                break;
        }


        Button bReturn= findViewById(R.id.btnReturn);
        bReturn.setOnClickListener(new View.OnClickListener() { //돌아가기 버튼 클릭
            @Override
            public void onClick(View v) {
                //인텍트 생성, (MainActivity)계산한 데이터 값를 넣는다
                Intent Out_intent= new Intent(getApplicationContext(),MainActivity.class);
                Out_intent.putExtra("Hap", result_value);
                //계산한 값을 MainActivity 에 다시 돌려준다
                setResult(RESULT_OK, Out_intent);
                finish();
            }
        });
    }
}