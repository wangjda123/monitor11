package com.example.monitor.core.result;

/**
 * 响应码枚举
 */
public enum ResultCode {
    SUCCESS(1),//成功
    FAIL(0),//失败
    UN_CATCH_ERROR(-1);//未处理异常
    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
