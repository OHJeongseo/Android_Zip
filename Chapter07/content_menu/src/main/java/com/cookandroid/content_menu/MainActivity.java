package com.cookandroid.content_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn01, btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch07_7);

        /*ch07-7~ ch07-11 */
        setTitle("배경색 바꾸기(컨택스트 메뉴)");
        baseLayout= findViewById(R.id.baseLayout07);
        btn01= findViewById(R.id.btn001);
         //2.메뉴를 사용할 위젯 등록(btn01, btn02)
        registerForContextMenu(btn01);
        btn02= findViewById(R.id.btn002);
        registerForContextMenu(btn02);
    }
    /* AppCompatActivity 클래스로부터 오버라이딩 */
     //3. 메뉴 파일 등록, 위젯(버튼)에 따라 다르게 메뉴의 xml 파일을 인플레이트
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater= getMenuInflater();
         //btn01 롱 클릭(View v: 롱 클릭하는 위젯)
        if(v==btn01){
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu001, menu);
        }
        //btn02롱 클릭
        if(v==btn02){
            menuInflater.inflate(R.menu.menu002, menu);
        }
    }

    //4. 메뉴 선택시 동작할 내용, 메뉴항목에서 선택된 id(메뉴 xml 에서 설정한)에 따른 동작을 작성(switch()~ case 문 사용
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Red:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.Green:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.Blue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.Rotate:
                btn02.setRotation(45);
                return true;
            case R.id.Size:
                btn02.setScaleX(2);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    
    /*직접 풀어보기 7-2 java 코드로만 컨텍스트메뉴 작성  */
}