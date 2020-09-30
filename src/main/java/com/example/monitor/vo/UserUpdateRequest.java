package com.example.monitor.vo;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private  Integer id;
    private  String name;
    private  String passward;
    private  Integer roleId;
}
