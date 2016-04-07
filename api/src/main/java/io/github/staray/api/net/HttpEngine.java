package io.github.staray.api.net;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Staray-Xu on 2016/4/6.
 */
public class HttpEngine {
    private final static String SERVER_URL = "http://op.juhe.cn/onebox/weather/query";
    private final static String REQUEST_METHOD = "GET";
    private final static String ENCODE_TYPE = "UTF-8";
    private final static int TIME_OUT = 20000;

    private static HttpEngine httpEngine;

    public static HttpEngine getInstance() {
        if (null == httpEngine) {
            httpEngine = new HttpEngine();
        }
        return httpEngine;
    }

    public <T> T getData(Map<String, String> params, Type typeOfT) {
        HttpURLConnection httpURLConnection = getConnection(params);
        if (null == httpURLConnection) {
            return null;
        }
        try {
            if (httpURLConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = httpURLConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte buffer[] = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1) {
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                httpURLConnection.disconnect();
                // 返回字符串
                String result = new String(baos.toByteArray());

                Gson gson = new Gson();
                return gson.fromJson(result, typeOfT);
            } else {
                httpURLConnection.disconnect();
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private HttpURLConnection getConnection(Map<String, String> params) {
        HttpURLConnection httpURLConnection = null;
        try {
            String strUrl = SERVER_URL + "?" + urlencode(params);
            URL url = new URL(strUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(TIME_OUT);
            httpURLConnection.setReadTimeout(TIME_OUT);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Response-Type", "json");
            httpURLConnection.setInstanceFollowRedirects(false);
        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return httpURLConnection;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : params.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
        return sb.toString();
    }
}
