package com.example.monitor.controller;

import com.example.monitor.DTO.LoginDTO;
import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.DTO.UserDTO;
import com.example.monitor.vo.*;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on
*/
@Auth("noauth")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private PermissionController permissionController;
    /**
     * 登录
     * @param request
     * @return
     */
    @Auth("noauth")
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request) {
        if (request.getName() == null || request.getName().equals(""))
            return ResultUtil.fail("用户名必填");
        if (request.getPassword() == null || request.getPassword().equals(""))
            return ResultUtil.fail("密码必填");

        Result<LoginDTO> loginDTO = userService.login(request.getName(), request.getPassword());
        if (!loginDTO.isSuccess()){
           /* return ResultUtil.fail(loginDTO.getMsg());

        LoginResponse response = new LoginResponse();
        BeanUtils.copyProperties(loginDTO.getData(),response);
        List<PermissionListResponse> permissionList = this.permissionController.getPermissionListResponses(loginDTO.getData().getPermissionDTOList());

        response.setPermissionList(permissionList);*/
    }
        return ResultUtil.success(loginDTO);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Result logout(@RequestBody LogoutRequest request){
        if (this.userService.logout(request.getId(),request.getToken())) {
            return ResultUtil.success();
        }
        return ResultUtil.fail("退出失败");
    }

    /**
     * 新增用户
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody UserAddRequest request) {

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(request,userDTO);
        if (userService.save(userDTO)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail("添加失败，请检查姓名、");
    }

    @PostMapping("/update")
    public Result update(@RequestBody UserUpdateRequest request) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(request,userDTO);
        userDTO.setPassward(request.getPassward());
        if (userService.update(userDTO)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail("编辑失败，登录名是否已存在");
    }


    /**
     * 删除用户
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody UserIdRequest request) {
        userService.deleteById(request.getUserId());
        return ResultUtil.success();
    }
    /**
     * 用户列表
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        List<UserRespone> responseList = new ArrayList<>();
        List<UserDTO> list=userService.userList();
        for (UserDTO userDTO:list){
            UserRespone userRespone=new UserRespone();
            BeanUtils.copyProperties(userDTO,userRespone);
            responseList.add(userRespone);
        }

        return ResultUtil.success("成功",responseList);
    }

}
