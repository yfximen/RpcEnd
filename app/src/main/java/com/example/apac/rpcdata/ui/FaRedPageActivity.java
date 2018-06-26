package com.example.apac.rpcdata.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.MainActivity;
import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.adapter.ListRedAdapter;
import com.example.apac.rpcdata.bean.RedPageBean;
import com.example.apac.rpcdata.utils.IdBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FaRedPageActivity extends Activity {


    private TextView home_count_page;
    private TextView home_num_page;
    private String rpc_count;
    private String rpc_num;
    final OkHttpClient client = new OkHttpClient();
    List<IdBean> listid=new ArrayList<IdBean>();


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息",ReturnMessage);
                RedPageBean redPageBean = new Gson().fromJson(ReturnMessage, RedPageBean.class);
                String info = redPageBean.getResult().getInfo();

                Toast.makeText(FaRedPageActivity.this, info, Toast.LENGTH_SHORT).show();

                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 *
                 */

            }

        }

    };
    private String ub_id_1;
    private String ud_nickname_1;
    private String ub_id_2;
    private String ud_nickname_2;
    private String ub_id_3;
    private String ud_nickname_3;
    private String ub_id_4;
    private String ud_nickname_4;
    private String ub_id_5;
    private String ud_nickname_5;
     private ListView red_list;
    private ListRedAdapter adapter;
    private TextView red_packet_ok;
    private String sid;
    private Button btn_red_okme;
    private ImageButton fa_bash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        setContentView(R.layout.activity_fa_red_page);
        Intent intent = getIntent();
        rpc_count = intent.getStringExtra("rpc_count");
        rpc_num = intent.getStringExtra("rpc_num");
        sid = intent.getStringExtra("sid");

        ub_id_1 = intent.getStringExtra("ub_id_1");
        ud_nickname_1 = intent.getStringExtra("ud_nickname_1");

        ub_id_2 = intent.getStringExtra("ub_id_2");
        ud_nickname_2 = intent.getStringExtra("ud_nickname_2");

        ub_id_3 = intent.getStringExtra("ub_id_3");
        ud_nickname_3 = intent.getStringExtra("ud_nickname_3");

        ub_id_4 = intent.getStringExtra("ub_id_4");
        ud_nickname_4 = intent.getStringExtra("ud_nickname_4");

        ub_id_5 = intent.getStringExtra("ub_id_5");
        ud_nickname_5 = intent.getStringExtra("ud_nickname_5");

         initFruits();

         initView();
         initData();


    }

    private void initFruits() {
          IdBean lista=new IdBean(ub_id_1+"1",ud_nickname_1+"1");
          listid.add(lista);

        IdBean listb=new IdBean(ub_id_2+"2",ud_nickname_2+"2");
        listid.add(listb);

        IdBean listc=new IdBean(ub_id_3,ud_nickname_3);
        listid.add(listc);

        IdBean listd=new IdBean(ub_id_4,ud_nickname_4);
        listid.add(listd);

        IdBean liste=new IdBean(ub_id_5,ud_nickname_5);
        listid.add(liste);


    }

    private void initData() {
        home_count_page.setText(rpc_count);
        home_num_page.setText(rpc_num);
        adapter = new ListRedAdapter(FaRedPageActivity.this, R.layout.listview_item,listid);
        red_list.setAdapter(adapter);
        red_packet_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 postRequest("1112c20ad4d76fe97759aa27a0c99bff672110");

               }
        });
        btn_red_okme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest(sid);
            }
        });
        fa_bash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(FaRedPageActivity.this,MainActivity.class));
               finish();
            }
        });
    }

    private void initView() {
        home_count_page = findViewById(R.id.home_count_page);
        home_num_page = findViewById(R.id.home_num_page);
        red_list = findViewById(R.id.red_list);
        red_packet_ok = findViewById(R.id.red_packet_ok);
        btn_red_okme = findViewById(R.id.btn_red_okme);
        fa_bash = findViewById(R.id.fa_bash);

    }


    private void postRequest(String sid)  {
        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()


                .add("sid",sid)
                .add("index","")
                .add("ub_id","")
                .add("uo_long","")
                .add("uo_lat","")
                .add("uo_high","")


                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://rpc.frps.lchtime.cn/index.php/rpc/rpc")
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
