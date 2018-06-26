package com.example.apac.rpcdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.BalanceRecordOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/6/22.
 */

public class BalanceRecordRecordAdapter extends RecyclerView.Adapter<BalanceRecordRecordAdapter.ViewHolder> {

    private Context context;
    private List<BalanceRecordOrderBean> list;

    public BalanceRecordRecordAdapter(Context context, List<BalanceRecordOrderBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_balance_record_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_time.setText(list.get(position).getRro_time());
        holder.tv_note.setText(list.get(position).getRro_info());
        if ("-1".equals(list.get(position).getRro_dir())) {
            holder.tv_amount.setTextColor(Color.parseColor("#666666"));
            holder.tv_amount.setText("-" + list.get(position).getRro_num());
        } else if ("1".equals(list.get(position).getRro_dir())) {
            holder.tv_amount.setTextColor(Color.parseColor("#ff2802"));
            holder.tv_amount.setText("+" + list.get(position).getRro_num());
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_balance_record_time)
        TextView tv_time;

        @BindView(R.id.tv_balance_record_amount)
        TextView tv_amount;

        @BindView(R.id.tv_balance_record_note)
        TextView tv_note;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
