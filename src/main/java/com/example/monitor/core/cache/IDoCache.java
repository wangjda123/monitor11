package com.example.monitor.core.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 缓存接口
 */
@Component
public interface IDoCache {
    <T> T getData(String key, Class<T> tClass);

    JSONObject getData(String key);

    //缺省无限长时间
    void setData(String key, Object data);

    //设置缓存值，cacheTime单位为：秒 0或<0表示无限长
    void setData(String key, Object data, int cacheTime);

    void removeData(String key);

    boolean containsKey(String key);

    void shutDown();
}
