package com.example.apac.rpcdata.ui.pay;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.example.apac.rpcdata.bean.CommonBean;
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

public class ScanResultP extends BasePresenter {

    private ScanResultPface face;

    public ScanResultP(ScanResultPface face, FragmentActivity activity){
        this.face = face;
        setActivity(activity);
    }

    /**
     * 获取对方信息
     */
    public void getPayInfo(int id, String qrcode) {
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("qrcode", qrcode);
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
                    if ("10".equals(payInfoBean.getResult().getCode())){
                        face.setPayInfo(payInfoBean);
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

    /**
     * 支付
     */
    public void getPay(int id, String to_ub_id, String to_ub_phone, String rpc_num, String ud_pay_pwd) {
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("to_ub_id", to_ub_id);
        params.put("to_ub_phone", to_ub_phone);
        params.put("rpc_num", rpc_num);
        params.put("ud_pay_pwd", ud_pay_pwd);
        NetworkUtils.getNetworkUtils().send(id, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                dismissProgressDialog();
                makeText("您网络不给力，请稍后再试");
            }

            @Override
            public void onResponse(String s, int i) {
                if ("{".equals(s.substring(0, 1))) {
                    CommonBean commonBean = JSON.parseObject(s, CommonBean.class);
                    if ("10".equals(commonBean.getResult().getCode())){
                        face.setPay();
                    } else {
                        makeText(commonBean.getResult().getInfo());
                    }
                } else {
                    makeText("数据异常");
                }
                dismissProgressDialog();
            }
        });
    }

    public interface ScanResultPface{

        void setPayInfo(PayInfoBean payInfoBean);

        void setPay();
    }

}
