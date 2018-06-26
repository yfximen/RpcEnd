package com.example.apac.rpcdata.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.WangZhe;

import java.util.List;

/**
 * Created by lchtime4 on 2018/6/15.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private List<WangZhe> wangZheList;
    private WangZhe wangZhe;

    public RecycleAdapter(List<WangZhe> wangZheList) {
        this.wangZheList = wangZheList;
    }
    
    @Override
    public  ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_package_itemson1, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
         viewHolder.tvdatails.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int adapterPosition = viewHolder.getAdapterPosition();
                 WangZhe wangZhe = wangZheList.get(adapterPosition);
                 Toast.makeText(parent.getContext(), wangZhe.getTime(), Toast.LENGTH_SHORT).show();

             }
         });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WangZhe wangZhe = wangZheList.get(position);
        holder.tvdatails.setText(wangZhe.getXq());
        holder.tvnum.setText(wangZhe.getNum());
        holder.tvtime.setText(wangZhe.getTime());
        holder.tvwhit.setText(wangZhe.getWhit());


    }



    @Override
    public int getItemCount() {
        return wangZheList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder{

           TextView tvnum,tvtime,tvwhit,tvdatails;

         public ViewHolder(View itemView) {
             super(itemView);

               tvnum = itemView.findViewById(R.id.tvnum);
               tvtime = itemView.findViewById(R.id.tvtime);
               tvwhit = itemView.findViewById(R.id.tvwhit);
               tvdatails = itemView.findViewById(R.id.tvdatails);


         }
     }


}
