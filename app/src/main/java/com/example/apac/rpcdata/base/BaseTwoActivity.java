package com.example.apac.rpcdata.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by lchtime4 on 2018/6/15.
 * 抽取Activity基类
 */

public abstract class BaseTwoActivity<T> extends AppCompatActivity {
    public T persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getViewID());
        persenter = getPersenter();
        initView();
        initData();
        ButterKnife.bind(this);


    }

    protected abstract int getViewID();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract T getPersenter();

 }
