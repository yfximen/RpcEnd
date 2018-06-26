package com.example.apac.rpcdata.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by lchtime4 on 2018/5/30.
 */

public class ToastUtil {





    public static void showToast(Context context , String msg){
        Toast.makeText(context , msg, Toast.LENGTH_SHORT).show();
    }


}
