package com.cookandroid.chapter08_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
/*실습 8-2 간단 이미지 뷰어 만들기 , "SD 카드에 있는 특정파일(Pictures)에 있는 이미지파일을 가져와서 차례로 보여주는 앱" */
    Button bPrev, bNext;
    myPictureView mPicture; //커스텀 위젯 선언
    int current; //이미지파일의 순번으로 사용할 변수
    File[] imageFiles; //SD 카드에서 읽어올 이미지파일의 배열을 저장할 변수
    String imageFilesName; //SD 카드에서 읽어올 이미지파일의 파일명을 저장할 변수
    TextView tCurrent; //현재 그림번호의 위치를 알려주도록 표시할 변수를 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch08_02);

        /*실습 8-2 간단 이미지 뷰어 만들기
        setTitle("간단 이미지 뷰어");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_image001);

        //앱에 엑시스 작업을 허용여부 확인
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        bPrev= findViewById(R.id.btnPrev);
        bNext= findViewById(R.id.btnNext);
        mPicture= findViewById(R.id.myPictureView01); //커스텀 위젯 바인딩

        //SD 카드에서 파일을 읽어 listFiles()메서드로 배열을 만들어서 저장한다
        imageFiles= new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        +"/Pictures").listFiles();
        imageFilesName= imageFiles[0].toString(); //첫번째 파일의 이름을 추출하여
        mPicture.imagePath= imageFilesName; //mPicture(커스텀위젯을 바인딩한 변수) 클래스의 imagePath 에 전달

        bPrev.setOnClickListener(new View.OnClickListener() { //이전 그림 버튼
            @Override
            public void onClick(View v) {
                if(current<= 0){ //첫번째 그림에서 버튼을 클릭할때 토스트 메세지 출력
                    Toast.makeText(MainActivity.this, "첫번째 그림입니다", Toast.LENGTH_SHORT).show();
                }else{
                    //버튼 클릭 할때 1개씩 감소
                    current--; 
                    //현재 번호의 이미지파일을 mPicture.imagePath 에 전달
                    imageFilesName= imageFiles[current].toString(); 
                    mPicture.imagePath= imageFilesName;
                    mPicture.invalidate(); //mPicture 에 있는 onDraw()호출
                }
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() { //다음 그림 버튼
            @Override
            public void onClick(View v) {
                if(current>= imageFiles.length-2){ //마지막 그림에서 버튼을 클릭할때 토스트 메세지 출력
                    Toast.makeText(MainActivity.this, "마지막 그림입니다", Toast.LENGTH_SHORT).show();
                }else{
                    //버튼 클릭 할때 1개씩 증가
                    current++;
                    imageFilesName= imageFiles[current].toString();
                    mPicture.imagePath= imageFilesName;
                    mPicture.invalidate();
                }
            }
        });  */

        /*직접 풀어보기 8-2   */
        setTitle("간단 이미지 뷰어(수정)");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_image001);


        //앱에 엑시스 작업을 허용여부 확인
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        
        bPrev= findViewById(R.id.btnPrev);
        bNext= findViewById(R.id.btnNext);
        tCurrent= findViewById(R.id.textCurrent);
        mPicture= findViewById(R.id.myPictureView01); //커스텀 위젯 바인딩

        //SD 카드에서 파일(storage/emulated/0/Pictures)을 읽어 listFiles()메서드로 객체(파일)를 배열로 만들어서 저장한다
        imageFiles= new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/Pictures").listFiles();
        imageFilesName= imageFiles[0].toString(); //첫번째 파일의 이름을 추출하여
        mPicture.imagePath= imageFilesName; //mPicture(커스텀위젯을 바인딩한 변수) 클래스의 imagePath 에 전달-> 커스텀위젯에 그림이 출력된다

        tCurrent.setText(String.valueOf(current+1)+"/"+(imageFiles.length-1)); //그림번호표시, 배열은 0~ 시작되기때문에 +1를 더하고 표시되도록 설정

        bPrev.setOnClickListener(new View.OnClickListener() { //이전 그림 버튼
            @Override
            public void onClick(View v) {
                if(current<= 0){ //첫번째 그림에서 버튼을 클릭
                    current= imageFiles.length-2; //마지막그림파일 번호를 가져오고
                }else{  //버튼 클릭 할때 1개씩 감소
                    current--;
                }
                //현재 번호의 이미지파일을 mPicture.imagePath 에 전달
                imageFilesName= imageFiles[current].toString();
                mPicture.imagePath= imageFilesName;
                mPicture.invalidate(); //mPicture 에 있는 onDraw()호출, invalidate()-> 그림 무효화(새로고침)

                tCurrent.setText(String.valueOf(current+1)+"/"+(imageFiles.length-1)); //그림번호표시(현재 그림파일 배열+1)
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() { //다음 그림 버튼
            @Override
            public void onClick(View v) {
                if(current>= imageFiles.length-2){ //마지막 그림에서 버튼을 클릭할때 , -2(썸네일 그림을 포함하기때문에,표시되지않음)
                    current= 0; //마지막그림파일 번호를 가져오고
                }else{  //버튼 클릭 할때 1개씩 증가
                    current++;
                }
                imageFilesName= imageFiles[current].toString();
                mPicture.imagePath= imageFilesName;
                mPicture.invalidate();
                tCurrent.setText(String.valueOf(current+1)+"/"+(imageFiles.length-1)); //그림번호표시(현재 그림파일 배열+1)
            }
        });

    }
}