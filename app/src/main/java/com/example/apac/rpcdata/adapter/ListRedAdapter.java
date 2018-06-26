package com.example.apac.rpcdata.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.RedId;
import com.example.apac.rpcdata.utils.IdBean;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/23.
 */

public class ListRedAdapter extends ArrayAdapter {
    private final int resourceId;


    public ListRedAdapter(Context context, int textViewResourceId, List<IdBean> objects) {
        super(context, textViewResourceId, objects);
        resourceId= textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         IdBean fruit = (IdBean) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView list_id =  view.findViewById(R.id.list_id);//获取该布局内的图片视图
        TextView list_name =  view.findViewById(R.id.list_name);//获取该布局内的文本视图
        list_id.setText(fruit.getUb_id());
        list_name.setText(fruit.getUd_nickname());


        return view;



    }
}
