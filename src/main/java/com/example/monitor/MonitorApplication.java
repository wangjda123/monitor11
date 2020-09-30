package com.example.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.example.monitor.dao")
@SpringBootApplication
public class MonitorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
    /**
     * 外部tomcat启动
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MonitorApplication.class);
    }
}
