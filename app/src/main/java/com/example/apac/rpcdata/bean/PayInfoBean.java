package com.example.apac.rpcdata.bean;

/**
 * Created by user on 2018/6/22.
 */

public class PayInfoBean {

    private ResultBean result;
    private String ub_phone;
    private String ub_id;
    private String ud_nickname;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getUb_phone() {
        return ub_phone;
    }

    public void setUb_phone(String ub_phone) {
        this.ub_phone = ub_phone;
    }

    public String getUb_id() {
        return ub_id;
    }

    public void setUb_id(String ub_id) {
        this.ub_id = ub_id;
    }

    public String getUd_nickname() {
        return ud_nickname;
    }

    public void setUd_nickname(String ud_nickname) {
        this.ud_nickname = ud_nickname;
    }

}
