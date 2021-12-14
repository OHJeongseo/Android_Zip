package com.cookandroid.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
//스프링과 연관되는 인터페이스 설정
public interface PhoneService {
    @GET("list") //전체보기
    Call<List<Phone>> findAll();

    @POST("insert") //추가
    Call<Phone> save(@Body Phone phoneDto);

    @DELETE("delete/{id}") //삭제
    Call<Void> deleteById(@Path("id") Long id); //리턴값 사용안함

    @PUT("update/{id}")
    Call<Phone> update(@Path("id") Long id, @Body Phone phone);
}
