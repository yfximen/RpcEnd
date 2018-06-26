package com.example.apac.rpcdata.ui.receiptcode;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.ReceiptCodeBean;
import com.example.apac.rpcdata.ui.BaseUI;

import butterknife.BindView;

/**
 * 收款码
 * Created by user on 2018/6/21.
 */

public class ReceiptCodeUI extends BaseUI implements ReceiptCodeP.ReceiptCodePface {

    @BindView(R.id.iv_receipt_code)
    ImageView iv_receipt_code;

    private ReceiptCodeP receiptCodeP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.ui_home_receipt_code;
    }

    @Override
    protected void setControlBasis() {
        setTitle("收款码");
        receiptCodeP = new ReceiptCodeP(this, getActivity());
    }

    @Override
    protected void prepareData() {
        receiptCodeP.getReceiptCode(R.string.pay_qrcode);
    }

    @Override
    public void setReceiptCode(ReceiptCodeBean receiptCodeBean) {
        Glide.with(this).load(receiptCodeBean.getUrl_qrcode()).into(iv_receipt_code);
    }
}
