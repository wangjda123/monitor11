package com.example.monitor.service;
import com.example.monitor.pojo.RolePermission;
import com.example.monitor.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
public interface RolePermissionService extends Service<RolePermission> {
    List<Integer> findPermissionIdListByRoleId(Integer id);
}
