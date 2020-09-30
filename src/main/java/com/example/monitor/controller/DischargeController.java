package com.example.monitor.controller;

import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.vo.*;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.DischargeService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 局部放电
*/
@Auth("noauth")
@RestController
@RequestMapping("/discharge")
public class DischargeController {
    @Resource
    private DischargeService dischargeService;

    /**
     * 首页局部放电展示
     * @param
     * @return
     */
    @PostMapping("/list")
    public Result list() {
        List<DischargeDTO> query = dischargeService.list();
        List<DischargeRespone> list=new ArrayList<>();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (DischargeDTO dischargeDTO :query){
            DischargeRespone dischargeRespone=new DischargeRespone();
            BeanUtils.copyProperties(dischargeDTO,dischargeRespone);
            /*String format = sf.format(dischargeDTO.getTime());
            dischargeRespone.setTime(format);*/
            list.add(dischargeRespone);
        }
        return ResultUtil.success("成功",list);
    }

    /**
     * 超声波展示
     * @return
     */
    @PostMapping("/tempList")
     public Result tempList(){
        List<DischargeDTO> query=dischargeService.tempList();
        List<DischargeTempRespone> list=new ArrayList<>();
         for (DischargeDTO dischargeDTO :query){
             DischargeTempRespone dischargeTempRespone=new DischargeTempRespone();
             BeanUtils.copyProperties(dischargeDTO,dischargeTempRespone);
             list.add(dischargeTempRespone);
         }
         return ResultUtil.success("成功",list);
     }

    /**
     * 地声波展示
     * @return
     */
    @PostMapping("/humidityList")
    public Result humidityList(){
        List<DischargeDTO> query=dischargeService.humidityList();
        List<DischargeHuidityRespone> list=new ArrayList<>();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (DischargeDTO dischargeDTO :query){
            DischargeHuidityRespone dischargeHuidityRespone=new DischargeHuidityRespone();
            String format=sf.format(dischargeDTO.getTime());
            BeanUtils.copyProperties(dischargeDTO,dischargeHuidityRespone);
            list.add(dischargeHuidityRespone);
        }
        return ResultUtil.success("成功",list);
    }

    /**
     * 模糊信息查询（超声波）
     * @param request
     * @return
     */

    @PostMapping("/UList")
    public Result UList(@RequestBody SwitchListRequest request){
        List<DischargeListRespone> list=new ArrayList<>();
        List<DischargeDTO> query=dischargeService.UList(request);
        for (DischargeDTO dischargeDTO :query){
            DischargeListRespone dischargeListRespone=new DischargeListRespone();
            BeanUtils.copyProperties(dischargeDTO,dischargeListRespone);
            list.add(dischargeListRespone);
        }
        PageResponse<DischargeListRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/GList")
    public Result GList(@RequestBody SwitchListRequest request){
        List<DischargeListRespone> list=new ArrayList<>();
        List<DischargeDTO> query=dischargeService.GList(request);
        for (DischargeDTO dischargeDTO :query){
            DischargeListRespone dischargeListRespone=new DischargeListRespone();
            BeanUtils.copyProperties(dischargeDTO,dischargeListRespone);
            list.add(dischargeListRespone);
        }
        PageResponse<DischargeListRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }
}
