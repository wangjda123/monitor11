package com.example.monitor.controller;

import com.example.monitor.DTO.PermissionDTO;
import com.example.monitor.DTO.RolePermissionDTO;
import com.example.monitor.service.PermissionService;
import com.example.monitor.service.RolePermissionService;
import com.example.monitor.vo.RoleAddRequest;
import com.example.monitor.vo.RoleGetResponse;
import com.example.monitor.vo.RoleIdRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.pojo.Role;
import com.example.monitor.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on 2020/08/04.
*/
@Auth("noauth")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 添加角色
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody RoleAddRequest request) {
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        BeanUtils.copyProperties(request,rolePermissionDTO);
        if (this.roleService.save(rolePermissionDTO)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail("角色名已存在");
    }


    /**
     * 修改角色
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody RoleAddRequest request) {
        // vo -> dto
        RolePermissionDTO rolePermissionDTO = new RolePermissionDTO();
        BeanUtils.copyProperties(request,rolePermissionDTO);
        if (roleService.update(rolePermissionDTO)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail("角色名已存在");
    }



    @PostMapping("/list")
    public Result list(@RequestBody PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<Role> list = roleService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        PageResponse<Role> response=new PageResponse<Role>();
        response.setList(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        return ResultUtil.success("成功",response);
    }

    /**
     * 根据角色id获取 权限列表
     * @param request
     * @return
     */
    @PostMapping("/get")
    public Result getPermission(@RequestBody RoleIdRequest request){
        List<PermissionDTO> list = this.permissionService.list();
        List<RoleGetResponse> topList = getRoleGetResponses(request.getId(), list);
        return ResultUtil.success("成功",topList);
    }
    /**
     * 设置vo的select属性
     * @param id
     * @param list
     * @return
     */
    private List<RoleGetResponse> getRoleGetResponses(@PathVariable("id") Integer id, List<PermissionDTO> list) {
        List<RoleGetResponse> temp = new ArrayList<>();
        // dto -> vo
        for (PermissionDTO permissionDTO : list) {
            RoleGetResponse roleGetResponse = new RoleGetResponse();
            BeanUtils.copyProperties(permissionDTO, roleGetResponse);
            temp.add(roleGetResponse);
        }

        // parentId非0的设置给父级
        for (RoleGetResponse roleGetResponse : temp) {
            Integer parentId = roleGetResponse.getParentId();
            if (parentId.equals(0)){
                continue;
            }
            // 找到父级
            for (RoleGetResponse parent : temp) {
                if (parent.getId().equals(parentId)){
                    if (parent.getChildren() == null) {
                        List<RoleGetResponse> childList = new ArrayList<>();
                        childList.add(roleGetResponse);
                        parent.setChildren(childList);
                    }else {
                        parent.getChildren().add(roleGetResponse);
                    }
                }
            }
        }
        // 查询角色拥有权限id
        List<Integer> permissionIdList = this.rolePermissionService.findPermissionIdListByRoleId(id);
//        permissionIdList.forEach(System.out::println);
        for (Integer i : permissionIdList) {
            for (RoleGetResponse roleGetResponse : temp) {
                if (roleGetResponse.getId().equals(i)) {
                    roleGetResponse.setSelected(true);
                }
            }
        }

        List<RoleGetResponse> topList = new ArrayList<>();
        // 找到所有顶级 parentId=0
        for (RoleGetResponse top: temp) {
            if (top.getParentId().equals(0)) {
                topList.add(top);
            }
        }
        return topList;
    }
}
