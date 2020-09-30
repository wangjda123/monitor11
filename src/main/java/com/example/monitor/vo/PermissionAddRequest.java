package com.example.monitor.vo;

import lombok.Data;

@Data
public class PermissionAddRequest {
    /**
     * id
     */
    private Integer id;
    /**
     * 标题
     */
    private String perName;
    /**
     * 表达式
     */
    private String expression;
}
