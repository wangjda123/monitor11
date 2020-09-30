package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class PermissionDTO {
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
    private Integer parentId;
}
