package com.example.monitor.configuration.interceptor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @version 1.0
 * @date 2020/5/20 13:00
 */
@Component

public class Config {
    /*
    当前站点域名
     */
    @Value("${com.config.domain}")
    private String doMain;

}
