package com.cookandroid.implicit_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 /*암시적 인텍트: 약속된 액션 지정, 안드로이드가 제공하는 기존 응용프로그램 샐행 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch10_21);

        /*실습 10-3 로그캣(예기치 못한 오류가 발생할때  원인을 찾기 위해서 log 남긴다,
                그것을 보여주는 화면) 을 이용한 엑티비티 생명주기 확인하기 */
        setTitle("엑티비티 테스트 예제");
        //onCreate()를 실행 log 를 남김
        android.util.Log.i("엑티비티 테스트", "onCreate()"); //태그(구분),  로그에 남길 문자
        Button bTel= findViewById(R.id.btnTel);
        Button bEnd= findViewById(R.id.btnEnd);

        bTel.setOnClickListener(new View.OnClickListener() { //전화 걸기 버튼
           @Override
            public void onClick(View v) {
               Uri uri= Uri.parse("tel:01022739380"); //uri 문자열 "tel:전화번호"
               Intent intent= new Intent(Intent.ACTION_DIAL, uri); //ACTION_DIAL-> 전화 걸기 창 열기
               startActivity(intent);
            }
        });
        
        bEnd.setOnClickListener(new View.OnClickListener() { //끝내기 버튼
            @Override
            public void onClick(View v) {
                finish(); //엑티비티 종료
            }
        });


        /*ch10-20(ch10-19.xml(메인엑티비티)와 연결되는 암시적 인텍트 java 코드
        setTitle("암시적 인텍트 예제");
        Button bTel= findViewById(R.id.btnTel);
        Button bWel= findViewById(R.id.btnWeb);
        Button bGoogleMap= findViewById(R.id.btnGoogle);
        Button bSearch= findViewById(R.id.btnSearch);
        Button bSms= findViewById(R.id.btnSms);
        Button bPhoto= findViewById(R.id.btnPhoto);

        //인텍트 전화걸기
        bTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("tel:01022739380"); //"tel:전화번호"-> 파싱-> Uri
                Intent intent= new Intent(Intent.ACTION_DIAL, uri); //ACTION_DIAL-> 전화 걸기 창 열기
                startActivity(intent);
            }
        });

        //인텍트 웹 페이지
        bWel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("http://www.hanbit.co.kr"); //"http://웹주소"-> 파싱-> Uri
                Intent intent= new Intent(Intent.ACTION_VIEW, uri);//ACTION_VIEW-> 웹페이지 열기
                startActivity(intent);
            }
        });

        //인텍트 구글맵
        bGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("http://maps.google.co.kr/maps?q="+37.559133+
                        ","+126.927824+"&z"+15); //"구글맵 주소+경도,위도"-> 파싱-> Uri
                Intent intent= new Intent(Intent.ACTION_VIEW, uri); 
                startActivity(intent);
            }
        });

        //인텍트 구글 검색
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_WEB_SEARCH); //ACTION_WEB_SEARCH-> 구글검색 열기
                intent.putExtra(SearchManager.QUERY, "안드로이드"); //검색어 '안드로이드'-> 검색
                startActivity(intent);
            }
        });

        //인텍트 문자보내기
        bSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_SENDTO); //ACTION_SENDTO-> 문자메세지 액션
                intent.putExtra("sms_body", "안녕하세요?"); //보낼문자
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-2273-9380"))); //문자보내기 
                startActivity(intent);
            }
        });

        //인텍트 사진찍기
        bPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //MediaStore.ACTION_IMAGE_CAPTURE-> 카메라 열기
                startActivity(intent);
               //이미지 앱사용 카페에서 확인->https://cafe.naver.com/busanit2018db
               // *ppt 코드* //
            }
        });*/
    }
    
    //액티비티의 생명주기를 log 로 남겨서 을 로그캣을 통해서 동작과정을 화면에 보여준다
    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.util.Log.i("엑티비티 테스트", "onDestroy()"); //태그(구분),  로그에 남길 문자
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.i("엑티비티 테스트", "onPause()"); //태그(구분),  로그에 남길 문자
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        android.util.Log.i("엑티비티 테스트", "onRestart()"); //태그(구분),  로그에 남길 문자
    }

    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.i("엑티비티 테스트", "onResume()"); //태그(구분),  로그에 남길 문자
    }

    @Override
    protected void onStart() {
        super.onStart();
        android.util.Log.i("엑티비티 테스트", "onStart()"); //태그(구분),  로그에 남길 문자
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.i("엑티비티 테스트", "onStop()"); //태그(구분),  로그에 남길 문자
    }
}