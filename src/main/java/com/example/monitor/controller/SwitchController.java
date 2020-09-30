package com.example.monitor.controller;


import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.vo.*;
import com.example.monitor.annotation.Auth;
import com.example.monitor.core.result.*;
import com.example.monitor.service.SwitchService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
* Created by CodeGenerator on 2020/07/07.
*/
@Auth("noauth")
@RestController
@RequestMapping("/switch")
public class SwitchController<SalesTransactionErrorListForm> {
    @Resource
    private SwitchService switchService;

    /**
     * 除湿机温度查询
     * @param
     * @return
     */
    @PostMapping("/list")
    public Result list() {
        List<SwitchDTO> query = switchService.list();
        List<SwitchRespone> list = new ArrayList<>();
        for (SwitchDTO switchDTO:query){
            SwitchRespone switchRespone=new SwitchRespone();
            BeanUtils.copyProperties(switchDTO,switchRespone);
            list.add(switchRespone);
        }
        return ResultUtil.success(list);
    }

    /**
     * 除湿机湿度查询
     * @param
     * @return
     */

    @PostMapping("humList")
    public  Result humList(){
        List<SwitchDTO> query = switchService.humidList();
        List<HumidityRespone> list = new ArrayList<>();
        for (SwitchDTO switchDTO:query){
            HumidityRespone humidityRespone=new HumidityRespone();
            BeanUtils.copyProperties(switchDTO,humidityRespone);
            list.add(humidityRespone);
        }
        return ResultUtil.success(list);
    }

    /**
     * 首页开关柜除湿机温湿度展示
     * @return
     */
    @PostMapping("select")
    public  Result select(){
        List<SwitchDTO> query = switchService.selectList();
        List<SwitchSelectRespone> list = new ArrayList<>();
        for (SwitchDTO switchDTO:query){
            SwitchSelectRespone switchSelectRespone=new SwitchSelectRespone();
            BeanUtils.copyProperties(switchDTO,switchSelectRespone);
            list.add(switchSelectRespone);
        }
        return ResultUtil.success(list);
    }

    /**
     * 模糊信息查询（湿度）
     * @param request
     * @return
     */

    @PostMapping("/humidityList")
    public Result humList(@RequestBody SwitchListRequest request){
        List<SwitchListRespone> list=new ArrayList<>();
        List<SwitchDTO> query=switchService.humList(request);
        for (SwitchDTO switchDTO :query){
            SwitchListRespone switchListRespone=new SwitchListRespone();
            BeanUtils.copyProperties(switchDTO,switchListRespone);
            list.add(switchListRespone);
        }
        PageResponse<SwitchListRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }

    /**
     * 模糊信息查询（温度）
     * @param request
     * @return
     */

    @PostMapping("/tempList")
    public Result Select(@RequestBody SwitchListRequest request){
        List<SwitchListRespone> list=new ArrayList<>();
        List<SwitchDTO> query=switchService.tempList(request);
        for (SwitchDTO switchDTO :query){
            SwitchListRespone switchListRespone=new SwitchListRespone();
            BeanUtils.copyProperties(switchDTO,switchListRespone);
            list.add(switchListRespone);
        }
        PageResponse<SwitchListRespone> response=new PageResponse<>();
        response.setList(list);
        response.setTotalCount(list.size());
        return ResultUtil.success(response);
    }
    /**
     * 下载Csv文件
     * @return
     */
    @PostMapping(path = "/download")
    public ResponseEntity<InputStreamResource> download(SalesTransactionErrorListForm salesTransactionErrorListForm)
            throws IOException {
        //salesTransactionErrorListService.download(salesTransactionErrorListForm);
        File file = null;
        try {
            file = File.createTempFile("vehicle", ".csv");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SwitchDTO switchDTO=new SwitchDTO();
             switchDTO.setSwitchName("aa");

            // switchDTO.setTime();
             List<SwitchDTO> list=new ArrayList<>();
             list.add(switchDTO);

        try {
            CsvUtility.writeAll(file, list);
        } catch (IOException e) {
            System.out.println("11111111111111111");
        }
        try (InputStream fileStream = new ByteArrayInputStream(
                FileUtils.readFileToByteArray(file))) {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename =aa.csv");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            InputStreamResource resource = new InputStreamResource(fileStream);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(FileUtils.readFileToByteArray(file).length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }
    }
}
