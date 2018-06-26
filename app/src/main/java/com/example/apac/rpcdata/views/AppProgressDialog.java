package com.example.apac.rpcdata.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apac.rpcdata.R;


/**
 * Created by user on 2017/2/16.
 */

public class AppProgressDialog {

    private Dialog progressDialog;
    private TextView textView;

    public void show(Context mContext) {
        if (progressDialog == null) {
            progressDialog = new Dialog(mContext, R.style.MIDialog);
            ProgressBar progressBar = new ProgressBar(mContext, null, android.R.attr.progressBarStyleInverse);
            progressBar.setId(android.R.id.progress);
            textView = new TextView(mContext);
            textView.setTextSize(16);
            textView.setTextColor(Color.WHITE);
            textView.setText("请稍后...");
            RelativeLayout layout = new RelativeLayout(mContext);
            layout.setBackgroundResource(R.drawable.progress_dialog_bg);

            RelativeLayout.LayoutParams progressParams = new RelativeLayout.LayoutParams(-2, -2);
            progressParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layout.addView(progressBar, 0, progressParams);
            RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(-2, -2);
            textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textParams.addRule(RelativeLayout.BELOW, android.R.id.progress);
            layout.addView(textView, 1, textParams);
            progressBar.setIndeterminate(true);
            setCancelable(false);
            progressDialog.setOnKeyListener(keyListener);
            progressDialog.setContentView(layout);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void setProgress(String progress) {
        textView.setText(progress);
    }

    public boolean isShowing() {
        if (progressDialog != null) {
            return progressDialog.isShowing();
        }
        return false;
    }

    private DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {

        @Override
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
            return false;
        }
    };

    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void setCancelable(boolean flag) {
        if (progressDialog == null)
            return;
        // Sets whether this dialog is cancelable with the 返回键
        progressDialog.setCancelable(flag);
        if (flag) {
            progressDialog.setOnKeyListener(null);
        } else {
            progressDialog.setOnKeyListener(keyListener);
        }
    }
}
