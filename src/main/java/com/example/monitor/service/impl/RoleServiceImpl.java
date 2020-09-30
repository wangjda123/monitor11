package com.example.monitor.service.impl;

import com.example.monitor.DTO.RoleDTO;
import com.example.monitor.DTO.RolePermissionDTO;
import com.example.monitor.dao.RoleMapper;
import com.example.monitor.dao.RolePermissionMapper;
import com.example.monitor.pojo.Role;
import com.example.monitor.pojo.RolePermission;
import com.example.monitor.service.RoleService;
import com.example.monitor.core.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/08/04.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> list(String name) {
        List<RoleDTO> roleDTOList=new ArrayList<>();
        if (name==null){
            List<Role> roles=roleMapper.selectAll();
            return roles;
        }else{
            Example example = new Example(Role.class);
            example.createCriteria().andLike("name","%"+name+"%");
            List<Role> roles = this.roleMapper.selectByExample(example);
            return roles;
        }

    }

    @Override
    public boolean save(RolePermissionDTO rolePermissionDTO) {
        Role role=new Role();
        //检查用户名是否存在
        role.setRoleName(rolePermissionDTO.getRoleName());
        if (roleMapper.selectOne(role)!=null){
            return  false;
        }
        //dto->po
        BeanUtils.copyProperties(rolePermissionDTO,role);
        //保存Role name
        roleMapper.insertSelective(role);
        //查询保存角色id
        Integer roleId=roleMapper.selectOne(role).getId();
        List<RolePermission> rolePermissionList = new ArrayList<>();
        List<Integer> permissionIdList = rolePermissionDTO.getPermissionIdList();
        // 勾选的id集合封装到PO中
        for (Integer permissionId : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
//        System.out.println(rolePermissionList);
        // 保存勾选的权限
        for (RolePermission rolePermission : rolePermissionList) {
            this.rolePermissionMapper.insert(rolePermission);
        }

        return true;
    }

    @Override
    public void updateStatus(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        this.roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public boolean update(RolePermissionDTO rolePermissionDTO) {
        //检查用户名是否重复
        Role role = new Role();
        role.setRoleName(rolePermissionDTO.getRoleName());
        List<Role> list=roleMapper.select(role);
        for (Role role1 : list) {
            if (!role1.getId().equals(rolePermissionDTO.getId())) {
                return false;
            }
        }
        //dto->po
        BeanUtils.copyProperties(rolePermissionDTO,role);

        // 更新name remark
        this.roleMapper.updateByPrimaryKeySelective(role);
        // 删除中间表数据
        this.rolePermissionMapper.deleteByPrimaryKey(role.getId());
        // 更新中间表数据
        List<RolePermission> rolePermissionList = new ArrayList<>();
        List<Integer> permissionIdList = rolePermissionDTO.getPermissionIdList();
        // 勾选的id集合封装到中间表PO中
        for (Integer permissionId : permissionIdList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        // 保存勾选的权限
        for (RolePermission rolePermission : rolePermissionList) {
            this.rolePermissionMapper.insert(rolePermission);
        }
        return true;
    }
}
