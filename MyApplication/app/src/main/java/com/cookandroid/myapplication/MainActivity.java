package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
/*랜덤 구구단 앱 만들기 */
    EditText num1, num2, eResult;
    Button bRandom, bResult;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*랜덤 구구단 앱 만들기 */
        setTitle("랜덤 구구단앱");
        num1= findViewById(R.id.edit01);
        num2= findViewById(R.id.edit02);
        eResult= findViewById(R.id.editResult);

        bRandom= findViewById(R.id.btnRandom);
        bResult= findViewById(R.id.btnResult);

        listView= findViewById(R.id.listView);

        bRandom.setOnClickListener(new View.OnClickListener() { //랜덤숫자를 가져와서 넣는다
            @Override
            public void onClick(View v) {
                int random1= new Random().nextInt(8)+2;
                int random2= new Random().nextInt(8)+2;

                num1.setText(String.valueOf(random1));
                num2.setText(String.valueOf(random2));
            }
        });

        bResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //정답
                int result= Integer.parseInt(num1.getText().toString())*
                            Integer.parseInt(num2.getText().toString());
                //입력 값
                int gugudan= Integer.parseInt(eResult.getText().toString());

                if(result== gugudan){ //입력값과 정답값을 확인
                    Toast.makeText(getApplicationContext(),"정답입니다", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"오답입니다", Toast.LENGTH_SHORT).show();
                    
                    //오답시 입력한 값의 구구단을 리스트뷰에 출력
                    String[] value= new String[9];
                    //구구단을 구하여 문자열 배열에 넣는다
                    for(int i=0;i<value.length;i++){
                        value[i]= Integer.parseInt(num1.getText().toString())+" x "+
                                (i+1)+"= "+Integer.parseInt(num1.getText().toString())*(i+1);
                    }

                    //문자열배열을 어레이어뎁터에 넣고 리스트뷰와 연결한다
                    ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_list_item_1, value);
                    listView.setAdapter(adapter);
                }


            }
        });
        
    }
}