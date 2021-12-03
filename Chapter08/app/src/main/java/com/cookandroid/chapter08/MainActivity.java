package com.cookandroid.chapter08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    /*내장 메모리 파일 처리 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch08_1);

        /*ch8-2(ch08-1.xml)과 연결 */
        Button bRead, bWrite;
        bRead= findViewById(R.id.btnRead);
        bWrite= findViewById(R.id.btnWrite);

        bWrite.setOnClickListener(new View.OnClickListener() { //내장메모리 파일쓰기 버튼 이벤트
            @Override
            public void onClick(View v) {
                try{ //파일처리에 대한 예외처리
                    //내장메모리에 파일을 쓴다 (경로: /data/data/패키지명 'com.cookandroid.파일이름'/files/file.txt)
                    FileOutputStream out= openFileOutput("file.txt", Context.MODE_PRIVATE); //file.txt 로 파일을 쓰기모드(MODE_PRIVATE)로 연다
                    String str= "쿡북 안드로이드"; //파일에 쓸 문자열을 작성
                    out.write(str.getBytes()); //파일에 작성할 문자열을 byte[]형으로 변경(getByte())하여 넣는다
                    out.close(); //파일 닫기
                    Toast.makeText(getApplicationContext(), "file.txt 생성 완료", Toast.LENGTH_SHORT).show();
                }catch (IOException e) {}
            }
        });

        bRead.setOnClickListener(new View.OnClickListener() { //내장메모리 파일읽기 버튼 이벤트
            @Override
            public void onClick(View v) {
                try{ //파일처리에 대한 예외처리 
                    //내장메모리에서 파일을 읽어 토스트 메세지로 보여준다 (경로: /data/data/패키지명/files/file.txt)
                    FileInputStream in= openFileInput("file.txt"); //내장메모리에 있는 file.txt 을 읽어온다
                    //읽어온 데이터를 byte[]형 변수 'txt' 에 저장한다
                    byte[] txt= new byte[30];
                    in.read(txt);
                    String str= new String(txt); //byte[]형 txt 를 문자열로 변환
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show(); //문자열(읽어온 데이터)를 토스트로 출력
                    in.close(); //파일 닫기
                }catch (IOException e){ //파일이 없을때 출력할 토스트메세지를 설정한다
                    Toast.makeText(getApplicationContext(), "파일 없음", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}