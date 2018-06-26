package com.example.apac.rpcdata.bean;

import java.io.Serializable;

/**
 * Created by lchtime4 on 2018/6/23.
 */

public class RedId implements Serializable{
    private String ub_id;
    private String ud_nickname;

    public RedId(String ub_id, String ud_nickname) {
        this.ub_id = ub_id;
        this.ud_nickname = ud_nickname;
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
