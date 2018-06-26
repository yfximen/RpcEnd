package com.example.apac.rpcdata.ui.receiptcode;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.example.apac.rpcdata.bean.ReceiptCodeBean;
import com.example.apac.rpcdata.ui.BasePresenter;
import com.example.apac.rpcdata.utils.NetworkUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by user on 2018/6/22.
 */

public class ReceiptCodeP extends BasePresenter {

    private ReceiptCodePface face;

    public ReceiptCodeP(ReceiptCodePface face, FragmentActivity activity) {
        this.face = face;
        setActivity(activity);
    }

    /**
     * 生成收款码  邀请码
     */
    public void getReceiptCode(int id) {
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        NetworkUtils.getNetworkUtils().send(id, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                dismissProgressDialog();
                makeText("数据请求失败");
            }

            @Override
            public void onResponse(String s, int i) {
                if ("{".equals(s.substring(0, 1))) {
                    ReceiptCodeBean receiptCodeBean = JSON.parseObject(s, ReceiptCodeBean.class);
                    if ("10".equals(receiptCodeBean.getResult().getCode())){
                        face.setReceiptCode(receiptCodeBean);
                    } else {
                        makeText(receiptCodeBean.getResult().getInfo());
                    }
                } else {
                    makeText("数据异常");
                }
                dismissProgressDialog();
            }
        });
    }

    public interface ReceiptCodePface {
        void setReceiptCode(ReceiptCodeBean receiptCodeBean);
    }

}
