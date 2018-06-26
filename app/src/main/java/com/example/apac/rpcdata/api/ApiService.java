package com.example.apac.rpcdata.api;

import com.example.apac.rpcdata.bean.RegisterBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 实现类
 */

public interface ApiService {
    //用户注册
    @POST("/index.php/user/reg")
    Flowable<RegisterBean> getRegister(@Query("tjr_phone") String tjr_phone,@Query("ub_phone") String ub_phone,@Query("ud_pwd") String ud_pwd);

 }
