package io.github.staray.api;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import io.github.staray.api.net.HttpEngine;

/**
 * Created by Staray-Xu on 2016/4/7.
 * Api接口实现
 */
public class ApiImpl implements Api {
    //配置您申请的KEY
    private static final String APP_KEY = "aa0a6082143ed26507b09e5cd4c674a4";

    private HttpEngine httpEngine;

    public ApiImpl() {
        httpEngine = HttpEngine.getInstance();
    }

    @Override
    public ApiResponse<Void> getWeatherData(String cityName) {
        Map<String, String> params = new HashMap<>();
        params.put("key", APP_KEY);
        params.put("cityname", cityName);

        Type type = new TypeToken<ApiResponse<Void>>() {
        }.getType();
        return httpEngine.getData(params, type);
    }
}
