package com.cookandroid.webview_01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

@SuppressWarnings("deprecation")  //경고(줄표시)가 나오는것을 막아주는 코드
public class MainActivity extends AppCompatActivity {
    EditText edtUrl;
    Button bGo, bBack;
    WebView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch06_22);

        /* ch06_22~28 */
         //타이블바에 이미지(아이콘)넣기
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_new_image_01);

         //바인딩
        edtUrl= findViewById(R.id.edtUrl);
        bGo= findViewById(R.id.bGo);
        bBack= findViewById(R.id.bBack);
        web= findViewById(R.id.webView);

         //상속받은 클래스를 생성하여 웹뷰에 넣는다
        web.setWebViewClient(new CookWebViewClient());

         //webSettings 클래스를 이용 줌버튼 컨트롤이 화면에 보이도록 설정
        WebSettings webSet= web.getSettings();
        webSet.setBuiltInZoomControls(true);
         //웹뷰에서 자바스크립트 작동되도록 설정
        webSet.setJavaScriptEnabled(true);

        bGo.setOnClickListener(new View.OnClickListener() { //이동 버튼
            @Override
            public void onClick(View v) {
                 //에디트텍스트에 입력한 URL 웹페이지가 웹뷰에 나오게 한다
                web.loadUrl(edtUrl.getText().toString());
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //웹뷰의 이전화면으로 돌아간다
                web.goBack();
            }
        });


    }

     //WebViewClient 를 상속받는 클래스를 만든다
    class CookWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}