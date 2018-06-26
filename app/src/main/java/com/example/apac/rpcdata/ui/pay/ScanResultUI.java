package com.example.apac.rpcdata.ui.pay;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.PayInfoBean;
import com.example.apac.rpcdata.ui.BaseUI;
import com.example.apac.rpcdata.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL;

/**
 * 支付扫描结果  转账
 * Created by user on 2018/6/21.
 */

public class ScanResultUI extends BaseUI implements ScanResultP.ScanResultPface {

    //对方账号
    @BindView(R.id.tv_scan_result_phone)
    TextView tv_scan_result_phone;
    //余额
    @BindView(R.id.et_scan_result_mount)
    EditText et_scan_result_mount;
    //对方昵称
    @BindView(R.id.tv_scan_result_nickname)
    TextView tv_scan_result_nickname;
    //密码
    @BindView(R.id.et_scan_result_paypwd)
    EditText et_scan_result_paypwd;

    @BindView(R.id.tv_scan_result_pay)
    TextView tv_scan_result_pay;

    private ScanResultP scanResultP;
    private Intent intent;
    private String ui;
    private String to_ub_id = "";

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.ui_home_scan_result;
    }

    @Override
    protected void setControlBasis() {
        intent = getIntent();
        ui = intent.getStringExtra("ui");
        if ("pay".equals(ui)) {
            setTitle("支付");
            tv_scan_result_pay.setText("确认支付");
        } else if ("transfer".equals(ui)) {
            setTitle("转账");
            tv_scan_result_pay.setText("确认转账");
        }
        et_scan_result_mount.setInputType(InputType.TYPE_CLASS_NUMBER | TYPE_NUMBER_FLAG_DECIMAL);
        scanResultP = new ScanResultP(this, getActivity());
    }

    @Override
    protected void prepareData() {
        if ("pay".equals(ui)) {
            String url = intent.getStringExtra("url");
            scanResultP.getPayInfo(R.string.pay_to, url);
        } else if ("transfer".equals(ui)) {
            to_ub_id = intent.getStringExtra("to_id");
            tv_scan_result_phone.setText(intent.getStringExtra("to_phone"));
            tv_scan_result_nickname.setText(intent.getStringExtra("to_nickname"));
        }
    }

    @Override
    public void setPayInfo(PayInfoBean payInfoBean) {
        if (payInfoBean != null) {
            to_ub_id = payInfoBean.getUb_id();
            tv_scan_result_phone.setText(payInfoBean.getUb_phone());
            tv_scan_result_nickname.setText(payInfoBean.getUd_nickname());
        }
    }

    @OnClick(R.id.tv_scan_result_pay)
    void pay() {
        String phone = tv_scan_result_phone.getText().toString().trim();
        String mount = et_scan_result_mount.getText().toString().trim();
        String payPwd = et_scan_result_paypwd.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            makeText("对方账户不能为空");
            return;
        }
        if (TextUtils.isEmpty(mount)) {
            makeText("余额不能为空");
            return;
        }
        int posDot = mount.indexOf(".");
        if (posDot == mount.length() - 1 || posDot == 0) {
            makeText("输入的余额不正确");
            return;
        }
        if (Double.parseDouble(mount) <= 0) {
            makeText("输入的余额不正确");
            return;
        }
        if (TextUtils.isEmpty(payPwd)) {
            makeText("支付密码不能为空");
            return;
        }
        scanResultP.getPay(R.string.pay, to_ub_id, phone, mount, CommonUtils.getMD5Value(payPwd));
    }

    @Override
    public void setPay() {
        if ("pay".equals(ui)) {
            makeText("支付成功");
        } else if ("transfer".equals(ui)) {
            makeText("转账成功");
        }
        finish();
    }
}
