package com.example.monitor.dao;

import com.example.monitor.DTO.UserDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    @Select(" select u.id,u.name,u.passward,r.id roleId,r.role_name roleName \n" +
            "     FROM user AS u  \n" +
            "     LEFT JOIN user_role AS ur ON u.id = ur.user_id \n" +
            "     LEFT JOIN role AS r ON ur.role_id = r.id ")
    List<UserDTO> userList(UserDTO userDTO);

    @Select("SELECT id from user where name=#{name}")
    Integer selectByRealName(String realName);

    @Select("SELECT id from user where passward=#{passward}")
    List<Integer> selectByPassward(String passward);

}