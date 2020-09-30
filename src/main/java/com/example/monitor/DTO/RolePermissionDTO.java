package com.example.monitor.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RolePermissionDTO {
    private Integer id;
    private String RoleName;
    private List<Integer> permissionIdList;
}
