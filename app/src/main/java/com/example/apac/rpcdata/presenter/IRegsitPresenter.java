package com.example.apac.rpcdata.presenter;

import com.example.apac.rpcdata.bean.RegisterBean;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/20.
 */

public interface IRegsitPresenter {

    void getRegister(String path, String tjr_phone, String ub_phone, String ud_pwd);

    void onFailed(String str);
    
    void onSuccess(RegisterBean registerBeans);

    void onDestory();


}
