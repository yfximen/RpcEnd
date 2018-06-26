package com.example.apac.rpcdata.ui.pay;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.apac.rpcdata.R;
import com.example.apac.rpcdata.ui.BaseUI;

import java.io.File;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * 支付扫描
 * Created by user on 2018/6/21.
 */

public class ScanUI extends BaseUI implements QRCodeView.Delegate {


    @BindView(R.id.zxingview)
    QRCodeView mQRCodeView;

    @Override
    protected void back() {
        finish();
    }

    @Override
    protected int getLayout() {
        return R.layout.ui_home_scan;
    }


    @Override
    protected void setControlBasis() {
        setTitle("支付");
        requestPermissions();
        mQRCodeView.setDelegate(this);
    }

    @Override
    protected void prepareData() {

    }

    private void requestPermissions() {
        if (ActivityCompat.checkSelfPermission(ScanUI.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(ScanUI.this, "请在手机设置中打开相机的权限", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mQRCodeView.startCamera();
            mQRCodeView.showScanRect();
            mQRCodeView.startSpotAndShowRect();
            mQRCodeView.changeToScanQRCodeStyle();
        }
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        mQRCodeView.startSpot();
        if (result.contains("index.php")) {
            Toast.makeText(this, "此扫描仅支持红包链支付", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ScanUI.this, ScanResultUI.class);
            intent.putExtra("ui", "pay");
            intent.putExtra("url", result);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "请在手机设置中打开相机的权限", Toast.LENGTH_SHORT).show();
    }

}
