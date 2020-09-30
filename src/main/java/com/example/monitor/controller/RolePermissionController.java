package com.example.monitor.controller;

import com.example.monitor.annotation.Auth;
import com.example.monitor.service.RolePermissionService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
* Created by CodeGenerator on 2020/08/04.
*/
@Auth("admin")
@RestController
@RequestMapping("/role/permission")
public class RolePermissionController {
    @Resource
    private RolePermissionService rolePermissionService;


}
