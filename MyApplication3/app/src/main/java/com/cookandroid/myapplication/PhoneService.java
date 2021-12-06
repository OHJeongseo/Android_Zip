package com.cookandroid.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PhoneService {
    @GET("list") //전체보기
    Call<List<Phone>> findAll();

    @POST("insert") //추가
    Call<Phone> save(@Body Phone phoneDto);
}
