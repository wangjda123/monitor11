package com.example.monitor.pojo;

import javax.persistence.*;

public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称（海门模型，数据分析，采集测试，用户管理）
     */
    @Column(name = "per_name")
    private String perName;

    /**
     * 表达式
     */
    private String expression;
    @Column(name = "parent_id")
    private  Integer parentId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取权限名称（海门模型，数据分析，采集测试，用户管理）
     *
     * @return per_name - 权限名称（海门模型，数据分析，采集测试，用户管理）
     */
    public String getPerName() {
        return perName;
    }

    /**
     * 设置权限名称（海门模型，数据分析，采集测试，用户管理）
     *
     * @param perName 权限名称（海门模型，数据分析，采集测试，用户管理）
     */
    public void setPerName(String perName) {
        this.perName = perName;
    }

    /**
     * 获取表达式
     *
     * @return expression - 表达式
     */
    public String getExpression() {
        return expression;
    }

    /**
     * 设置表达式
     *
     * @param expression 表达式
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}