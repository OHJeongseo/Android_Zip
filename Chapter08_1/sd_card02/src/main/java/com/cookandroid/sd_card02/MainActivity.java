package com.cookandroid.sd_card02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch08_14);


        /*ch08-15 특정 폴더의 하위 폴더 및 파일목록(ch08-14.xml 연결)
        //지정한 폴더의 하위폴더 및 파일목록에 접근하려면 File.listFiles()를 사용(반환값:File[])한다  */
        Button bFile; EditText eFile;
        bFile= findViewById(R.id.btnFileList);
        eFile= findViewById(R.id.editFileList);

        bFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //안드로이드 시스템 폴더의 경로를 가져와서 저장한다
                String sysDir= Environment.getRootDirectory().getAbsolutePath(); 
                //시스템 폴더의 폴더 및 파일 목록을 구하여 파일배열 변수에 넣는다
                File[] sysFiles= (new File(sysDir).listFiles());

                String strFileName; //파일이름을 저장할 변수 선언
                for(int i=0; i<sysFiles.length; i++){ //시스템 폴더의 폴더및 파일의 개수만큼 반복한다
                    if(sysFiles[i].isDirectory()== true) //현재 파일이 폴더일 경우에 파일이름변수에 <폴더>+파일이름을 붙여서 넣는다
                        strFileName= "<폴더>"+sysFiles[i].toString();
                    else //폴더가 아니면  <파일>+파일이름을 붙여서 넣는다
                        strFileName= "<파일>"+sysFiles[i].toString();

                    eFile.setText(eFile.getText()+"\n"+strFileName); //폴더 및 파일목록을 에디트텍스트에  이어서 출력
                }
            }
        });



        /* SD 카드에 폴더 및 파일 생성 (ch08_12.xml 연결)
        //엑서스 허용여부 확인
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        Button bCreate, bDelete;
        bCreate= findViewById(R.id.btnCreate);
        bDelete= findViewById(R.id.btnDelete);
        //sd 카드 경로를 가져와서 저장한다
        final String strSDPath= Environment.getExternalStorageDirectory().getAbsolutePath();
        //sd 카드 경로 아래에 새로 만들 폴더를 경로와 이름을 같이 설정해서 File 형 변수 에 넣는다
        final File mDir= new File(strSDPath+"/mDir");

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDir.mkdir();
            } //디렉토리 생성
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDir.delete();
            } //디렉토리 삭제 
        });
        //결과는 새로고침해야 실행되는것을 확인할수있다
        */




    }
}