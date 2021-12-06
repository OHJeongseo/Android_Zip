package com.cookandroid.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//서버 연결 설정
public class Retrofit2Client {
    private static Retrofit2Client instance;
    private PhoneService phoneService;

    //서버 포트 설정
    public Retrofit2Client(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://192.168.0.5:8899")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        phoneService= retrofit.create(PhoneService.class);
    }

    public  static Retrofit2Client getInstance(){
        if(instance== null){
            instance= new Retrofit2Client();
        }
        return instance;
    }

    public PhoneService getPhoneService(){
        return phoneService;
    }
}
