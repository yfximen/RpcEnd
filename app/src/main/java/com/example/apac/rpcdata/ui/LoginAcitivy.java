package com.example.apac.rpcdata.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.MainActivity;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.bean.LoginBean;
import com.example.apac.rpcdata.utils.AppMD5Util;
import com.example.apac.rpcdata.utils.Sp;
import com.example.apac.rpcdata.utils.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 登录模块
 */
public class LoginAcitivy extends BaseActivity {

    @BindView(R.id.et_username)
    EditText mUserNameEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;

    @BindView(R.id.tv_login)
    TextView mLoginTv;
    @BindView(R.id.tv_register)
    TextView mRegisterTv;
    final OkHttpClient client = new OkHttpClient();

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息",ReturnMessage);
                 LoginBean userBean = new Gson().fromJson(ReturnMessage, LoginBean.class);
                 String info = userBean.getResult().getInfo();
                int ub_id = userBean.getUb_id();
                Sp inData = Sp.getInData(LoginAcitivy.this);


                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 *
                 */
                  if (info.equals("登陆成功")){
                      Intent intent = new Intent(LoginAcitivy.this, MainActivity.class);

                      String sid = userBean.getResult().getSid();
                      intent.putExtra("sid",sid);
                      startActivity(intent);
                      finish();
                  }

                Toast.makeText(LoginAcitivy.this, info, Toast.LENGTH_SHORT).show();

            }

        }

    };
    private LoginBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login_acitivy);
        super.onCreate(savedInstanceState);

        mLoginTv.setOnClickListener(this);
        mRegisterTv.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.tv_login: //登录

                String usernameEt = mUserNameEt.getText().toString().trim();
                String passwordEt = mPasswordEt.getText().toString().trim();
                AppMD5Util appMD5Util = new AppMD5Util();
                String passwordEtPwd = appMD5Util.MD5Util(passwordEt);
               Log.i("LoginMD5",passwordEtPwd);
                if(usernameEt.equals("") || passwordEt.equals("")){
                    ToastUtil.showToast(mContext , "请完善信息");

                 } else if (!usernameEt.equals("")&&!passwordEt.equals("")){

                    postRequest(usernameEt,passwordEtPwd);

                }



                break;
            case R.id.tv_register://注册
                Intent intent = new Intent(mContext, RegisterAcitivy.class);
                startActivity(intent);
                break;

        }


    }
    private void postRequest(String username, String password)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()


                .add("sid","")
                .add("index","")
                .add("uo_lat","")
                .add("uo_long","")
                .add("uo_high","")
                .add("ub_phone",username)
                .add("ud_pwd",password)


                .build();
        //发起请求
        final Request request = new Request.Builder()

                .url("http://rpc.frps.lchtime.cn/index.php/user/login")
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


}
