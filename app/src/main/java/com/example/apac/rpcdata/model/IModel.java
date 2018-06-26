package com.example.apac.rpcdata.model;

import com.example.apac.rpcdata.presenter.IRegsitPresenter;

/**
 * Created by lchtime4 on 2018/6/20.
 */

public interface IModel {
    void getRegsit(String path,String tjr_phone, String ub_phone, String ud_pwd,  IRegsitPresenter iRegsitPresenter);




}
