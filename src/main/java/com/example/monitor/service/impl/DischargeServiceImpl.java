package com.example.monitor.service.impl;

import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.dao.DischargeMapper;
import com.example.monitor.pojo.Discharge;
import com.example.monitor.service.DischargeService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.SwitchListRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/16.
 */
@Service
@Transactional
public class DischargeServiceImpl extends AbstractService<Discharge> implements DischargeService {
    @Resource
    private DischargeMapper dischargeMapper;

    /**
     * 首页超声波
     * @return
     */
    @Override
    public List<DischargeDTO> list() {
        DischargeDTO dischargeDTO=new DischargeDTO();
        List<DischargeDTO> list=dischargeMapper.list(dischargeDTO);
        return list;
    }

    /**
     * 超声波展示
     * @return
     */
    @Override
    public List<DischargeDTO> tempList() {
        DischargeDTO dischargeDTO=new DischargeDTO();
        List<DischargeDTO> list=dischargeMapper.tempList(dischargeDTO);
        return list;
    }

    /**
     * 地声波
     * @return
     */
    @Override
    public List<DischargeDTO> humidityList() {
        DischargeDTO dischargeDTO=new DischargeDTO();
        List<DischargeDTO> list = dischargeMapper.humidityList(dischargeDTO);
        return list;
    }

    @Override
    public List<DischargeDTO> UList(SwitchListRequest request) {
        DischargeDTO dischargeDTO=new DischargeDTO();
        if (dischargeDTO.getSwitchName()==null || dischargeDTO.getSwitchName().isEmpty()){
            request.setSwitchName("");
        }else{
            dischargeDTO.setSwitchName("%" + dischargeDTO.getSwitchName() + "%");
        }
        List<DischargeDTO> list=dischargeMapper.tempList(dischargeDTO);
        return list;

    }

    /**
     * 局部放电地声波
     * @param request
     * @return
     */
    @Override
    public List<DischargeDTO> GList(SwitchListRequest request) {
        DischargeDTO dischargeDTO=new DischargeDTO();
        if (dischargeDTO.getSwitchName()==null || dischargeDTO.getSwitchName().isEmpty()){
            request.setSwitchName("");
        }else{
            dischargeDTO.setSwitchName("%" + dischargeDTO.getSwitchName() + "%");
        }
        List<DischargeDTO> list=dischargeMapper.GList(dischargeDTO);
        return list;
    }
}
