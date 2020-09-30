package com.example.monitor.service;
import com.example.monitor.DTO.LoginDTO;
import com.example.monitor.DTO.UserDTO;
import com.example.monitor.core.result.Result;
import com.example.monitor.pojo.User;
import com.example.monitor.core.Service;
import com.example.monitor.vo.UserListRequest;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/06.
 */
public interface UserService extends Service<User> {
    //用户登录
    Result<LoginDTO> login(String name, String password);
    //登出
    boolean logout(Integer id , String token);
    //新增
    boolean save(UserDTO userDTO);
    //修改
    boolean update(UserDTO userDTO);
    //用户列表
    List<UserDTO> userList();

}
