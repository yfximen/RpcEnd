package com.example.apac.rpcdata.utils;

/**
 * Created by lchtime4 on 2018/6/21.
 */

public class UserBean {


    /**
     * result : {"code":"10","info":"注册成功!","index":"","sid":"1724c254e7753095807e1cc4a10596e438eceb21","code1":"111","app_version":"1.6","msg":"","download_url":"http://vm.lchtime.cn//ios.php"}
     * ub_id : 3604
     */

    private ResultBean result;
    private String ub_id;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getUb_id() {
        return ub_id;
    }

    public void setUb_id(String ub_id) {
        this.ub_id = ub_id;
    }

    public static class ResultBean {
        /**
         * code : 10
         * info : 注册成功!
         * index :
         * sid : 1724c254e7753095807e1cc4a10596e438eceb21
         * code1 : 111
         * app_version : 1.6
         * msg :
         * download_url : http://vm.lchtime.cn//ios.php
         */

        private String code;
        private String info;
        private String index;
        private String sid;
        private String code1;
        private String app_version;
        private String msg;
        private String download_url;

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

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getCode1() {
            return code1;
        }

        public void setCode1(String code1) {
            this.code1 = code1;
        }

        public String getApp_version() {
            return app_version;
        }

        public void setApp_version(String app_version) {
            this.app_version = app_version;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }
    }
}
