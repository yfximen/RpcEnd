package com.example.apac.rpcdata.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lchtime4 on 2018/6/15.
 * 红包链模块
 */

public class RedMoneyFragment extends BaseFragment {


    private String[] titles = {"红包链", "确认中","以确定","发布"};
    private TabLayout tablayout;
    private ViewPager viewpager;
    private List<Fragment> fragments;
    private MyAdapter adapter;


    @Override
    protected int getLayoutID() {
        return R.layout.red_package_item;
    }

    @Override
    protected void initView(View view) {
        tablayout = view.findViewById(R.id.tablayout);
        viewpager = view.findViewById(R.id.viewpager);
        //页面，数据源
        fragments = new ArrayList<>();
        fragments.add(new HomeRedFragment());
//        fragments.add(new OneFragment2());
//        fragments.add(new OneFragment3());
//        fragments.add(new OneFragment4());
        //ViewPager的适配器
        adapter = new MyAdapter(getActivity().getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        //绑定
        tablayout.setupWithViewPager(viewpager);

    }

    @Override
    protected void initData() {

    }
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
