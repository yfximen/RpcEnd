package com.example.apac.rpcdata.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.BalanceRecordListBean;
import com.example.apac.rpcdata.bean.BalanceRecordOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/6/22.
 */

public class BalanceRecordAdapter extends RecyclerView.Adapter<BalanceRecordAdapter.ViewHolder> {

    private Context context;
    private List<BalanceRecordListBean> list;

    public BalanceRecordAdapter(Context context) {
        this.context = context;
    }

    public List<BalanceRecordListBean> getList() {
        return list;
    }

    public void setList(List<BalanceRecordListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addList(List<BalanceRecordListBean> list) {
        if (this.list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_balance_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_date.setText(list.get(position).getDate());
        List<BalanceRecordOrderBean> lists = list.get(position).getList();
        BalanceRecordRecordAdapter recordAdapter = new BalanceRecordRecordAdapter(context, lists);
        holder.rv_record.setAdapter(recordAdapter);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_balance_record_date)
        TextView tv_date;

        @BindView(R.id.rv_balance_record)
        RecyclerView rv_record;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv_record.setLayoutManager(linearLayoutManager);
        }
    }
}
