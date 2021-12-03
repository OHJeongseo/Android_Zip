package com.cookandroid.chapter07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn01;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_1);


        /*ch07-1~ ch07-5 옵션메뉴
        setTitle("배경색 바꾸기");
        baseLayout= findViewById(R.id.baseLayout);
        btn01= findViewById(R.id.btn01);
         */


        /*ch07-6 java 코드로 옵션메뉴, 레이아웃은 기존 코드를 사용한다*/
        setTitle("배경색 바꾸기");
        baseLayout= findViewById(R.id.baseLayout);
        btn01= findViewById(R.id.btn01);



    }

    /*AppCompatActivity 클래스의 오버라이딩 메서드
     //앱이 실행되면 메뉴의 내용을 xml 파일에서 자동으로 읽어온다, 단 화면에 보여줄 메뉴 xml 파일의 id를 설정
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
         //메뉴 인플레이터(xml 파일을 java 코드에 접근하여 실제 객체를 만든다) 생성, menu01.xml 파일등록
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu01, menu); //메뉴 xml 파일 id 설정
        return super.onCreateOptionsMenu(menu);
    }

     //메뉴를 선택했을때 어떤동작을 할것인지 설정, switch()~ case 문 사용
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){ //설정한 id가 선택될때 동작할 값을 설정한다
            case R.id.iRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.iGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.iBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.sRotate:
                btn01.setRotation(45);
                return true;
            case R.id.sSize:
                btn01.setScaleX(2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    } */

    /*ch07-06 java 코드만 사용한 옵션 메뉴 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
         //메뉴.xml 파일에 접근하는 대신 menu.add()메서드로 메뉴항목을 추가
        menu.add(0, 1, 0,"배경(빨강)"); //그룹 id, 항목 id, 순번, 제목
        menu.add(0, 2, 0,"배경(초록)");
        menu.add(0, 3, 0,"배경(파랑)");
        
         //서브메뉴: SubMenu 클래스를 사용하여 만든다
        SubMenu subMenu= menu.addSubMenu("버튼 변경 >>");
        subMenu.add(0, 4, 0,"버튼 45도 회전");
        subMenu.add(0, 5, 0,"버튼 2배 확대");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 4:
                btn01.setRotation(45);
                return true;
            case 5:
                btn01.setScaleX(2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}