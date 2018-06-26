package com.example.apac.rpcdata.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.apac.rpcdata.MainActivity;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.adapter.MyBaseExpandableListAdapter;
import com.example.apac.rpcdata.bean.Group;
import com.example.apac.rpcdata.bean.Item;

import java.util.ArrayList;

public class BalanceActivity extends AppCompatActivity {

    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        mContext = BalanceActivity.this;
        exlist_lol = (ExpandableListView) findViewById(R.id.exlist_lol);


        //数据准备
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group("AD"));
        gData.add(new Group("AP"));
        gData.add(new Group("TANK"));

        lData = new ArrayList<Item>();

        //AD组
        lData.add(new Item("2018-0-1","剑圣"));
        lData.add(new Item("2018-0-2","德莱文"));
        lData.add(new Item("2018-0-3","男枪"));
        lData.add(new Item("2018-0-4","韦鲁斯"));
        iData.add(lData);
        //AP组
        lData = new ArrayList<Item>();
        lData.add(new Item("2018-0-12", "提莫"));
        lData.add(new Item("2018-0-13", "安妮"));
        lData.add(new Item("2018-0-14", "天使"));
        lData.add(new Item("2018-0-15", "泽拉斯"));
        lData.add(new Item("2018-0-16", "狐狸"));
        iData.add(lData);
        //TANK组
        lData = new ArrayList<Item>();
        lData.add(new Item("2018-0-16", "诺手"));
        lData.add(new Item("2018-0-15", "德邦"));
        lData.add(new Item("2018-0-14", "奥拉夫"));
        lData.add(new Item("2018-0-13", "龙女"));
        lData.add(new Item("2018-0-12", "狗熊"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);


        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "你点击了：" + iData.get(groupPosition).get(childPosition).getiName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
