package com.cookandroid.chapter08_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
/*실습 8-1 간단 일기장 앱 만들기 */
    DatePicker dPicker;
    EditText editText;
    Button bWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch08_10);

        /*SD 카드에서 파일 읽기 (ch08_10.xml 과 연결) build.gradle 에서  targetSdk 29로 설정한다*/
        Button bRead;
        final EditText edit01;
        bRead= findViewById(R.id.btnRead);
        edit01= findViewById(R.id.editSD);
        
         //앱에 파일엑시스 작업을 허용여부 확인
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        bRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                   //SD 카드의 절대경로에 있는 sd_text.txt 파일을  가져와서 byte[]로 읽고 읽은 값을 문자열로 변화해서 에디터에 출력한다 
                  FileInputStream in= new FileInputStream("/storage/emulated/0/sd_test2.txt");
                  byte[] txt= new byte[in.available()];
                  in.read(txt);
                  edit01.setText(new String(txt));
                  in.close(); //출력하고 파일 닫기
                }catch (IOException e){}
            }
        });

        /*실습 8-1 간단 일기장 앱 만들기
        //타이틀바 이름, 아이콘을 넣느다
        setTitle("간단 일기장");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.q10);

        dPicker= findViewById(R.id.datePicker01);
        editText= findViewById(R.id.edit01);
        bWrite= findViewById(R.id.btnWrite);

        //Calendar 를 사용하여 현재 날짜의 연,월,일을 구하여 변수에 저장
        Calendar cal= Calendar.getInstance();
        int cYear= cal.get(Calendar.YEAR);
        int cMonth= cal.get(Calendar.MONTH);
        int cDay= cal.get(Calendar.DAY_OF_MONTH);

        // * 직접 풀어보기 8-1 처음실행했을때 해당날짜의 일기파일이 있으면 해당 날짜의 내용을 보여주도록 설정한다
         //오늘날짜 확인하고 파일이름에 넣는다
        fileName= Integer.toString(cYear)+"_"+Integer.toString(cMonth+1)+
                "_"+Integer.toString(cDay)+".txt";
         //오늘날짜의 파일여부 확인하고 
        String str= readDiary(fileName);
         //읽어온 파일내용을 에디트 텍스트에 출력한다
        editText.setText(str);

        
        //데이트파커 초기화(파라미터 값:연, 월, 일, 리스너)하고-> "파일이름을 설정(날짜)하고 파일이 있으면 파일내용를 출력한다"
        dPicker.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) { //데이터파커의 값이 변경되면
                //현재 날짜에 해당하는 파일이름을 만든다
                fileName= Integer.toString(year)+"_"+Integer.toString(monthOfYear+1)+
                        "_"+Integer.toString(dayOfMonth)+".txt";
                //현재날짜의 일기파일을 가져온다(readDiary-> 파일여부 확인하고 있으면 파일을 반환해준다)
                String str= readDiary(fileName);
                //읽어온 파일내용을 에디트 텍스트에 출력한다
                editText.setText(str);
                bWrite.setEnabled(true); //버튼을 클릭할수있도록 설정(readDiary)에서 설정한 이름으로 변경된다
            }
        });

        //"파일여부(존재)에 따라 이름이 변경,파일을 가져와서(또는 새로만들어서) 파일내용을 쓴다"
        bWrite.setOnClickListener(new View.OnClickListener() { //수정,새로 저장(파일여부에 따라 이름이 변경)->readDiary() 정의
            @Override
            public void onClick(View v) {
                try{
                    //파일(일기파일)을 쓰기모드로 연다
                    FileOutputStream out= openFileOutput(fileName, Context.MODE_PRIVATE);
                    String str= editText.getText().toString(); //에디트텍스트 값을 저장
                    out.write(str.getBytes()); //저장한 문자열을 byte[]로 변환하여 파일에 넣는다
                    out.close(); //파일 닫기
                    //파일이 정상적으로 작동되었다는것을  토스트메세지를 출력한다
                    Toast.makeText(MainActivity.this, fileName+" 저장", Toast.LENGTH_SHORT).show();
                }catch (IOException e){ }
            }
        });  */

    }


    /* 실습 8-1 간단 일기장 앱 만들기
    //일기파일을 파라미터로 받늗다-> "파일을 읽어와서 파일내용을 반환하는 메소드"
    String readDiary(String fileName){
        String diaryStr= null; //읽어온 일기파일을 저장할 문자열변수 선언
        FileInputStream in; //일기파일을 읽기위한 스트림변수 선언
        try{
            in= openFileInput(fileName); //파라미터로 받은 일기파일을 가져와서 저장한다,없다면 88행을 실행한다
            //파일내용을 저장할 byte[] 변수를 선언
            byte[] txt= new byte[500];
            //파일내용을 byte[]변수로 읽어들인후 파일을 닫는다
            in.read(txt);
            in.close();
            //읽어온 txt 를 문자열로 변경하고 공백을 제거 반환할 문자열변수에 저장한다
            diaryStr= (new String(txt)).trim();
            bWrite.setText("수정하기"); //버튼 이름을 변경한다
            bWrite.setEnabled(true);
        }catch (IOException e){ //파일이 없으면  에디터텍스트에 파일이 없음을 표시하고 버튼이름을 변경한다
            editText.setHint("일기 없음");
            bWrite.setText("새로 저장");
            bWrite.setEnabled(false);
        }

        return diaryStr; //저장한 문자열을 반환한다 , 50행 str 변수에 저장된다
    }

     */

}