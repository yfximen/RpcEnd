package com.example.apac.rpcdata.utils;

/**
 * Created by zbb on 2017/12/28.
 */

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkhttp {
    /**
     * 静态实例
     */
    private static MyOkhttp sOkHttpManager;

    /**
     * okhttpclient实例
     */
    private OkHttpClient mClient;

    /**
     * 因为我们请求数据一般都是子线程中请求，在这里我们使用了handler
     */
    private Handler mHandler;

    /**
     * 构造方法
     */
    private MyOkhttp() {

        //mClient = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

/**
 * 在这里直接设置连接超时.读取超时，写入超时
 */
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);


        //添加拦截器
        //builder.addInterceptor(new LoggingInterceptor());

        mClient = builder.build();

/**
 * 如果是用的3.0之前的版本 使用以下直接设置连接超时.读取超时，写入超时
 */

//client.setConnectTimeout(10, TimeUnit.SECONDS);
//client.setWriteTimeout(10, TimeUnit.SECONDS);
//client.setReadTimeout(30, TimeUnit.SECONDS);


/**
 * 初始化handler
 */
        mHandler = new Handler(Looper.getMainLooper());
    }


    /**
     * 单例模式 获取OkHttp实例
     *
     * @return
     */
    public static MyOkhttp getInstance() {

        if (sOkHttpManager == null) {
            sOkHttpManager = new MyOkhttp();
        }
        return sOkHttpManager;
    }

//-------------------------同步的方式请求数据--------------------------

    /**
     * 对外提供的get方法,同步的方式
     *
     * @param url 传入的地址
     * @return
     */
    public static Response getSync(String url) {

//通过获取到的实例来调用内部方法
        return sOkHttpManager.inner_getSync(url);
    }

    /**
     * GET方式请求的内部逻辑处理方式，同步的方式
     *
     * @param url
     * @return
     */
    private Response inner_getSync(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
//同步请求返回的是response对象
            response = mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 对外提供的同步获取String的方法
     *
     * @param url
     * @return
     */
    public static String getSyncString(String url) {
        return sOkHttpManager.inner_getSyncString(url);
    }


    /**
     * 同步方法
     */
    private String inner_getSyncString(String url) {
        String result = null;
        try {
/**
 * 把取得到的结果转为字符串，这里最好用string()
 */
            result = inner_getSync(url).body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //-------------------------异步的方式请求数据--------------------------
    public static void getAsync(String url, DataCallBack callBack) {
        getInstance().inner_getAsync(url, callBack);
    }

    /**
     * 内部逻辑请求的方法
     *
     * @param url
     * @param callBack
     * @return
     */
    private void inner_getAsync(String url, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();

        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                deliverDataSuccess(result, callBack);
            }
        });
    }


    /**
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
/**
 * 在这里使用异步处理
 */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    /**
     * 分发成功的时候调用
     *
     * @param result
     * @param callBack
     */
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
/**
 * 在这里使用异步线程处理
 */
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        String results = decodeUnicode(result);
                        callBack.requestSuccess(results);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 数据回调接口
     */
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(String result) throws Exception;
    }

//-------------------------提交表单--------------------------

    public static void postAsync(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().inner_postAsync(url, params, callBack);
    }

    private void inner_postAsync(String url, Map<String, String> params, final DataCallBack callBack) {

        RequestBody requestBody = null;
        if (params == null) {
            params = new HashMap<String, String>();
        }

/**
 * 如果是3.0之前版本的，构建表单数据是下面的一句
 */
//FormEncodingBuilder builder = new FormEncodingBuilder();

/**
 * 3.0之后版本
 */
        FormBody.Builder builder = new FormBody.Builder();

/**
 * 在这对添加的参数进行遍历，map遍历有四种方式，如果想要了解的可以网上查找
 */
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey().toString();
            String value = null;
/**
 * 判断值是否是空的
 */
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }
/**
 * 把key和value添加到formbody中
 */
            builder.add(key, value);
        }
        requestBody = builder.build();
//结果返回
// 请求对象
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                deliverDataSuccess(result, callBack);
            }


        });
    }


    //-------------------------文件下载--------------------------
    public static void downloadAsync(String url, String desDir, DataCallBack callBack) {
        getInstance().inner_downloadAsync(url, desDir, callBack);
    }

    /**
     * 下载文件的内部逻辑处理类
     *
     * @param url      下载地址
     * @param desDir   目标地址
     * @param callBack
     */
    private void inner_downloadAsync(final String url, final String desDir, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

/**
 * 在这里进行文件的下载处理
 */
                InputStream inputStream = null;
                FileOutputStream fileOutputStream = null;
                try {
//文件名和目标地址
                    File file = new File(desDir, getFileName(url));
//把请求回来的response对象装换为字节流
                    inputStream = response.body().byteStream();
                    fileOutputStream = new FileOutputStream(file);
                    int len = 0;
                    byte[] bytes = new byte[2048];
//循环读取数据
                    while ((len = inputStream.read(bytes)) != -1) {
                        fileOutputStream.write(bytes, 0, len);
                    }
//关闭文件输出流
                    fileOutputStream.flush();
//调用分发数据成功的方法
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (IOException e) {
//如果失败，调用此方法
                    deliverDataFailure(request, e, callBack);
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                }
            }

        });
    }

    /**
     * 根据文件url获取文件的路径名字
     *
     * @param url
     * @return
     */
    private String getFileName(String url) {
        int separatorIndex = url.lastIndexOf("/");
        String path = (separatorIndex < 0) ? url : url.substring(separatorIndex + 1, url.length());
        return path;
    }
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

}
