package com.example.apac.rpcdata.ui;

import com.example.apac.rpcdata.bean.RegisterBean;


/**
 * Created by lchtime4 on 2018/6/20.
 */

public interface Iview {
    void onFailed(String str);

    void onSuccess(RegisterBean registerBean);
}
