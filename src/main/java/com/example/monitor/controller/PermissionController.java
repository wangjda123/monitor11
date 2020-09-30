package com.example.monitor.controller;

import com.example.monitor.DTO.PermissionDTO;
import com.example.monitor.vo.PermissionAddRequest;
import com.example.monitor.vo.PermissionListResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.pojo.Permission;
import com.example.monitor.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;


    /**
     * 新增权限
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody PermissionAddRequest request) {
        PermissionDTO permissionDTO=new PermissionDTO();
        // vo转dto
        BeanUtils.copyProperties(request,permissionDTO);
        permissionService.save(permissionDTO);
        return ResultUtil.success();
    }

    /**
     * 删除权限
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody PermissionAddRequest request) {
        permissionService.deleteById(request.getId());
        return ResultUtil.success();
    }

    /**
     * 修改权限
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody PermissionAddRequest request) {
        // vo -> dto
        PermissionDTO permissionDTO = new PermissionDTO();
        BeanUtils.copyProperties(request,permissionDTO);
//        System.out.println(permissionDTO);

        permissionService.update(permissionDTO);
        return ResultUtil.success();
    }


    /**
     * 权限列表
     * @param request
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<Permission> list = permissionService.findAll();
        PageInfo pageInfo = new PageInfo(list);

        PageResponse<Permission> response=new PageResponse<Permission>();
        response.setList(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        return ResultUtil.success("成功",response);
    }
    /**
     * 权限dto转vo 带child
     * @param list
     * @return
     */
    public List<PermissionListResponse> getPermissionListResponses(List<PermissionDTO> list) {
        List<PermissionListResponse> temp = new ArrayList<>();
        // dto -> vo
        for (PermissionDTO permissionDTO : list) {
            PermissionListResponse permissionListResponse = new PermissionListResponse();
            BeanUtils.copyProperties(permissionDTO, permissionListResponse);
            temp.add(permissionListResponse);
        }
        // parentId非0的设置给父级
        for (PermissionListResponse permissionListResponse : temp) {
            Integer parentId = permissionListResponse.getParentId();
            if (parentId.equals(0)){
                continue;
            }
            // 找到父级
            for (PermissionListResponse parent : temp) {
                if (parent.getId().equals(parentId)){
                    if (parent.getChildren() == null) {
                        List<PermissionListResponse> childList = new ArrayList<>();
                        childList.add(permissionListResponse);
                        parent.setChildren(childList);
                    }else {
                        parent.getChildren().add(permissionListResponse);
                    }
                }
            }
        }
        List<PermissionListResponse> topList = new ArrayList<>();
        // 找到所有顶级 parentId=0
        for (PermissionListResponse top: temp) {
            if (top.getParentId().equals(0)) {
                topList.add(top);
            }
        }
        return topList;
    }
}
