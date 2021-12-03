package com.cookandroid.chapter12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
/*Android SQLite */
    myDBHelper myDBHelper;
    EditText eName, eNumber, eNameResult, eNumberResult;
    Button bInit, bInsert, bSelect, bUpdate, bDelete;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*실습 12-2 가수 그룹 관리 DB 앱 만들기*/
        setTitle("가수 그룹 관리 DB");

        eName= findViewById(R.id.editName);
        eNumber= findViewById(R.id.editNumber);
        eNameResult= findViewById(R.id.nameResult);
        eNumberResult= findViewById(R.id.numberResult);

        bInit= findViewById(R.id.btnInit);
        bInsert= findViewById(R.id.btnInsert);
        bUpdate= findViewById(R.id.btnUpdate);
        bDelete= findViewById(R.id.btnDelete);
        bSelect= findViewById(R.id.btnSelect);

        myDBHelper= new myDBHelper(this); //객체생성-> groupDB 파일 생성(myDBHelper() 생성자 실행되었다는 의미)

        bInit.setOnClickListener(new View.OnClickListener() { //초기화버튼
            @Override
            public void onClick(View v) {
                sqlDB= myDBHelper.getWritableDatabase(); //getWritableDatabase() 쓰기 전용으로 데이터베이스 오픈
                myDBHelper.onUpgrade(sqlDB, 1, 2); //기존테이블이 있으면 지우고 새로 생성
                sqlDB.close(); //데이터베이스 닫기
            }
        });

        bInsert.setOnClickListener(new View.OnClickListener() { //입력 버튼 클릭
            @Override
            public void onClick(View v) {
                sqlDB= myDBHelper.getWritableDatabase(); 
                //입력 SQL 문을 작성하여 실행
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('" +
                       eName.getText().toString()+ "'," +
                       eNumber.getText().toString()+");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show(); //결과 토스트메세지로 출력
                bSelect.callOnClick(); //입력결과 즉시확인(->조회버튼)
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() { //수정버튼 클릭
            @Override
            public void onClick(View v) {
                sqlDB= myDBHelper.getWritableDatabase(); 
                //수정 SQL 문을 작성하여 실행
                sqlDB.execSQL("UPDATE groupTBL SET gNumber="+
                        eNumber.getText().toString()+" WHERE gName='" +
                        eName.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show(); //결과 토스트메세지로 출력
                bSelect.callOnClick(); //수정결과 즉시확인
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {  //삭제버튼 클릭
            @Override
            public void onClick(View v) {
                sqlDB= myDBHelper.getWritableDatabase();
                //삭제 SQL 문을 작성하여 실행
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName='" +
                        eName.getText().toString()+"';");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();
                bSelect.callOnClick(); //삭제결과 즉시확인
            }
        });

        bSelect.setOnClickListener(new View.OnClickListener() { //조회버튼 클릭
            // (db의 테이블안의 데이터를 커서를  사용하여 가져와서 가공하여 출력한다)
            @Override
            public void onClick(View v) {
                sqlDB= myDBHelper.getReadableDatabase(); //getReadableDatabase() 읽기전용으로 데이터베이스 오픈
                Cursor cursor; //커서 생성
                cursor= sqlDB.rawQuery("SELECT * FROM groupTBL;",null); //모든 데이터를 조회한후 커서에 넣는다

                //데이터를 보여주고 전에 넣을 헤더와 데이터를 저장할 문자열 변수 선언
                String sNames= "그룹 이름"+"\r\n"+"-------------"+"\r\n";
                String sNumber= "인원"+"\r\n"+"-------------"+"\r\n";

                //커서에 데이터가 있을때까지 반복하고 입력된 데이터는 칼럼의 위치에 맞게 문자열 변수에 넣고
                while (cursor.moveToNext()){ //moveToNext() 커서변수의 다음 행으로 넘어가게 한다
                    sNames+= cursor.getString(0)+"\r\n";
                    sNumber+= cursor.getString(1)+"\r\n";
                }

                //저장한 문자열 변수를 에디터 텍스트에 넣는다(결과를 출력)
                eNameResult.setText(sNames);
                eNumberResult.setText(sNumber);

                //커서와 데이터베이스 닫는다
                cursor.close();
                sqlDB.close();
            }
        });



    }

    //SQLiteOpenHelper 를 상속받아서 myDBHelper 를 정의한다
    public class myDBHelper extends SQLiteOpenHelper{
        //생성자 정의(첫번째 엑티비티, 두번째 '새로생성될 데이터베이스의 파일명' 마지막 파라미터 '데이터베이스 버전'
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        //테이블 생성 SQL(execSQL->SQL 문 실행(insert/Update/Delete))
        // onUpgrade()호출되거나 데이터를 입력닿때 혹은 테이블이 없을때 처음 한번 호출된다
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        //테이블을 삭제하고 새로 생성한다-> 초기화할때 호출된다
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}