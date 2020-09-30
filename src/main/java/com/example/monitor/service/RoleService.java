package com.example.monitor.service;
import com.example.monitor.DTO.RoleDTO;
import com.example.monitor.DTO.RolePermissionDTO;
import com.example.monitor.pojo.Role;
import com.example.monitor.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
public interface RoleService extends Service<Role> {


    List<Role> list(String name);
    boolean save(RolePermissionDTO rolePermissionDTO);
    void updateStatus(RoleDTO roleDTO);
    boolean update(RolePermissionDTO rolePermissionDTO);
}
