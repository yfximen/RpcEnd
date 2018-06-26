package com.example.apac.rpcdata.utils;

import android.widget.Toast;

import com.example.apac.rpcdata.MyApplication;
import com.example.apac.rpcdata.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;


public class NetworkUtils {

    private static NetworkUtils networkUtils;
    protected MyApplication application;
    private int index = 1;

    public static NetworkUtils getNetworkUtils() {
        if (networkUtils == null) {
            networkUtils = new NetworkUtils();
        }
        return networkUtils;
    }

    public void setApplication(MyApplication application) {
        this.application = application;
    }

    public String getUrl(int id) {
        return application.getResources().getString(R.string.service_host_address).concat(application.getResources().getString(id));
    }

    protected void makeText(final String content) {
        Toast.makeText(application.getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    /**
     * post请求
     */
    public void send(int id, Map<String, String> params, final StringCallback stringCallback) {
        String url = getUrl(id);
        PostFormBuilder builder = OkHttpUtils.post().url(url);
        builder.addParams("sid", "1231c81e728d9d4c2f636f067f89cc148622c");
        builder.addParams("index", (index++) + "");
        builder.addParams("uo_long", "");
        builder.addParams("uo_lat", "");
        builder.addParams("uo_high", "");
        builder.addParams("ub_id", "");
        if (params != null) {
            for (String key : params.keySet()) {
                builder.addParams(key, params.get(key));
            }
        }
        builder.build().execute(stringCallback);
    }

}
