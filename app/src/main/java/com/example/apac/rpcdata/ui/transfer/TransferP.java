package com.example.apac.rpcdata.ui.transfer;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.example.apac.rpcdata.bean.PayInfoBean;
import com.example.apac.rpcdata.ui.BasePresenter;
import com.example.apac.rpcdata.utils.NetworkUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by user on 2018/6/22.
 */

public class TransferP extends BasePresenter {

    private TransferPface face;

    public TransferP(TransferPface face, FragmentActivity activity) {
        this.face = face;
        setActivity(activity);
    }

    /**
     * 获取对方信息
     */
    public void getTransferInfo(int id, String ub_phone) {
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("ub_phone", ub_phone);
        NetworkUtils.getNetworkUtils().send(id, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                dismissProgressDialog();
                makeText("数据请求失败");
            }

            @Override
            public void onResponse(String s, int i) {
                if ("{".equals(s.substring(0, 1))) {
                    PayInfoBean payInfoBean = JSON.parseObject(s, PayInfoBean.class);
                    if ("10".equals(payInfoBean.getResult().getCode())) {
                        face.setTransferInfo(payInfoBean);
                    } else {
                        makeText(payInfoBean.getResult().getInfo());
                    }
                } else {
                    makeText("数据异常");
                }
                dismissProgressDialog();
            }
        });
    }

    public interface TransferPface {
        void setTransferInfo(PayInfoBean payInfoBean);
    }

}
