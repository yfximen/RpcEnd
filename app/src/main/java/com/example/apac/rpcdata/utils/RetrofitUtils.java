package com.example.apac.rpcdata.utils;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装网络请求类
 */

public class RetrofitUtils {
    private static RetrofitUtils RETROFIT_UTILS = null;

    private RetrofitUtils() {}

    public static RetrofitUtils getInData() {
        if (RETROFIT_UTILS == null){
            synchronized (RetrofitUtils.class){
                if (RETROFIT_UTILS == null){
                    RETROFIT_UTILS = new RetrofitUtils();
                }
            }
        }
        return RETROFIT_UTILS;
    }

    public <T> T getRetrofit(String path,Class<T> cla){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG",message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        T t = retrofit.create(cla);

        return t;
    }
}
