package io.github.staray.model;

import org.json.JSONArray;

/**
 * Created by Staray-Xu on 2016/4/6.
 * 天气模型类
 * 封装天气预报的模型类
 */
public class Weather {

    /**
     * 天气详情类
     * 包括：黎明、白天、夜晚
     * 返回数据格式为JSONArray
     * eg:
     *  "info":{
            "dawn":[
                "1",
                "多云",
                "14",
                "北风",
                "微风",
                "18:49"
            ],
            "night":[
                "0",
                "晴",
                "10",
                "北风",
                "3-4 级",
                "18:50"
            ],
            "day":[
                "1",
                 "多云",
                "23",
                "北风",
                "4-5 级",
                "05:57"
            ]
        }
     */
    class Info {
        private JSONArray dawn;
        private JSONArray day;
        private JSONArray night;

        public JSONArray getDawn() {
            return dawn;
        }

        public JSONArray getDay() {
            return day;
        }

        public JSONArray getNight() {
            return night;
        }

        public void setDawn(JSONArray dawn) {
            this.dawn = dawn;
        }

        public void setDay(JSONArray day) {
            this.day = day;
        }

        public void setNight(JSONArray night) {
            this.night = night;
        }
    }

    private String date;        // 日期
    private Info info;          // 天气详情
    private String week;        // 星期几
    private String nongli;      // 农历日期

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
