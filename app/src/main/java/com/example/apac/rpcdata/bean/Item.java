package com.example.apac.rpcdata.bean;

/**
 * Created by Jay on 2015/9/25 0025.
 */
public class Item {

    private String iTime;
    private String iName;


    public Item() {
    }

    public Item(String iTime, String iName) {
        this.iTime = iTime;
        this.iName = iName;
    }

    public String getiTime() {
        return iTime;
    }

    public void setiTime(String iTime) {
        this.iTime = iTime;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
