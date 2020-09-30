package com.example.monitor.controller;

import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.DTO.WirelessDTO;
import com.example.monitor.vo.SwitchListRespone;
import com.example.monitor.vo.WirelessListRequest;
import com.example.monitor.vo.WirelessListRespone;
import com.example.monitor.vo.WirelessRespone;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.WirelessService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on 2020/07/21.
*/
@Auth("noauth")
@RestController
@RequestMapping("/wireless")
public class WirelessController {
    @Resource
    private WirelessService wirelessService;


    @PostMapping("/list")
    public Result list() {

        List<WirelessDTO> query = wirelessService.selectWireless();
        List<WirelessRespone>  list=new ArrayList<>();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (WirelessDTO wirelessDTO:query){
            WirelessRespone wirelessRespone=new WirelessRespone();
            BeanUtils.copyProperties(wirelessDTO,wirelessRespone);
        /*    String format = sf.format(wirelessDTO.getTime());
            wirelessRespone.setTime(format);*/
            list.add(wirelessRespone);
        }
        return ResultUtil.success("成功",list);
    }

    /**
     * 无线测温展示（模糊查询）
     * @param request
     * @return
     */
    @PostMapping("/wirelessList")
    public Result wirelessList(@RequestBody WirelessListRequest request){
        List<WirelessListRespone> list=new ArrayList<>();
        List<WirelessDTO> query=wirelessService.wirelessList(request);
        for (WirelessDTO wirelessDTO :query){
            WirelessListRespone wirelessListRespone=new WirelessListRespone();
            BeanUtils.copyProperties(wirelessDTO,wirelessListRespone);
            list.add(wirelessListRespone);
        }
        PageResponse<WirelessListRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return  ResultUtil.success(response);
    }

}