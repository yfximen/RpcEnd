package com.example.apac.rpcdata.ui.transfer;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.PayInfoBean;
import com.example.apac.rpcdata.ui.BaseUI;
import com.example.apac.rpcdata.ui.pay.ScanResultUI;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 转账
 * Created by user on 2018/6/22.
 */

public class TransferUI extends BaseUI implements TransferP.TransferPface {

    @BindView(R.id.et_transfer_phone)
    EditText et_transfer_phone;

    private TransferP transferP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.ui_home_transfer;
    }

    @Override
    protected void setControlBasis() {
        setTitle("转账");
        transferP = new TransferP(this, getActivity());
    }

    @Override
    protected void prepareData() {

    }

    @OnClick(R.id.tv_transfer_next)
    void next() {
        String phone = et_transfer_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            makeText("对方账号不能为空");
            return;
        }
        transferP.getTransferInfo(R.string.transfer_to, phone);
    }

    @Override
    public void setTransferInfo(PayInfoBean payInfoBean) {
        Intent intent = new Intent(TransferUI.this, ScanResultUI.class);
        intent.putExtra("ui", "transfer");
        intent.putExtra("to_id", payInfoBean.getUb_id());
        intent.putExtra("to_phone", payInfoBean.getUb_phone());
        intent.putExtra("to_nickname", payInfoBean.getUd_nickname());
        startActivity(intent);
        finish();
    }
}
