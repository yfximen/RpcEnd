package com.example.apac.rpcdata.bean;

import java.util.List;

/**
 * Created by user on 2018/6/22.
 */

public class BalanceRecordBean {

    private ResultBean result;
    private List<BalanceRecordListBean> rpc_rpc_order;
    private String total;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<BalanceRecordListBean> getRpc_rpc_order() {
        return rpc_rpc_order;
    }

    public void setRpc_rpc_order(List<BalanceRecordListBean> rpc_rpc_order) {
        this.rpc_rpc_order = rpc_rpc_order;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
