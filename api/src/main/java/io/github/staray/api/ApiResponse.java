package io.github.staray.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Staray-Xu on 2016/4/7.
 * 接口返回结果
 */
public class ApiResponse<T> {
    private String reason;          // 成功/失败原因
    private T result;               // 返回结果
    @SerializedName("error_code")
    private int errorCode;          // 结果代码

    public ApiResponse(int errorCode, String reason) {
        this.errorCode = errorCode;
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
