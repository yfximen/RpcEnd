package com.example.apac.rpcdata.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.adapter.RecycleAdapter;
import com.example.apac.rpcdata.base.BaseFragment;
import com.example.apac.rpcdata.bean.WangZhe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lchtime4 on 2018/6/15.
 * 首页中红包链模块
 */

public class HomeRedFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<WangZhe> wangZhes= new ArrayList<>();


    @Override
    protected int getLayoutID() {
        return R.layout.layout_package_item1;

    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecycleAdapter adapter=new RecycleAdapter(wangZhes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 1; i++) {
            WangZhe bianqu = new WangZhe ("1500","等待","2018-07-10 -00:00:00","详情");
            wangZhes.add(bianqu);
            WangZhe daji = new WangZhe ("1500","等待","2018-07-11-00:00:00","详情");
            wangZhes.add(daji);
            WangZhe diaochan = new WangZhe ("1500","等待","2018-07-12 00:00:00","详情");
            wangZhes.add(diaochan);
            WangZhe huamulan = new WangZhe ("1500","等待","2018-07-13-00:00:00","详情");
            wangZhes.add(huamulan);
            WangZhe libai = new WangZhe ("1500","等待","2018-07-14-00:00:00","详情");
            wangZhes.add(libai);

        }
    }



}
