package com.example.monitor.service.impl;

import com.example.monitor.dao.RolePermissionMapper;
import com.example.monitor.pojo.RolePermission;
import com.example.monitor.service.RolePermissionService;
import com.example.monitor.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends AbstractService<RolePermission> implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Integer> findPermissionIdListByRoleId(Integer id) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(id);
        List<RolePermission> rolePermissionList = this.rolePermissionMapper.select(rolePermission);
        List<Integer> permissionIdList = new ArrayList<>();
        for (RolePermission permission : rolePermissionList) {
            permissionIdList.add(permission.getPermissionId());
        }

        return permissionIdList;
    }
}
