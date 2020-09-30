package com.example.monitor.service;
import com.example.monitor.DTO.PermissionDTO;
import com.example.monitor.pojo.Permission;
import com.example.monitor.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
public interface PermissionService extends Service<Permission> {
    List<PermissionDTO> list();

    Integer findParentIdByTitle(String perName);
    void save(PermissionDTO permissionDTO);
    void update(PermissionDTO permissionDTO);
}
