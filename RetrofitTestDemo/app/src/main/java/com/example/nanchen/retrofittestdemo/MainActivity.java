package com.example.nanchen.retrofittestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mob.mobapi.MobAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobAPI.initSDK(this,Config.MOB_APP_KEY);//初始化

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://apicloud.mob.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<User> userCall = service.registUser(Config.MOB_APP_KEY, "15680802251", "123456", "503233512@qq.com");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e(TAG,"注册成功");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"注册失败");
            }
        });
    }
}
