package com.example.monitor.service.impl;

import com.example.monitor.DTO.PermissionDTO;
import com.example.monitor.dao.PermissionMapper;
import com.example.monitor.pojo.Permission;
import com.example.monitor.service.PermissionService;
import com.example.monitor.core.AbstractService;
import org.springframework.beans.BeanUtils;
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
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDTO> list() {
        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        List<Permission> all = this.permissionMapper.selectAll();
        // PO转DTO
        for (Permission permission : all) {
            PermissionDTO permissionDTO = new PermissionDTO();
            BeanUtils.copyProperties(permission, permissionDTO);
            permissionDTOList.add(permissionDTO);
        }

        return permissionDTOList;

    }

    @Override
    public Integer findParentIdByTitle(String title) {
        Permission permission = new Permission();
        permission.setPerName(title);
        Permission parent = this.permissionMapper.selectOne(permission);
        if (parent==null){
            return null;
        }
        return parent.getId();
    }

    @Override
    public void save(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDTO,permission);
        permission.setId(0);

        this.permissionMapper.insertSelective(permission);
    }

    @Override
    public void update(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDTO,permission);
        this.permissionMapper.updateByPrimaryKeySelective(permission);
    }
}
