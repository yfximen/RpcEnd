package com.example.apac.rpcdata.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.MainActivity;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.utils.AppMD5Util;
import com.example.apac.rpcdata.utils.UserBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterAcitivy extends Activity implements View.OnClickListener {

    private EditText tjr_phone;
    private EditText tvpassword;
    private EditText ub_phone;
    private Button btdl;
    private Button btzc;
    private String tjr_phone1;
    private String tvpassword1;
    private String ub_phone1;
    private EditText reg_ud_pwd;

    final OkHttpClient client = new OkHttpClient();

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息", ReturnMessage);
                UserBean userBean = new Gson().fromJson(ReturnMessage, UserBean.class);
                String AA = userBean.getUb_id();
                String info = userBean.getResult().getInfo();
                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 */
                Toast.makeText(RegisterAcitivy.this, info, Toast.LENGTH_SHORT).show();

            }

        }

    };
    private UserBean userBean;
    private String info;
    private ImageView mBackMain;
    /**
     * 注册
     */
    private TextView mRegBtzc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_register_acitivy);
        initView();
        initData();

    }

    private void initData() {

    }

    private void initView() {
        tjr_phone = findViewById(R.id.reg_tjr_phone);
        tvpassword = findViewById(R.id.reg_tvpassword);
        reg_ud_pwd = findViewById(R.id.reg_ud_pwd);
        ub_phone = findViewById(R.id.reg_ub_phone);


        mBackMain = findViewById(R.id.back_main);
        mBackMain.setOnClickListener(this);
        mRegBtzc = (TextView) findViewById(R.id.reg_btzc);
        mRegBtzc.setOnClickListener(this);
    }


    private void postRequest(String tjr_phone1, String tvpassword1, String ub_phone1) {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("tjr_phone", tjr_phone1)
                .add("ud_pwd", tvpassword1)
                .add("ub_phone", ub_phone1)

                .add("sid", "")
                .add("index", "")
                .add("uo_lat", "")
                .add("uo_long", "")
                .add("uo_high", "")
                .add("ub_id", "")
                .add("ud_ol_status", "1")


                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://rpc.frps.lchtime.cn/index.php/user/reg")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        //将服务器响应的参数response.body().string())发送到hanlder中，并更新ui
                        mHandler.obtainMessage(1, response.body().string()).sendToTarget();

                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back_main:
                startActivity(new Intent(RegisterAcitivy.this, MainActivity.class));
                finish();
                break;

            case R.id.reg_btzc:
                Toast.makeText(RegisterAcitivy.this, "ss", Toast.LENGTH_SHORT).show();
                if (tjr_phone.getText().toString().equals("") || reg_ud_pwd.getText().toString().equals("") || ub_phone.getText().toString().equals("") || tvpassword.getText().toString().equals("")) {
                    Toast.makeText(RegisterAcitivy.this, "请完善信息！", Toast.LENGTH_SHORT).show();
                    return;

                } else if (!reg_ud_pwd.getText().toString().equals(tvpassword.getText().toString())) {
                    Toast.makeText(RegisterAcitivy.this, "请确认密码是否一致！", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    //推介人账号
                    tjr_phone1 = tjr_phone.getText().toString();
                    //密码
                    tvpassword1 = tvpassword.getText().toString();

                    AppMD5Util appMD5Util = new AppMD5Util();
                    String s = appMD5Util.MD5Util(tvpassword1);
                    Log.i("MD5", s);
                    //本人账号
                    ub_phone1 = ub_phone.getText().toString();
                    postRequest(tjr_phone1, s, ub_phone1);

                }


                break;
        }
    }
}
