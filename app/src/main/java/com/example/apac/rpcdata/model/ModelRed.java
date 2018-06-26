package com.example.apac.rpcdata.model;

import com.example.apac.rpcdata.api.ApiService;
import com.example.apac.rpcdata.bean.RegisterBean;
import com.example.apac.rpcdata.presenter.IRegsitPresenter;
import com.example.apac.rpcdata.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Created by lchtime4 on 2018/6/20.
 */

public class ModelRed implements IModel {
    RetrofitUtils retrofitUtils = RetrofitUtils.getInData();

    //注册
    @Override
    public void getRegsit(String path, String tjr_phone, String ub_phone, String ud_pwd, final IRegsitPresenter iRegsitPresenter) {
        
        ApiService apiService = retrofitUtils.getRetrofit(path, ApiService.class);
        Flowable<RegisterBean> flowable = apiService.getRegister(tjr_phone,ub_phone, ud_pwd);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                           iRegsitPresenter.onSuccess(registerBean);
                     }

                    @Override
                    public void onError(Throwable t) {

                        iRegsitPresenter.onFailed(t.getMessage());



                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
