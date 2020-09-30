package com.example.monitor.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleGetResponse {
    /**
     * id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 表达式
     */
    private boolean selected;
    private String expression;
    private Integer parentId;
    private List<RoleGetResponse> children;
}
