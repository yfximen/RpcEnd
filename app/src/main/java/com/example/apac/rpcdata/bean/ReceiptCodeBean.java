package com.example.apac.rpcdata.bean;

/**
 * Created by user on 2018/6/22.
 */

public class ReceiptCodeBean {

    private ResultBean result;
    private String url_qrcode;
    private String url_web;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getUrl_qrcode() {
        return url_qrcode;
    }

    public void setUrl_qrcode(String url_qrcode) {
        this.url_qrcode = url_qrcode;
    }

    public String getUrl_web() {
        return url_web;
    }

    public void setUrl_web(String url_web) {
        this.url_web = url_web;
    }
}
