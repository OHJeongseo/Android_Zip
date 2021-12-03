package com.cookandroid.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
/*어뎁터.스피너: pc의 드롭다운 박스와 비슷한 기능, 여러개 중 하나를 선택할수있는 기능 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*스피너 ch11-17 */
        setTitle("스피너 테스트");
        //스피너에 넣을 문자열 배열 선언
        final String[] movie={
                "써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드",
                "웰컴투동막골","헬보이","백투더퓨처","여인의향기","쥬라기공원","포레스트검프","Groundhog Day",
                "혹성탈출","아름다운비행","내이름은 칸","해리포터","마더","킹콩을들다","쿵후팬더",
                "짱구는 못말려","아저씨","아바타"
        };

        //이미지뷰에서 출력할 영화포스터 id 를 저장하는 배열 선언
        Integer[] posterID= {
                R.drawable.mov01,R.drawable.mov02,R.drawable.mov03,R.drawable.mov04,
                R.drawable.mov05,R.drawable.mov06,R.drawable.mov07,R.drawable.mov08,
                R.drawable.mov09,R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,R.drawable.mov16,
                R.drawable.mov17,R.drawable.mov18,R.drawable.mov19,R.drawable.mov20,
                R.drawable.mov21,R.drawable.mov22,R.drawable.mov23,R.drawable.mov24
        };

        //xml 에서 사용한 스피너 바인딩
        Spinner spinner= findViewById(R.id.spinner01);

        //어레이 어뎁터 변수선언
        ArrayAdapter<String> adapter;
        //어레이 어뎁터 생성(엑티비티, 한줄디자인(스피너 형식으로 설정), 데이터(스피너에 넣는 문자열 배열)
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        //생성된 어레이 어뎁터를 스피너와 연결
        spinner.setAdapter(adapter);

        //스피너 이벤트 정의
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //이미지 뷰 바인딩(엑티비티 사용하는)
                ImageView imageView= findViewById(R.id.imageView);
                //이미지뷰 중앙배치
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                //이미지뷰 간격
                imageView.setPadding(10,10,10,10);
                //선택한 항목과 일치하는 영화포스터를 이미지 뷰에 넣는다, movie 배열과 posterID 배열의 크기는 같다(제목과 포스터가 일치하도록)
                imageView.setImageResource(posterID[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

