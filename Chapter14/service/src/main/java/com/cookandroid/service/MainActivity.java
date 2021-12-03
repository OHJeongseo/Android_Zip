package com.cookandroid.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
/*서비스: 화면없이 동작하는 프로그램, 데몬 또는 백그라운드 프로세스라고도 한다*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}