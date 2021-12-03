package com.cookandroid.chapter10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch10_16);

        /* 양방향 엑티비티 */
        setTitle("Second 엑티비티");
        //MainActivity 에서 넘긴 데이터를 가져와서 더한다
        Intent in_intent= getIntent();
        final int hav_value=  in_intent.getIntExtra("num1",0)+
                                in_intent.getIntExtra("num2",0); //0 디폴트 값(데이터가 오지않을 경우)
        
        Button bReturn= findViewById(R.id.btnReturn);
        bReturn.setOnClickListener(new View.OnClickListener() { //돌아가기 버튼 클릭
            @Override
            public void onClick(View v) {
                //인텍트 생성, (MainActivity)넘길 데이터(더한 값)를 넣는다
                Intent Out_intent= new Intent(getApplicationContext(),MainActivity.class);
                Out_intent.putExtra("Hap", hav_value);
                //setResult()로 MainActivity 에 돌려준다- >MainActivity 에서 onActivityResult() 실행된다
                setResult(RESULT_OK, Out_intent); //RESULT_OK(-1)
                finish();
            }
        });

        /*직접풀어보기 10-2 
        setTitle("투표 결과");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_image_01);

        //MainActivity 에서 넘긴 인텍트를 받는다(getIntent)
        Intent intent= getIntent();
        //넘겨받은 데이터(키)를 데이터타입에 맞게 저장한다
        int[] voteResult= intent.getIntArrayExtra("VoteCount");
        String[] imageName= intent.getStringArrayExtra("ImageName");

        //넘겨받은 그림의 개수만큼 텍스트뷰와 레이팅바의 위젯 배열 선언
        TextView text[]= new TextView[imageName.length];
        RatingBar rBar[]= new RatingBar[imageName.length];

        //텍스트뷰와 레이팅바의 위젯 id 배열 선언
        Integer textID[]= {
                R.id.text01,R.id.text02,R.id.text03,R.id.text04,R.id.text05,
                R.id.text06,R.id.text07,R.id.text08,R.id.text09
        };
        Integer rBarID[]= {
                R.id.rBar01,R.id.rBar02,R.id.rBar03,R.id.rBar04,R.id.rBar05,
                R.id.rBar06,R.id.rBar07,R.id.rBar08,R.id.rBar09
        };

        //그림파일 id 배열 선언
        Integer imageFileId[]= {
                R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,
                R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9
        };

        //voteResult[] 최대 득표수 구하기
        int max_index= 0;
        for(int i=0;i<voteResult.length;i++){
            if(voteResult[i]>voteResult[max_index]){
                max_index= i;
            }
        }
        //바인딩
        TextView tMax= findViewById(R.id.textMax);
        ImageView iMax= findViewById(R.id.imageMax);

        //최대 득표수의 그림제목과 그림을 화면에 출력한다
        tMax.setText(imageName[max_index]);
        iMax.setImageResource(imageFileId[max_index]);

        //텍스트뷰, 레이팅바 배열에 id를 저장
        for(int i=0;i<voteResult.length;i++){
            text[i]= findViewById(textID[i]); //바인딩
            rBar[i]= findViewById(rBarID[i]);
        }

        //텍스트뷰에 그림제목을 표시하고, 레이팅바에는 투표수를 표시한다
        for(int i=0;i<voteResult.length;i++){
            text[i].setText(imageName[i]);
            rBar[i].setRating((float) voteResult[i]);
        }


        Button bReturn= findViewById(R.id.btnReturn);
        bReturn.setOnClickListener(new View.OnClickListener() { //돌아가기 버튼 클릭
            @Override
            public void onClick(View v) {
                finish(); //현재 엑티비티 종료-> MainActivity 보여진다
            }
        }); */

        /* 실습 10-2 명화 선호드 투표 앱 만들기
        setTitle("투표 결과");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_image_01);

        //MainActivity 에서 넘긴 인텍트를 받는다(getIntent)
        Intent intent= getIntent();
        //넘겨받은 데이터(키)를 데이터타입에 맞게 저장한다
        int[] voteResult= intent.getIntArrayExtra("VoteCount");
        String[] imageName= intent.getStringArrayExtra("ImageName");

        //넘겨받은 그림의 개수만큼 텍스트뷰와 레이팅바의 위젯 배열 선언
        TextView text[]= new TextView[imageName.length];
        RatingBar rBar[]= new RatingBar[imageName.length];

        //텍스트뷰와 레이팅바의 위젯 id 배열 선언
        Integer textID[]= {
                R.id.text01,R.id.text02,R.id.text03,R.id.text04,R.id.text05,
                R.id.text06,R.id.text07,R.id.text08,R.id.text09
        };
        Integer rBarID[]= {
                R.id.rBar01,R.id.rBar02,R.id.rBar03,R.id.rBar04,R.id.rBar05,
                R.id.rBar06,R.id.rBar07,R.id.rBar08,R.id.rBar09
        };

        //텍스트뷰, 레이팅바 배열에 id를 저장
        for(int i=0;i<voteResult.length;i++){
            text[i]= findViewById(textID[i]); //바인딩
            rBar[i]= findViewById(rBarID[i]);
        }

        //텍스트뷰에 그림제목을 표시하고, 레이팅바에는 투표수를 표시한다
        for(int i=0;i<voteResult.length;i++){
            text[i].setText(imageName[i]);
            rBar[i].setRating((float) voteResult[i]);
        }

        
        Button bReturn= findViewById(R.id.btnReturn);
        bReturn.setOnClickListener(new View.OnClickListener() { //돌아가기 버튼 클릭
            @Override
            public void onClick(View v) {
                finish(); //현재 엑티비티 종료-> MainActivity 보여진다
            }
        }); */

    }
}