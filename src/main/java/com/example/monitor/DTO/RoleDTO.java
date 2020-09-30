package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class RoleDTO {

    private Integer id;

    /**
     * 角色名称（业务员，数据分析员，管理员）
     */
    @Column(name = "role_name")
    private String roleName;
}
