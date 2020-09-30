package com.example.monitor.core.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResultUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    /**
     * 成功且带数据
     **/
    public static Result success(String msg, Object object) {
        return result(ResultCode.SUCCESS.code, msg, object);
    }

    /**
     * 成功不带数据
     **/
    public static Result success(Object object) {
        return success("成功", object);
    }

    /**
     * 指定code返回
     **/
    public static Result result(Integer code) {
        return result(code, "");
    }

    /**
     * 指定code+msg返回
     **/
    public static Result result(Integer code, String msg) {
        return result(code, msg, null);
    }

    /**
     * 最终都调用此方法
     **/
    public static Result result(Integer code, String msg, Object object) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        if (object != null) result.setData(object);
        errorLog(code, msg, object);
        return result;
    }

    //只有error才自动写日志
    private static void errorLog(Integer code, String msg, Object object) {
        if (code == ResultCode.FAIL.code && object instanceof Exception) {
            logger.error(msg, (Exception) object);
        }
    }

    /**
     * 成功但不带数据
     **/
    public static Result success() {
        return success("成功", null);
    }

    /**
     * 失败但不带数据
     **/
    public static Result fail() {
        return fail("失败", null);
    }

    /**
     * 失败
     **/
    public static Result fail(String msg, Object object) {
        return result(ResultCode.FAIL.code, msg, object);
    }

    /**
     * 失败
     **/
    public static Result fail(String msg) {
        return result(ResultCode.FAIL.code, msg, null);
    }

    /**
     * 失败
     **/
    public static Result fail(Exception ex) {
        return fail(ex.getMessage(), ex);
    }
}
