package io.github.staray.api;

/**
 * Created by Staray-Xu on 2016/4/7.
 */
public interface Api {

    /**
     * 天气数据接口
     * @Params cityName 城市名称（中文）
     * @return 天气数据
     */
    public ApiResponse<Void> getWeatherData(String cityName);
}
