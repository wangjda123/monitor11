package com.example.monitor.DTO;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用戶名
     */
    private String name;
    /**
     * 用户密码
     *
     */
    private String passward;
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;

}
