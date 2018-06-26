package com.example.apac.rpcdata.ui;

import android.graphics.Color;
import android.view.View;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.base.BaseTwoActivity;
import com.example.apac.rpcdata.fragment.HomeFragment;
import com.example.apac.rpcdata.fragment.MyFragment;
import com.example.apac.rpcdata.fragment.PropertyFragment;
import com.example.apac.rpcdata.fragment.RedMoneyFragment;
import com.example.apac.rpcdata.fragment.ShoppingFragment;
import com.hjm.bottomtabbar.BottomTabBar;


/**
 * 红包链主模块
 */

public class RedPacketActivity extends BaseTwoActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected int getViewID() {
        return R.layout.activity_red_packet;
    }

    @Override
    protected void initView() {
        bottomTabBar = findViewById(R.id.bottom_tab_bar);
    }

    @Override
    protected void initData() {


        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50,50)
                .setFontSize(8)
                .setTabPadding(4,6,10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                 //fragment看下面的模板
                .addTabItem("首页",R.mipmap.icon_weixin, HomeFragment.class)
                .addTabItem("商城",R.mipmap.icon_zhifubao, ShoppingFragment.class)
                .addTabItem("红包链",R.mipmap.icon_weixin, RedMoneyFragment.class)
                .addTabItem("资产",R.mipmap.icon_zhifubao, PropertyFragment.class)
                .addTabItem("我的",R.mipmap.icon_weixin, MyFragment.class)

                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }

    @Override
    protected Object getPersenter() {
        return null;
    }


}
