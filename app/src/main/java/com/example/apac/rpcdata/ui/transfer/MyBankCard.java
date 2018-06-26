package com.example.apac.rpcdata.ui.transfer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.ui.MyActivity;
import com.example.apac.rpcdata.ui.me.BankCardAdd;

public class MyBankCard extends Activity implements View.OnClickListener {

    private ImageView mGoMain;
    private ImageView mImgAdd;
    private LinearLayout mBankid;
    private TextView mKg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 注意顺序
        setContentView(R.layout.activity_my_bank_card);
        initView();


    }

    private void initView() {
        mGoMain = (ImageView) findViewById(R.id.go_main);
        mGoMain.setOnClickListener(this);
        mImgAdd = (ImageView) findViewById(R.id.img_add);
        mImgAdd.setOnClickListener(this);
        mBankid = (LinearLayout) findViewById(R.id.bankid);
        mKg = (TextView) findViewById(R.id.kg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.go_main:
                 startActivity(new Intent(MyBankCard.this, MyActivity.class));
                 finish();

                break;
            case R.id.img_add:
                startActivity(new Intent(MyBankCard.this,BankCardAdd.class));
                   finish();

                break;
        }
    }
}
