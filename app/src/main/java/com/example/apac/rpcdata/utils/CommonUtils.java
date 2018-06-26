package com.example.apac.rpcdata.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.Toast;

import java.security.MessageDigest;

/**
 * Created by user on 2018/6/22.
 */

public class CommonUtils {

    /**
     * 文本复制
     *
     * @param context
     * @param content
     */
    public static void getTextCopy(Context context, String content) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", content);
        //将ClipData内容放到系统剪贴板里
        cm.setPrimaryClip(mClipData);
        Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
    }

    //MD5加密
    public static String getMD5Value(String data) {
        //获得java提供信息摘要算法加密功能类的一个实例
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        //将获取到的string转换成byte数组
        char[] chars = data.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        //获取MD5计算后的byte值
        byte[] md5byte = md5.digest(bytes);
        //将获取到的byte值转回string
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < md5byte.length; i++) {
            //使用0xff保持转值不出错
            int val = ((int) md5byte[i]) & 0xff;
            if (val < 16) {
                stringBuffer.append("0");
            } else {
                stringBuffer.append(Integer.toHexString(val));
            }
        }
        return stringBuffer.toString();
    }

}
