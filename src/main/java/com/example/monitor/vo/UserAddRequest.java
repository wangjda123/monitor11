package com.example.monitor.vo;

import lombok.Data;

@Data
public class UserAddRequest {

    private String name;
    private String passward;
    private Integer roleId;
}
