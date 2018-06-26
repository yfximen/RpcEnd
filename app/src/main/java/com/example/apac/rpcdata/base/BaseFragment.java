package com.example.apac.rpcdata.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lchtime4 on 2018/6/15.
 * 抽取Fragment基类
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutID(),container,false);
        initView(view);
        initData();
        return view;
    }


    protected abstract int getLayoutID();
    protected abstract void initView(View view);
    protected abstract void initData();



}
