package com.example.monitor.controller;

import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.vo.*;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.TerminalService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on 2020/07/16.
*/
@Auth("noauth")
@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Resource
    private TerminalService terminalService;


    @PostMapping("/list")
    public Result list() {
        List<TerminalDTO> query = terminalService.list();
        List<TerminalRespone> list=new ArrayList<>();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        for (TerminalDTO terminalDTO:query){
            TerminalRespone terminalRespone=new TerminalRespone();
            BeanUtils.copyProperties(terminalDTO,terminalRespone);
              String format = sf.format(terminalDTO.getTime());
              terminalRespone.setTime(format);
             list.add(terminalRespone);
        }
        return ResultUtil.success("成功",list);
    }



    /**
     * 模糊信息查询
     * @param request
     * @return
     */
    @PostMapping("/ListSelect")
    public Result ListSelect(@RequestBody ListRequest request){
        List<TerminalRespone> list=new ArrayList<>();
        List<TerminalDTO> query=terminalService.tempList(request);
       for (TerminalDTO terminalDTO :query){
           TerminalRespone listRespone=new TerminalRespone();
           BeanUtils.copyProperties(terminalDTO,listRespone);
           list.add(listRespone);
       }
       PageResponse<TerminalRespone> response=new PageResponse<>();
       response.setList(list);
       response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }

    /**
     * 模糊信息查询（湿度）
     * @param request
     * @return
     */
    @PostMapping("/Select")
    public Result Select(@RequestBody ListRequest request){
        List<HumidityRespone> list=new ArrayList<>();
        List<TerminalDTO> query=terminalService.humidityList(request);
        for (TerminalDTO terminalDTO :query){
            HumidityRespone listRespone=new HumidityRespone();
            BeanUtils.copyProperties(terminalDTO,listRespone);
            list.add(listRespone);
        }
        PageResponse<HumidityRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }
}
