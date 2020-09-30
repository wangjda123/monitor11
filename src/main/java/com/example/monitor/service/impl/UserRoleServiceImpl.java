package com.example.monitor.service.impl;

import com.example.monitor.dao.UserRoleMapper;
import com.example.monitor.pojo.UserRole;
import com.example.monitor.service.UserRoleService;
import com.example.monitor.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;

}
