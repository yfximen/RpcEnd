package com.example.apac.rpcdata.ui.balancerecord;

import android.support.v4.app.FragmentActivity;

import com.alibaba.fastjson.JSON;
import com.example.apac.rpcdata.bean.BalanceRecordBean;
import com.example.apac.rpcdata.ui.BasePresenter;
import com.example.apac.rpcdata.utils.NetworkUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by user on 2018/6/22.
 */

public class BalanceRecordP extends BasePresenter {

    private BalanceRecordPface face;

    public BalanceRecordP(BalanceRecordPface face, FragmentActivity activity) {
        this.face = face;
        setActivity(activity);
    }

    /**
     * 余额记录
     */
    public void getBalanceRecord(int id, final int page) {
        showProgressDialog();
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        NetworkUtils.getNetworkUtils().send(id, params, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                dismissProgressDialog();
                makeText("数据请求失败");
                face.setNoData();
            }

            @Override
            public void onResponse(String s, int i) {
                if ("{".equals(s.substring(0, 1))) {
                    BalanceRecordBean balanceRecordBean = JSON.parseObject(s, BalanceRecordBean.class);
                    if ("10".equals(balanceRecordBean.getResult().getCode())) {
                        face.setBalanceRecord(balanceRecordBean, page);
                    } else {
                        makeText(balanceRecordBean.getResult().getInfo());
                        face.setNoData();
                    }
                } else {
                    makeText("数据异常");
                }
                dismissProgressDialog();
            }
        });
    }

    public interface BalanceRecordPface {
        void setBalanceRecord(BalanceRecordBean balanceRecordBean, int curPage);

        void setNoData();
    }

}
