package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserRespone {
    private  Integer id;
    private  String name;
    @Column(name = "role_name")
    private  String roleName;
    private  String passward;
}
