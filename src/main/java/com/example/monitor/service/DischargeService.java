package com.example.monitor.service;
import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.pojo.Discharge;
import com.example.monitor.core.Service;
import com.example.monitor.vo.SwitchListRequest;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/16.
 */
public interface DischargeService extends Service<Discharge> {
    List<DischargeDTO> list();

    /**
     * 超声波
     * @return
     */
    List<DischargeDTO> tempList();

    /**
     * 地声波
     * @return
     */
    List<DischargeDTO> humidityList();

    /**
     * 局部放电模糊查询
     * @param request
     * @return
     */
    List<DischargeDTO> UList(SwitchListRequest request);

    /**
     * 局部放电模糊查询(地声波)
     * @param request
     * @return
     */
    List<DischargeDTO> GList(SwitchListRequest request);
 }
