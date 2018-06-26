package com.example.apac.rpcdata.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.WangZheCofing;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/15.
 */

public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.ViewHolder> {
    private List<WangZheCofing> wangZheList;
    private WangZheCofing wangZhe;

    public ConfigAdapter(List<WangZheCofing> wangZheList) {
        this.wangZheList = wangZheList;
    }

    @Override
    public  ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_package_itemson2, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
          wangZhe = wangZheList.get(position);
        holder.pinkNamec.setText(wangZhe.getPinkNamec());
        holder.numc.setText(wangZhe.getNumc());
        holder.statec.setText(wangZhe.getStatec());
        holder.timec.setText(wangZhe.getTimec());
        Log.i("ccc",wangZhe.getNumc());

    }



    @Override
    public int getItemCount() {
        return wangZheList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder{

           TextView pinkNamec,numc,statec,timec;

         public ViewHolder(View itemView) {
             super(itemView);

             pinkNamec = itemView.findViewById(R.id.pinkNamec);
             numc = itemView.findViewById(R.id.numc);
             statec = itemView.findViewById(R.id.statec);
             timec = itemView.findViewById(R.id.timec);


         }
     }


}
