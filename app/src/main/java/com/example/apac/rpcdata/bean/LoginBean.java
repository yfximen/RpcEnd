package com.example.apac.rpcdata.bean;

/**
 * Created by lchtime4 on 2018/6/21.
 */

public class LoginBean {

    /**
     * result : {"code":"10","info":"登陆成功","index":"","sid":"1114c254e7753095807e1cca159e48ec4e0b6231","code1":"111","app_version":"1.6","msg":"","download_url":"http://vm.lchtime.cn//ios.php"}
     * user_type : 1
     * ub_id : 3604
     * ud_md5addr : 9295c354e650b37f58a18a71d709de10
     */

    private ResultBean result;
    private int user_type;
    private int ub_id;
    private String ud_md5addr;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getUb_id() {
        return ub_id;
    }

    public void setUb_id(int ub_id) {
        this.ub_id = ub_id;
    }

    public String getUd_md5addr() {
        return ud_md5addr;
    }

    public void setUd_md5addr(String ud_md5addr) {
        this.ud_md5addr = ud_md5addr;
    }

    public static class ResultBean {
        /**
         * code : 10
         * info : 登陆成功
         * index :
         * sid : 1114c254e7753095807e1cca159e48ec4e0b6231
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
