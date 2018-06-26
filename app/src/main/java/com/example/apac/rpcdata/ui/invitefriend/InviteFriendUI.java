package com.example.apac.rpcdata.ui.invitefriend;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.ReceiptCodeBean;
import com.example.apac.rpcdata.ui.BaseUI;
import com.example.apac.rpcdata.ui.receiptcode.ReceiptCodeP;
import com.example.apac.rpcdata.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 邀请好友
 * Created by user on 2018/6/22.
 */

public class InviteFriendUI extends BaseUI implements ReceiptCodeP.ReceiptCodePface {

    @BindView(R.id.iv_invite_erweima)
    ImageView iv_invite_erweima;

    @BindView(R.id.tv_invite_recommend_adr)
    TextView tv_invite_recommend_adr;

    private ReceiptCodeP receiptCodeP;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.ui_home_invite_friend;
    }

    @Override
    protected void setControlBasis() {
        setTitle("邀请好友");
        receiptCodeP = new ReceiptCodeP(this, getActivity());
    }

    @Override
    protected void prepareData() {
        receiptCodeP.getReceiptCode(R.string.friend);
    }

    @Override
    public void setReceiptCode(ReceiptCodeBean receiptCodeBean) {
        Glide.with(this).load(receiptCodeBean.getUrl_qrcode()).into(iv_invite_erweima);
        tv_invite_recommend_adr.setText(receiptCodeBean.getUrl_web());
    }

    @OnClick(R.id.tv_invite_recommend_adr)
    void recommendAdr() {
        CommonUtils.getTextCopy(this, tv_invite_recommend_adr.getText().toString().trim());
    }

}
