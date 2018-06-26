package com.example.apac.rpcdata.bean;

/**
 * Created by lchtime4 on 2018/6/25.
 */

public class CarBang {

    /**
     * result : {"sid":"1112c20ad4d76fe97759aa27a0c99bff672110","index":"","code":"10","info":"绑定银行卡成功"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * sid : 1112c20ad4d76fe97759aa27a0c99bff672110
         * index :
         * code : 10
         * info : 绑定银行卡成功
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
