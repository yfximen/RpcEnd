package com.example.apac.rpcdata;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.apac.rpcdata.bean.HomeBean;
import com.example.apac.rpcdata.ui.BalanceActivity;
import com.example.apac.rpcdata.ui.FaRedPageActivity;
import com.example.apac.rpcdata.ui.MyActivity;
import com.example.apac.rpcdata.ui.PayMentActivity;
import com.example.apac.rpcdata.ui.RedPacketActivity;
import com.example.apac.rpcdata.ui.TransferActivity;
import com.example.apac.rpcdata.ui.balancerecord.BalanceRecordUI;
import com.example.apac.rpcdata.ui.invitefriend.InviteFriendUI;
import com.example.apac.rpcdata.ui.pay.ScanUI;
import com.example.apac.rpcdata.ui.receiptcode.ReceiptCodeUI;
import com.example.apac.rpcdata.ui.transfer.TransferUI;
import com.example.apac.rpcdata.utils.Sp;
import com.example.apac.rpcdata.utils.ToastUtil;
//import com.uuzuche.lib_zxing.activity.CaptureActivity;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.google.gson.Gson;
 import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity {
     
     private static final int REQUEST_CODE = 0x1111;
     private ArrayList<String> list_path;
     private ArrayList<String> list_title;
     final OkHttpClient client = new OkHttpClient();
     private String sid;
     private ViewSwitcher mViewSwitcher;
     private HomeBean homeBean;
     private int rpc_level;

     private List<HomeBean.RpcUpBean> rpc_up;

     private TextView red_packet_ok;
    private String rpc_count;
    private String rpc_num;
    private TextView home_count_page1;
    private TextView home_num_page1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){


        @Override
        public void handleMessage(Message msg){
            if(msg.what==1){
                String ReturnMessage = (String) msg.obj;
                Log.i("获取的返回信息",ReturnMessage);
                homeBean = new Gson().fromJson(ReturnMessage, HomeBean.class);
                String info = homeBean.getResult().getInfo();

                //判断用户的状态，0为未完成发红包，1为完成
                rpc_level = homeBean.getRpc_level();

                  rpc_num = homeBean.getRpc_num();
                  rpc_count = homeBean.getRpc_count();


                home_count_page1.setText(rpc_count);
                home_num_page1.setText(rpc_num);

                //得到用户要发送红包的用户id
                rpc_up = homeBean.getRpc_up();

                /***
                 * 在此处可以通过获取到的Msg值来判断
                 * 给出用户提示注册成功 与否，以及判断是否用户名已经存在
                 *
                 */


            }

        }

    };
    
    
    
    //定义图标数组
    private int[] imageRes = {
            R.mipmap.tc3_2x,
            R.mipmap.z1_2x,
            R.mipmap.z2_2x,
            R.mipmap.z5_2x,
            R.mipmap.tc3_2x,
            R.mipmap.z3_2x,
            R.mipmap.z6_2x,
            R.mipmap.z7_2x,
            R.mipmap.z8_2x,
    };

    //定义图标下方的名称数组
    private String[] name = {
            "红包链",
            "支付",
            "收款码",
            "转账",
            "余额记录",
            "商城",
            "邀请好友",
            "数字资产",
            "我的"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);

            Intent intent = this.getIntent();
        sid = intent.getStringExtra("sid");
         Sp.getInData(MainActivity.this).setSid(sid);
        initView();

        initData();
        initNine();
     }

    private void initData() {

        postRequest(sid);

    }

    private void initNine() {
        GridView gridview = (GridView) findViewById(R.id.gridview);
        int length = imageRes.length;

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i]);//添加图像资源的ID
            map.put("ItemText", name[i]);//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.item_nine,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.img_shoukuan, R.id.txt_shoukuan});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_LONG).show();
                if (rpc_level==0){
                    //确认发送红包
                    red_packet_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, FaRedPageActivity.class);
                            intent.putExtra("rpc_count", rpc_count);
                            intent.putExtra("rpc_num", rpc_num);
                            intent.putExtra("sid", sid);
                            Log.i("sid--------",sid);
                            Log.i("ub_id_1",rpc_up.get(0).getUb_id());
                            Log.i("ub_id_2",rpc_up.get(1).getUb_id());
                            Log.i("ub_id_3",rpc_up.get(2).getUb_id());
                            Log.i("ub_id_4",rpc_up.get(3).getUb_id());
                            Log.i("ub_id_5",rpc_up.get(4).getUb_id());

                            intent.putExtra("ub_id_1", rpc_up.get(0).getUb_id());
                            intent.putExtra("ud_nickname_1", rpc_up.get(0).getUd_nickname());

                            intent.putExtra("ub_id_2", rpc_up.get(1).getUb_id());
                            intent.putExtra("ud_nickname_2", rpc_up.get(1).getUd_nickname());

                            intent.putExtra("ub_id_3", rpc_up.get(2).getUb_id());
                            intent.putExtra("ud_nickname_3", rpc_up.get(2).getUd_nickname());

                            intent.putExtra("ub_id_4", rpc_up.get(3).getUb_id());
                            intent.putExtra("ud_nickname_4", rpc_up.get(3).getUd_nickname());

                            intent.putExtra("ub_id_5", rpc_up.get(4).getUb_id());
                            intent.putExtra("ud_nickname_5", rpc_up.get(4).getUd_nickname());

                            startActivity(intent);



                        }
                    });

                    return;
                }else if (position==0){



                }else if (position==1){

//                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//                    startActivityForResult(intent, REQUEST_CODE);
                      Intent intent=new Intent(MainActivity.this, ScanUI.class);
                      startActivity(intent);

                }else if (position==2){
//                    ToastUtil.showToast(MainActivity.this,"敬请期待");
//                    finish();
                    Intent intent=new Intent(MainActivity.this, ReceiptCodeUI.class);
                    startActivity(intent);

                }else if (position==3){
//                     startActivity(new Intent(MainActivity.this,TransferActivity.class));
//                    finish();
                    Intent intent=new Intent(MainActivity.this, TransferUI.class);
                    startActivity(intent);

                }else if (position==4){
//                    startActivity(new Intent(MainActivity.this,BalanceActivity.class));
//                    finish();
                    Intent intent=new Intent(MainActivity.this, BalanceRecordUI.class);
                    startActivity(intent);
                }else if (position==6){
                    Intent intent=new Intent(MainActivity.this, InviteFriendUI.class);
                    startActivity(intent);
                }else if (position==8){

                    startActivity(new Intent(MainActivity.this,MyActivity.class));


                }



            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        postRequest(sid);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(MainActivity.this, PayMentActivity.class));
//                    finish();
//
//
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                }
//            }
        }
    }




    private void initView() {

        home_count_page1 = findViewById(R.id.home_count_page1);
        home_num_page1 = findViewById(R.id.home_num_page1);


        red_packet_ok = findViewById(R.id.red_packet_ok);

            mViewSwitcher = findViewById(R.id.viewswitcher);
            mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                @Override
                public View makeView() {
                    return getLayoutInflater().inflate(R.layout.item_viewswitch, null);
                }
            });






//设置切入动画
            TranslateAnimation animationTop = new TranslateAnimation(0, 0, 200, 0);
            animationTop.setFillAfter(true);
            animationTop.setDuration(200);
            //设置切出动画
            TranslateAnimation animationBottom = new
                    TranslateAnimation(0, 0, 0, -200);
            animationBottom.setFillAfter(true);
            animationBottom.setDuration(200);
            mViewSwitcher.setInAnimation(animationTop);
            mViewSwitcher.setOutAnimation(animationBottom);
            mTimer.schedule(mTimerTask, 1000, 4000);

        



    }
    //滚动
    Timer mTimer = new Timer();
    TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            mHandler1.sendEmptyMessage(0);
        }
    };
    Handler mHandler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ((TextView) mViewSwitcher.getNextView().findViewById(R.id.viewswitcher_tv_one)).setText("中包赠送价值200元的套餐");
            mViewSwitcher.showNext();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler1.removeCallbacksAndMessages(null);
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

                .url("http://rpc.frps.lchtime.cn/index.php/rpc/index")
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
