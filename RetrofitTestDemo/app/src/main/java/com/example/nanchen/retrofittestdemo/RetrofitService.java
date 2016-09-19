package com.example.nanchen.retrofittestdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author nanchen
 * @fileName RetrofitTestDemo
 * @packageName com.example.nanchen.retrofittestdemo
 * @date 2016/09/18  18:38
 */
public interface RetrofitService {

    @GET("user/rigister")
    Call<User> registUser(@Query("key") String key, @Query("username")String username, @Query("password")String password, @Query("email")String email);
}
