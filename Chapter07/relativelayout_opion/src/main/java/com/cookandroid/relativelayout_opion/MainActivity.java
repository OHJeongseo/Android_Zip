package com.cookandroid.relativelayout_opion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    ImageView imageView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*직접 풀어보기  7-1 */
        setTitle("제주도 풍경");
        imageView= findViewById(R.id.imageV);
        editText= findViewById(R.id.edit01);
         //타이틀바 이미지(아이콘) 넣기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
    }

    /*직접 풀어보기 7-1 */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //메뉴 인플레이터(xml 파일을 java 코드에 접근하여 실제 객체를 만든다) 생성, menu01.xml 파일등록
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu003, menu); //메뉴 xml 파일 id 설정
        return super.onCreateOptionsMenu(menu);
    }

    int fAngle; //회전값을 저장할 변수 선언
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.iRotation:
                //editText의 값을 가져와서 변수(전역변수)'fAngle'에 값을 누적시키면서 회전을 누를때마다 누적된값으로 회전하도록 설정한다
                String a = editText.getText().toString();
                fAngle+= Integer.parseInt(a);
                imageView.setRotation(fAngle);
                return true;
            case R.id.i1:
                imageView.setImageResource(R.drawable.jeju7);
                item.setChecked(true);
                return true;
            case R.id.i2:
                imageView.setImageResource(R.drawable.jeju9);
                item.setChecked(true);
                return true;
            case R.id.i3:
                imageView.setImageResource(R.drawable.jeju11);
                item.setChecked(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}