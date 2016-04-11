package io.github.staray.cache;

import io.github.staray.api.ApiResponse;

/**
 * Created by Staray-Xu on 2016/4/8.
 * 天气数据缓存接口
 */
public interface Cache {

    /**
     * 获取天气情况缓存数据
     *
     * @param cityName 城市名称
     * @return 天气参数
     */
    public ApiResponse<Void> getWeatherDataCache(String cityName);

    /**
     * * 缓存天气数据
     *
     * @param apiResponse 网络返回数据
     * @param cityName    城市名称
     */
    public void saveWeatherData2Cache(String cityName, ApiResponse<Void> apiResponse);
}
