package com.example.monitor.controller;

import com.example.monitor.DTO.MechanicalDTO;
import com.example.monitor.vo.MechanicalListRequset;
import com.example.monitor.vo.MechanicalRespone;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.MechanicalService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 机械特性
* Created by CodeGenerator on 2020/07/15.
*/
@Auth("noauth")
@RestController
@RequestMapping("/mechanical")
public class MechanicalController {
    @Resource
    private MechanicalService mechanicalService;
    /**
     * 首页机械特性展示
     * @param
     * @return
     */
    @PostMapping("/list")
    public Result list() {
        List<MechanicalDTO> query=mechanicalService.selectMechanics();
        List<MechanicalRespone> list=new ArrayList<>();
        for (MechanicalDTO mechanicalDTO:query){
            MechanicalRespone mechanicalRespone=new MechanicalRespone();
            BeanUtils.copyProperties(mechanicalDTO,mechanicalRespone);
            list.add(mechanicalRespone);
        }
        return ResultUtil.success(list);
    }

    @PostMapping("/mechanicalList")
    public  Result mechanicalList(@RequestBody MechanicalListRequset requset){
        List<MechanicalRespone> list=new ArrayList<>();
        List<MechanicalDTO> query=mechanicalService.mechanicalList(requset);
        for (MechanicalDTO mechanicalDTO :query){
            MechanicalRespone mechanicalRespone=new MechanicalRespone();
            BeanUtils.copyProperties(mechanicalDTO,mechanicalRespone);
            list.add(mechanicalRespone);
        }
        PageResponse<MechanicalRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);

    }
}

