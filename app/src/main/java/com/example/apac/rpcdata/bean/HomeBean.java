package com.example.apac.rpcdata.bean;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/22.
 */

public class HomeBean {


    /**
     * result : {"sid":"1112c20ad4d76fe97759aa27a0c99bff672110","index":"","code":"10","info":"执行成功"}
     * rpc_level : 0
     * rpc_num : 10012
     * rpc_count : 12
     * marquee : []
     * rpc_up : [{"ub_id":"7","ud_nickname":"rpc_7"},{"ub_id":"8","ud_nickname":"rpc_8"},{"ub_id":"9","ud_nickname":"rpc_9"},{"ub_id":"10","ud_nickname":"rpc_10"},{"ub_id":"11","ud_nickname":"rpc_11"}]
     */

    private ResultBean result;
    private int rpc_level;
    private String rpc_num;
    private String rpc_count;
    private List<?> marquee;
    private List<RpcUpBean> rpc_up;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getRpc_level() {
        return rpc_level;
    }

    public void setRpc_level(int rpc_level) {
        this.rpc_level = rpc_level;
    }

    public String getRpc_num() {
        return rpc_num;
    }

    public void setRpc_num(String rpc_num) {
        this.rpc_num = rpc_num;
    }

    public String getRpc_count() {
        return rpc_count;
    }

    public void setRpc_count(String rpc_count) {
        this.rpc_count = rpc_count;
    }

    public List<?> getMarquee() {
        return marquee;
    }

    public void setMarquee(List<?> marquee) {
        this.marquee = marquee;
    }

    public List<RpcUpBean> getRpc_up() {
        return rpc_up;
    }

    public void setRpc_up(List<RpcUpBean> rpc_up) {
        this.rpc_up = rpc_up;
    }

    public static class ResultBean {
        /**
         * sid : 1112c20ad4d76fe97759aa27a0c99bff672110
         * index :
         * code : 10
         * info : 执行成功
         */

        private String sid;
        private String index;
        private String code;
        private String info;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class RpcUpBean {
        /**
         * ub_id : 7
         * ud_nickname : rpc_7
         */

        private String ub_id;
        private String ud_nickname;

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
}
