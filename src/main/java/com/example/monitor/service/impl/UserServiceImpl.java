package com.example.monitor.service.impl;

import com.example.monitor.DTO.LoginDTO;
import com.example.monitor.DTO.PermissionDTO;
import com.example.monitor.DTO.UserDTO;
import com.example.monitor.DTO.UserLoginDTO;
import com.example.monitor.core.cache.IDoCache;
import com.example.monitor.core.result.Result;
import com.example.monitor.core.result.ResultUtil;
import com.example.monitor.dao.*;
import com.example.monitor.pojo.*;
import com.example.monitor.service.UserService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.UserListRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;

/**
 * 用户管理
 */

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Autowired
    IDoCache cache;

    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
    @Override
    public Result<LoginDTO> login(String name, String password) {
      User user= new User();
      user.setName(name);
      user.setPassward(password);
      User user1=userMapper.selectOne(user);
      if (user1==null){
          return ResultUtil.fail("用户名或密码错误");
      }
        //String token=createToken(user,24 * 60 * 60);//有效期1天
        // 获取菜单权限
       //根据userId查询拥有的所有的角色id
        UserRole userRole=new UserRole();
        userRole.setUserId(user.getId());
        List<UserRole> userRoleList=userRoleMapper.select(userRole);
        List<Integer> integerList=new ArrayList<>();
        for(UserRole role:userRoleList){
          integerList.add(role.getRoleId());
       }

        // 根据角色id查询权限id
        List<Integer> permissionIdList = new ArrayList<>();
        for (Integer roleId : integerList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            List<RolePermission> rolePermissionList = this.rolePermissionMapper.select(rolePermission);
            for (RolePermission permission : rolePermissionList) {
                permissionIdList.add(permission.getPermissionId());
            }
        }

        // 根据权限id查询权限列表
        Set<Permission> permissionSet = new HashSet<>();
        for (Integer permissionId : permissionIdList) {
            Permission permission = new Permission();
            permission.setId(permissionId);
            List<Permission> permissionList = this.permissionMapper.select(permission);
            permissionSet.addAll(permissionList);
        }

        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        for (Permission permission : permissionSet) {
            PermissionDTO permissionDTO = new PermissionDTO();
            BeanUtils.copyProperties(permission,permissionDTO);
            permissionDTOList.add(permissionDTO);
        }

        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setId(user.getId());
        loginDTO.setName(user.getName());

        loginDTO.setPermissionDTOList(permissionDTOList);
        return ResultUtil.success(loginDTO);
    }

    /**
     * 登出
     * @param id
     * @param token
     * @return
     */
    @Override
    public boolean logout(Integer id, String token) {
        if (cache.containsKey(token)){
            cache.removeData(token);
            return true;
        }
        return false;
    }

    /**
     * 增加用户
     * @param userDTO
     * @return
     */
    @Override
    public boolean save(UserDTO userDTO) {
        // dto -> po
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        // 保存用户信息
        this.userMapper.insertSelective(user);
        // 保存角色信息
        // 查询新增用户的id
        Integer userId = this.userMapper.selectOne(user).getId();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(userDTO.getRoleId());
        this.userRoleMapper.insert(userRole);
        return true;
    }

    /**
     * 编辑用户
     * @param userDTO
     * @return
     */
    @Override
    public boolean update(UserDTO userDTO) {
        //dto->pojo
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        // 检查用户名是否重复
        if (this.checkRepeat(user)) {
            return false;
        }
        userMapper.updateByPrimaryKeySelective(user);
         //删除已有
        userRoleMapper.deleteByPrimaryKey(userDTO.getId());
        //新增
        UserRole userRole=new UserRole();
        userRole.setUserId(userDTO.getId());
        userRole.setRoleId(userDTO.getRoleId());
        userRoleMapper.insertSelective(userRole);
        return true;
    }

    /**
     * 检查 姓名
     * @param user
     * @return true 重复 false 不重复发
     */
    private boolean checkRepeat(User user) {
        // 如果为空没重复  不为空可能是update时未修改 检验id
        Integer byName = this.userMapper.selectByRealName(user.getName());
        if (byName != null && !byName.equals(user.getId())) {
            return true;
        }
        List<Integer> integers = this.userMapper.selectByPassward(user.getPassward());
        for (Integer byPhoneNumber : integers) {
            if (!"".equals(user.getPassward()) &&
                    user.getPassward() != null &&
                    byPhoneNumber != null &&
                    !byPhoneNumber.equals(user.getId())) {

                return true;
            }
        }
        return false;
    }

    /**
     * 查询用户列表
     * @return
     */
    @Override
    public List<UserDTO> userList() {
        List<UserDTO> userDTOList=new ArrayList<>();
        UserDTO userDTO=new UserDTO();
        List<UserDTO> list=userMapper.userList(userDTO);
        return list;
    }

 /* *//**
     * 创建token
     **//*
    private String createToken(User doUser, int expire) {
        String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        UserLoginDTO dto = new UserLoginDTO();
        BeanUtils.copyProperties(doUser,dto);
        // 获取当前角色所有权限URL
        List<String> permissionList = this.getPermissionList(doUser.getId());
        dto.setPermissionURLList(permissionList);
        cache.setData(token, dto, expire);
        return token;
    }*/

    public List<String> getPermissionList(int id){
        List<String> permissionList = this.permissionMapper.permissionList(id);
        List<String> url = new ArrayList<>();
        for (String s : permissionList) {
            if (s.indexOf("/")==0){
                url.add(s.substring(1));
            }else {
                url.add(s.replaceAll(":","/"));
            }
        }
        List<String> url2 = new ArrayList<>();
        // 去掉路由地址
        for (String s : url) {
            int i = s.indexOf("/");
            if (i >0) {
                String substring = "/api"+s.substring(i);
                url2.add(substring);
            }
        }
        System.out.println("=========");
        url2.forEach(System.out::println);
        System.out.println("=========");
        return url2;
    }
}
