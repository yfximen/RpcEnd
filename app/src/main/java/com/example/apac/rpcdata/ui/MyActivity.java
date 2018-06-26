package com.example.apac.rpcdata.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.MainActivity;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.ui.me.AmentActivity;
import com.example.apac.rpcdata.ui.transfer.MyBankCard;
import com.example.apac.rpcdata.utils.ToastUtil;

public class MyActivity extends Activity implements View.OnClickListener {

    private ListView listview;
    /**
     * 东方不败
     */
    private TextView mMyName;
    /**
     * 信用等级:
     */
    private TextView mMyGrade;
    private ImageView mNc1;
    private ImageView mNc2;
    private ImageView mNc3;
    private ImageView mNc4;
    private ImageView mNc5;
    private ImageView mNc6;
    private ImageView mNc7;
    private ImageView mMe8;
    private ImageView mNc9;
    private ImageView mNc10;
    private ImageView mGoMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my);
        initView();


    }


    private void initView() {
        mMyName = (TextView) findViewById(R.id.my_name);
        mMyGrade = (TextView) findViewById(R.id.my_grade);
        mNc1 = (ImageView) findViewById(R.id.nc_1);
        mNc1.setOnClickListener(this);
        mNc2 = (ImageView) findViewById(R.id.nc_2);
        mNc2.setOnClickListener(this);
        mNc3 = (ImageView) findViewById(R.id.nc_3);
        mNc3.setOnClickListener(this);
        mNc4 = (ImageView) findViewById(R.id.nc_4);
        mNc4.setOnClickListener(this);
        mNc5 = (ImageView) findViewById(R.id.nc_5);
        mNc5.setOnClickListener(this);
        mNc6 = (ImageView) findViewById(R.id.nc_6);
        mNc6.setOnClickListener(this);
        mNc7 = (ImageView) findViewById(R.id.nc_7);
        mNc7.setOnClickListener(this);
        mMe8 = (ImageView) findViewById(R.id.me_8);
        mMe8.setOnClickListener(this);
        mNc9 = (ImageView) findViewById(R.id.nc_9);
        mNc9.setOnClickListener(this);
        mNc10 = (ImageView) findViewById(R.id.nc_10);
        mNc10.setOnClickListener(this);
        mGoMain = (ImageView) findViewById(R.id.go_main);
        mGoMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.nc_1:
               // Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();


                break;
            case R.id.nc_2:
                //ToastUtil.showToast(MyActivity.this,"sssss");
                Intent intent=new Intent(MyActivity.this, MyBankCard.class);
                startActivity(intent);
                finish();

                break;//修改密码
            case R.id.nc_3:
                Intent ament=new Intent(MyActivity.this, AmentActivity.class);
                startActivity(ament);
                finish();
                break;
            case R.id.nc_4:
                break;
            case R.id.nc_5:
                break;
            case R.id.nc_6:
                break;
            case R.id.nc_7:
                break;
            case R.id.me_8:
                break;
            case R.id.nc_9:
                break;
            case R.id.nc_10:

                break;
            case R.id.go_main:
                startActivity(new Intent(MyActivity.this, MainActivity.class));
                finish();
                break;
        }
    }

}
