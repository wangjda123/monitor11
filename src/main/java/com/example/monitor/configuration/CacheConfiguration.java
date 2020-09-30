package com.example.monitor.configuration;

;
import com.example.monitor.core.cache.DoCacheEhcache;
import com.example.monitor.core.cache.IDoCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class CacheConfiguration {
    @Bean
    IDoCache cache() throws MalformedURLException {
        return new DoCacheEhcache();//缺省使用Ehcache，如果使用redis，需要修改这里
    }
}
