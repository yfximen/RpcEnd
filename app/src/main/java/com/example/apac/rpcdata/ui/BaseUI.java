package com.example.apac.rpcdata.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apac.rpcdata.MyApplication;
import com.example.apac.rpcdata.R;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * 基类
 */

public abstract class BaseUI extends FragmentActivity {

    protected MyApplication application = null;
    private LinearLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() != -1) {
            setContentView(getLayout());
        }
        ButterKnife.bind(this);
        application = (MyApplication) getApplication();
        setStatus();
        setControlBasis();
        LinearLayout back = (LinearLayout) findViewById(R.id.ll_back);
        if (back != null) {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    back();
                }
            });
        }
        prepareData();
    }

    /**
     * 描述：返回
     */
    protected abstract void back();

    /**
     * 描述：设置控件
     */
    protected abstract int getLayout();

    /**
     * 描述：设置控件基础
     */
    protected abstract void setControlBasis();

    /**
     * 描述：准备数据
     */
    protected abstract void prepareData();

    protected FragmentActivity getActivity() {
        return BaseUI.this;
    }

    /**
     * 描述：设置标题
     *
     * @param text 标题
     */
    protected void setTitle(String text) {
        TextView tv_title = (TextView) findViewById(R.id.title);
        if (tv_title != null) {
            tv_title.setText(text);
        }
    }

    /**
     * 描述:隐藏返回按钮
     */
    protected void backGone() {
        LinearLayout back = (LinearLayout) findViewById(R.id.ll_back);
        if (back != null) {
            back.setVisibility(View.GONE);
        }
    }

    protected void makeText(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置状态栏
     */
    protected void setStatus() {
        LinearLayout ll_home = (LinearLayout) findViewById(R.id.ll_top);
        if (ll_home != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                // 透明导航栏  就是下面三个虚拟按钮
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                ll_home.setVisibility(View.VISIBLE);
                int statusBarHeight = getStatusBarHeight(this);
                //动态的设置隐藏布局的高度
                params = (LinearLayout.LayoutParams) ll_home.getLayoutParams();
                params.height = statusBarHeight;
                ll_home.setLayoutParams(params);
            }
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            //LogHelper.e("Exception", "*****EXCEPTION*****\n", e);
        }

        return 0;

    }

}
