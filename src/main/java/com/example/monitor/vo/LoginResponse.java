package com.example.monitor.vo;

import lombok.Data;

import java.util.List;
@Data
public class LoginResponse {

    private int id;
    private String name;
    private String token;
    private List<PermissionListResponse> permissionList;
}
