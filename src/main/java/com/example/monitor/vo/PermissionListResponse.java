package com.example.monitor.vo;

import lombok.Data;

import java.util.List;

@Data
public class PermissionListResponse {
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
    private Integer parentId;
    private List<PermissionListResponse> children;
}
