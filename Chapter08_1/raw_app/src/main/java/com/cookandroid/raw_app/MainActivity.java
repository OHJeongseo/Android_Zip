package com.cookandroid.raw_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
/*raw 폴더파일 처리
/res/raw 는 프로젝트에 포함된 폴더로 읽기 전용으로만 사용가능하다*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch08_8);

        Button bRead;
        final EditText edit01;
        bRead= findViewById(R.id.btnRead);
        edit01= findViewById(R.id.editText);

        //"버튼을 클릭하면 res/raw/raw_test.txt 파일에 있는 내용을 가져와서 에디터텍스트에 보여준다"
        bRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    //Context.getResources().openRawResource() -> 현재 패키지의 리소스 반환하는데 /res/rew 파일을 읽기용을 열어서 반환
                    InputStream in= getResources().openRawResource(R.raw.raw_test);
                    byte[] txt= new byte[in.available()]; //available()-> 입력스트림에서 읽을수있는 바이트수 반환, 파일의 크기만큼 설정
                    in.read(txt); //데이터를 txt 변수에 넣고
                    edit01.setText(new String(txt)); //문자열로 변환하여 에디터텍스트에 출력하고
                    in.close(); //파일 닫기
                }catch (IOException e){}
            }
        });

    }
}