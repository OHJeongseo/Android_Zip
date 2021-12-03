package com.cookandroid.chapter07_02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
    /* 실습 7-3 사용자 정보 입력 앱 만들기
    TextView name, email, toastText;
    Button btn01;
    EditText dlgName, dlgEmail;
    View dialog01, toast01; */

    /*직접 풀어보기 7-3 */
    TextView toastText;
    Button btn01;
    EditText dlgName, dlgEmail, name, email;
    View dialog01, toast01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_03);

        /*직접 풀어보기 7-3 */
         //타이틀바 이름, 아이콘을 넣느다
        setTitle("사용자 입력 정보(수정)");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.q10);

        name= findViewById(R.id.nameEdit);
        email= findViewById(R.id.emailEdit);
        btn01= findViewById(R.id.btn01);


        /* 실습 7-3 사용자 정보 입력 앱 만들기
        name= findViewById(R.id.nameText);
        email= findViewById(R.id.emailText);
        btn01= findViewById(R.id.btn01);

        //타이틀바 설정(제목,아이콘)
        setTitle("사용자 입력 정보");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_menu_allfriends);

        //버튼 이벤트
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog01.xml 파일을 dialog01 변수에 대입(인플레이트)한다
                dialog01= View.inflate(MainActivity.this, R.layout.dialog01, null);
                //대화상자를 생성하고 설정한다                
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialog01); //인플레이트한 뷰(dialog01)를 대화상자로 사용한다-> xml 를 대화상자로 사용한다
                //dlg.setPositiveButton("확인",null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {  //확인버튼 
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog01 안에 있는 에디터텍스트(id)를 바인딩한다
                        dlgName= dialog01.findViewById(R.id.dlgEdt01);
                        dlgEmail= dialog01.findViewById(R.id.dlgEdt02);
                        
                        //입력한 값을 메인화면(ch07_21)의 name,email 에 넣는다
                        name.setText(dlgName.getText().toString());
                        email.setText(dlgEmail.getText().toString());
                    }
                });
                //dlg.setNegativeButton("취소",null);
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() { //취소버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         //토스트 생성
                        Toast toast= new Toast(MainActivity.this);
                        //toast01.xml 파일을 toast01 변수에 대입(인플레이트)한다
                        toast01= View.inflate(MainActivity.this, R.layout.toast01, null);
                         //toast01 안에 있는 텍스트뷰를 바인딩하고 값을 설정한다
                        toastText= (TextView) toast01.findViewById(R.id.toastTextView);
                        toastText.setText("취소했습니다");
                         //인플레이트한 뷰(toast01)를 토스트로 사용한다-> xml 를 토스트로 사용한다
                        toast.setView(toast01);
                         //화면에 토스를 출력한다
                        toast.show();
                    }
                });
                dlg.show();
            }
        });

         */

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog01.xml 파일을 dialog01 변수에 대입(인플레이트)한다
                dialog01= View.inflate(MainActivity.this, R.layout.dialog01, null);
                //대화상자를 생성하고 설정한다
                AlertDialog.Builder dlg= new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialog01); //인플레이트한 뷰(dialog01)를 대화상자로 사용한다-> xml 를 대화상자로 사용한다
                //dlg.setPositiveButton("확인",null);

                //dialog01에 있는 에디터텍스트(id)를 바인딩한다
                dlgName= dialog01.findViewById(R.id.dlgEdt01);
                dlgEmail= dialog01.findViewById(R.id.dlgEdt02);
                //메인화면(ch07_03)에 있는 name,email(에디터텍스트) 에 입력된 값을 넣는다 , onClick 메소드안에 작성하면 값이 입력되지않는다
                dlgName.setText(name.getText().toString());
                dlgEmail.setText(email.getText().toString());
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {  //확인버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //입력(수정)한 값을 메인화면(ch07_21)의 name,email 에  다시 넣는다
                        name.setText(dlgName.getText().toString());
                        email.setText(dlgEmail.getText().toString());
                    }
                });
                //dlg.setNegativeButton("취소",null);
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() { //취소버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //토스트 생성
                        Toast toast= new Toast(MainActivity.this);
                        //toast01.xml 파일을 toast01 변수에 대입(인플레이트)한다
                        toast01= View.inflate(MainActivity.this, R.layout.toast01, null);
                        //toast01 안에 있는 텍스트뷰를 바인딩하고 값을 설정한다
                        toastText= (TextView) toast01.findViewById(R.id.toastTextView);
                         //토스트메세지 위치 랜덤설정
                        Display display= ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset= (int)(Math.random()*display.getWidth());
                        int yOffset= (int)(Math.random()*display.getHeight());
                        toastText.setText("취소했습니다");
                        //인플레이트한 뷰(toast01)를 토스트로 사용한다-> xml 를 토스트로 사용한다
                        toast.setView(toast01);
                        toast.setGravity(Gravity.TOP|Gravity.LEFT, xOffset, yOffset); //랜덤 설정사용
                        //화면에 토스를 출력한다
                        toast.show();
                    }
                });
                dlg.show();
            }
        });

    }
}