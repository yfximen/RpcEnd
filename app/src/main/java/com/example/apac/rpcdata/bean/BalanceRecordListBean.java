package com.example.apac.rpcdata.bean;

import java.util.List;

/**
 * Created by user on 2018/6/22.
 */

public class BalanceRecordListBean {

    private String date;
    private List<BalanceRecordOrderBean> list;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<BalanceRecordOrderBean> getList() {
        return list;
    }

    public void setList(List<BalanceRecordOrderBean> list) {
        this.list = list;
    }
}
