package com.example.apac.rpcdata.bean;

/**
 * Created by lchtime4 on 2018/6/23.
 */

public class RedPageBean {

    /**
     * result : {"sid":"1724c254e7753095807e1cc4a10596e438eceb21","index":"","code":"20","info":"余额不够"}
     * rpc_status : 0
     */

    private ResultBean result;
    private int rpc_status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getRpc_status() {
        return rpc_status;
    }

    public void setRpc_status(int rpc_status) {
        this.rpc_status = rpc_status;
    }

    public static class ResultBean {
        /**
         * sid : 1724c254e7753095807e1cc4a10596e438eceb21
         * index :
         * code : 20
         * info : 余额不够
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
}
