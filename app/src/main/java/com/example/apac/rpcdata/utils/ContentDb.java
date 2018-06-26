package com.example.apac.rpcdata.utils;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.ContentMy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lchtime4 on 2018/6/24.
 */

public class ContentDb {
    /**
     * 测试ListView使用
     * 模拟数据库读取List集合数据
     *
     * @return List数据
     */
    public static List<ContentMy> getListContent() {
        List<ContentMy> list = new ArrayList<ContentMy>();
        ContentMy c = new ContentMy();


        c.setId(0);
        c.setTv1("我的银行卡");
        c.setTv2("设置");

        c.setId(1);
        c.setTv1("登陆密码");
        c.setTv2("修改");
        c.setId(2);
        c.setTv1("支付密码");
        c.setTv2("修改");
        c.setId(3);
        c.setTv1("地址管理");
        c.setTv2("修改");
        c.setId(4);
        c.setTv1("公告");
        c.setTv2("浏览");
        c.setId(5);
        c.setTv1("语言");
        c.setTv2("切换");
        c.setId(6);
        c.setTv1("投诉建议");
        c.setTv2("进入");
        c.setId(7);
        c.setTv1("版本信息");
        c.setTv2("浏览");
        c.setId(8);
        c.setTv1("关于我们");
        c.setTv2("浏览");

          list.add(c);
         return list;
    }
}