package com.example.apac.rpcdata.ui.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.api.Api;
import com.example.apac.rpcdata.bean.CarBang;
import com.example.apac.rpcdata.ui.transfer.MyBankCard;
import com.example.apac.rpcdata.utils.MyOkhttp;
import com.example.apac.rpcdata.utils.Sp;
import com.example.apac.rpcdata.utils.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class BankCardAdd extends Activity implements View.OnClickListener {

    private ImageView mGoMain;
    /**
     * 我的银行卡
     */
    private TextView mMecar;
    /**
     * 请输入银行卡号
     */
    private EditText uc_card;
    /**
     * 请输入本人姓名
     */
    private EditText uc_name;
    /**  */
    private EditText uc_khh;
    private EditText uc_addr;
    /**
     * 绑定银行卡
     */
    private TextView mAddBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 注意顺序

        setContentView(R.layout.activity_bank_card_add);
         initView();
        initData();

    }

    private void initData() {
        mMecar.setText("填写银行卡信息");

    }

    private void initView() {
        mGoMain = (ImageView) findViewById(R.id.go_main);
        mGoMain.setOnClickListener(this);
        mMecar = (TextView) findViewById(R.id.mecar);
        //银行卡号
        uc_card = (EditText) findViewById(R.id.et_scan_result_yhks);
        //本人姓名
        uc_name = (EditText) findViewById(R.id.et_scan_result_names);
        //开户行
        uc_khh = (EditText) findViewById(R.id.tv_scan_result_openbank);
        //开户行地址
        uc_addr = (EditText) findViewById(R.id.et_scan_result_address);

        mAddBank = (TextView) findViewById(R.id.add_bank);
        mAddBank.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.go_main:
                startActivity(new Intent(BankCardAdd.this, MyBankCard.class));
                finish();
                break;
            case R.id.add_bank:
                Map<String,String> map=new HashMap<String, String>();
                String sid = Sp.getInData(BankCardAdd.this).getSid();
                map.put("sid",sid);
                map.put("index","");
                map.put("uo_lat","");
                map.put("uo_long","");
                map.put("uo_high","");

                map.put("uc_card",uc_card.getText().toString().trim());
                map.put("uc_name",uc_name.getText().toString().trim());

                map.put("uc_khh",uc_khh.getText().toString().trim());
                map.put("uc_addr",uc_addr.getText().toString().trim());
                map.put("action","1");
                map.put("uc_type","0");



                 String url="http://rpc.frps.lchtime.cn/index.php/caiwu/bund";

                MyOkhttp.postAsync(url, map, new MyOkhttp.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                        ToastUtil.showToast(BankCardAdd.this,request.toString());

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                         Gson gson=new Gson();
                        CarBang carBang = gson.fromJson(result, CarBang.class);
                        String info = carBang.getResult().getInfo();
                        ToastUtil.showToast(BankCardAdd.this,info);
                    }
                });


                break;
        }
    }
}
