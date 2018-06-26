package com.example.apac.rpcdata.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.adapter.ConfigAdapter;
import com.example.apac.rpcdata.base.BaseFragment;
import com.example.apac.rpcdata.bean.WangZheCofing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lchtime4 on 2018/6/15.
 * 首页中的待确定模块
 */

public class HomeConfingFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<WangZheCofing> wangZhes= new ArrayList<>();
    private ConfigAdapter adapter;

    @Override
    protected int getLayoutID() {
        return R.layout.layout_package_item2;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_cofing);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ConfigAdapter(wangZhes);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 1; i++) {
            WangZheCofing bianqu = new WangZheCofing ("东方不败","1200"+i,"代付款","2018-10-1");
            wangZhes.add(bianqu);
            WangZheCofing daji = new WangZheCofing ("西门吹雪","1300"+i,"未付款","2018-10-2");
            wangZhes.add(daji);


        }
    }
}
