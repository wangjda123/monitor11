package com.example.monitor.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.pojo.UserRole;
import com.example.monitor.service.UserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/08/04.
*/
@Auth("admin")
@RestController
@RequestMapping("/user/role")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/list")
    public Result list(@RequestBody PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<UserRole> list = userRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        PageResponse<UserRole> response=new PageResponse<UserRole>();
        response.setList(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        return ResultUtil.success("成功",response);
    }

}
