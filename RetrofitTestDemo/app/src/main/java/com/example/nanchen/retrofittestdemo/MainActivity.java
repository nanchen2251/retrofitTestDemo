package com.example.nanchen.retrofittestdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.mob.mobapi.MobAPI;

import okhttp3.ResponseBody;
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.MOB_API_SERVICE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        final Call<User> userCall = service.registUser(Config.MOB_APP_KEY, "18349225817", "123456", "129502199@qq.com");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.e(TAG,user.toString());
                if (response.isSuccessful()){
                    if (user.getRetCode().equals("200")){
                        Log.e(TAG,"注册成功");
                        Toast.makeText(MainActivity.this,"注册成功!",Toast.LENGTH_SHORT).show();
                    }else {
                        Log.e(TAG,"注册失败："+user.getMsg());
                    }
                }else {
                    ResponseBody  errorBody = response.errorBody();
                    Log.e(TAG,"error："+errorBody.toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG,"注册失败,请检查网络连接！");
                Log.e(TAG,t.toString());
            }
        });
    }
}
