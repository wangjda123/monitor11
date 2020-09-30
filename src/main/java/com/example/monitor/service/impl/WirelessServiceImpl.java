package com.example.monitor.service.impl;

import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.DTO.WirelessDTO;
import com.example.monitor.dao.WirelessMapper;
import com.example.monitor.pojo.Wireless;
import com.example.monitor.service.WirelessService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.WirelessListRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/21.
 */
@Service
@Transactional
public class WirelessServiceImpl extends AbstractService<Wireless> implements WirelessService {
    @Resource
    private WirelessMapper wirelessMapper;

    @Override
    public List<WirelessDTO> selectWireless() {
        WirelessDTO wirelessDTO=new WirelessDTO();
        List<WirelessDTO> query=wirelessMapper.selectWireless(wirelessDTO);
        return query;
    }

    /**
     * 无线测温模糊查询
     * @param request
     * @return
     */
    @Override
    public List<WirelessDTO> wirelessList(WirelessListRequest request) {
         WirelessDTO wirelessDTO=new WirelessDTO();
        if (wirelessDTO.getSwitchName()==null || wirelessDTO.getSwitchName().isEmpty()){
            request.setSwitchName("");
        }else{
            wirelessDTO.setSwitchName("%" + wirelessDTO.getSwitchName() + "%");
        }
        List<WirelessDTO> list=wirelessMapper.wirelessList(wirelessDTO);
        return list;
    }

}
