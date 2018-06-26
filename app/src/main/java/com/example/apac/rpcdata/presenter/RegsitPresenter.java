package com.example.apac.rpcdata.presenter;

import com.example.apac.rpcdata.bean.RegisterBean;
import com.example.apac.rpcdata.model.ModelRed;
import com.example.apac.rpcdata.ui.Iview;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/20.
 */

public class RegsitPresenter implements IRegsitPresenter {
      private Iview iview;
      private ModelRed modelRed;

    public RegsitPresenter(Iview iview) {
        this.iview = iview;
        modelRed=new ModelRed();
    }


    @Override
    public void getRegister(String path, String tjr_phone, String ub_phone, String ud_pwd) {
          modelRed.getRegsit(path,tjr_phone,ub_phone,ud_pwd,this);
    }

    @Override
    public void onFailed(String str) {
        if (iview != null){
            iview.onFailed(str);
        }
    }

    @Override
    public void onSuccess(RegisterBean data) {
        if (iview != null){
            iview.onSuccess(data);
        }
    }

    @Override
    public void onDestory() {
        if (iview != null){
            iview = null;
        }
    }
}
