package com.example.apac.rpcdata.bean;

/**
 * Created by lchtime4 on 2018/6/15.
 */

public class WangZhe {
    private String num;
    private String whit;
    private String time;
    private String xq;

    public WangZhe(String num, String whit, String time, String xq) {
        this.num = num;
        this.whit = whit;
        this.time = time;
        this.xq = xq;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getWhit() {
        return whit;
    }

    public void setWhit(String whit) {
        this.whit = whit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }
}
