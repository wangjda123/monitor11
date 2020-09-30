package com.example.monitor.dao;

import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends Mapper<Permission> {
    @Select("           SELECT p.expression FROM\n" +
            "            user AS u\n" +
            "            LEFT JOIN user_role AS ur ON u.id = ur.user_id\n" +
            "            LEFT JOIN role AS r ON r.id = ur.role_id\n" +
            "            LEFT JOIN role_permission as rp ON r.id = rp.role_id\n" +
            "            LEFT JOIN permission AS p on p.id = rp.permission_id")
    List<String> permissionList(Integer userId);
}