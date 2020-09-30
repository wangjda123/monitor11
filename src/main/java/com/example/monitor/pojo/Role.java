package com.example.monitor.pojo;

import javax.persistence.*;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称（业务员，数据分析员，管理员）
     */
    @Column(name = "role_name")
    private String roleName;

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
     * 获取角色名称（业务员，数据分析员，管理员）
     *
     * @return role_name - 角色名称（业务员，数据分析员，管理员）
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称（业务员，数据分析员，管理员）
     *
     * @param roleName 角色名称（业务员，数据分析员，管理员）
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}