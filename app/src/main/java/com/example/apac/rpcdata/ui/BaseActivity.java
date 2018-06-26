package com.example.apac.rpcdata.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;


/**
 * Created by lchtime4 on 2018/5/29.
 */

public class BaseActivity extends Activity implements View.OnClickListener{



    protected Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        ButterKnife.bind(this);



    }






    @Override
    public void onClick(View v) {

    }
}
