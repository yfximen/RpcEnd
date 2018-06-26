package com.example.apac.rpcdata.ui.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.CarBang;
import com.example.apac.rpcdata.bean.RegistBean;
import com.example.apac.rpcdata.utils.AppMD5Util;
import com.example.apac.rpcdata.utils.MyOkhttp;
import com.example.apac.rpcdata.utils.Sp;
import com.example.apac.rpcdata.utils.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class AmentActivity extends AppCompatActivity implements View.OnClickListener {

    /**  */
    private EditText mEtUsername;
    /**
     * 输入新密码
     */
    private EditText mEtPasswordOld;
    /**
     * 确认新密码
     */
    private EditText mEtPasswordYong;
    /**
     * 确认修改
     */
    private TextView mTvTransferRest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ament);
        initView();
    }

    private void initView() {
        mEtUsername = (EditText) findViewById(R.id.et_username);
        mEtPasswordOld = (EditText) findViewById(R.id.et_password_old);
        mEtPasswordYong = (EditText) findViewById(R.id.et_password_yong);
        mTvTransferRest = (TextView) findViewById(R.id.tv_transfer_rest);
        mTvTransferRest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_transfer_rest:
                Map<String,String> map=new HashMap<String, String>();
                String sid = Sp.getInData(AmentActivity.this).getSid();

                map.put("sid",sid);
                map.put("index","");
                map.put("uo_lat","");
                map.put("uo_long","");
                map.put("uo_high","");

                AppMD5Util appMD5Util=new AppMD5Util();
                String restPwd = appMD5Util.MD5Util(mEtPasswordYong.getText().toString().trim());


                map.put("new_pwd",restPwd);




                String url="http://rpc.frps.lchtime.cn/index.php/user/reset";

                MyOkhttp.postAsync(url, map, new MyOkhttp.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {

                        ToastUtil.showToast(AmentActivity.this,request.toString());

                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        Gson gson=new Gson();
                        RegistBean registBean = gson.fromJson(result, RegistBean.class);
                        String info = registBean.getResult().getInfo();
                        ToastUtil.showToast(AmentActivity.this,info);
                    }
                });


                break;
        }
    }
}
