package com.example.apac.rpcdata.ui;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.apac.rpcdata.MyApplication;
import com.example.apac.rpcdata.views.AppProgressDialog;

/**
 * Created by user on 2017/6/7.
 */

public abstract class BasePresenter {

    protected FragmentActivity activity;
    protected MyApplication application;
    private AppProgressDialog progressDialog;

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
        application = (MyApplication) activity.getApplication();
    }

    protected void makeText(final String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    public synchronized void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new AppProgressDialog();
        }
        progressDialog.show(activity);
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismissDialog();
        }
    }

    public synchronized AppProgressDialog getProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new AppProgressDialog();
        }
        return progressDialog;
    }

    public synchronized void setProgress(String context) {
        if (progressDialog != null) {
            progressDialog.setProgress(context);
        }
    }

}
