package io.github.staray.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.github.staray.api.Api;
import io.github.staray.api.ApiImpl;
import io.github.staray.api.ApiResponse;

/**
 * Created by Staray-Xu on 2016/4/8.
 */
public class CacheImpl implements Cache {

    private Context context;
    private Api api;

    public CacheImpl(Context context) {
        this.context = context;
        this.api = new ApiImpl();
    }

    /**
     * 从缓存中获取天气数据
     *
     * @param cityName 城市名称
     * @return 天气数据的JSON格式
     */
    @Override
    public ApiResponse<Void> getWeatherDataCache(String cityName) {
        SharedPreferences preferences = context.getSharedPreferences(cityName, Context.MODE_PRIVATE);
        String result = null;
        if (preferences.contains(cityName)) {
            result = preferences.getString(cityName, null);
            if (null == result) {
                return null;
            }
            Gson gson = new Gson();
            return gson.fromJson(result, new TypeToken<ApiResponse<Void>>() {
            }.getType());
        }
        return null;
    }

    /**
     * 使用SharedPreferences缓存天气数据
     *
     * @param cityName    城市名称
     * @param apiResponse 网络返回数据
     */
    @Override
    public void saveWeatherData2Cache(String cityName, ApiResponse<Void> apiResponse) {
        SharedPreferences preferences = context.getSharedPreferences(cityName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(cityName, apiResponse.toString());
        editor.commit();
    }
}
