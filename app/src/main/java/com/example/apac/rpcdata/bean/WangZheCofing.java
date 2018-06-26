package com.example.apac.rpcdata.bean;

/**
 * Created by lchtime4 on 2018/6/15.
 */

public class WangZheCofing {
    private String pinkNamec;
    private String numc;
    private String statec;
    private String timec;

    public WangZheCofing(String pinkNamec, String numc, String statec, String timec) {
        this.pinkNamec = pinkNamec;
        this.numc = numc;
        this.statec = statec;
        this.timec = timec;
    }

    public String getPinkNamec() {
        return pinkNamec;
    }

    public void setPinkNamec(String pinkNamec) {
        this.pinkNamec = pinkNamec;
    }

    public String getNumc() {
        return numc;
    }

    public void setNumc(String numc) {
        this.numc = numc;
    }

    public String getStatec() {
        return statec;
    }

    public void setStatec(String statec) {
        this.statec = statec;
    }

    public String getTimec() {
        return timec;
    }

    public void setTimec(String timec) {
        this.timec = timec;
    }
}
