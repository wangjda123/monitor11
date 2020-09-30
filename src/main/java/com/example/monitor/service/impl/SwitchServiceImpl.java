package com.example.monitor.service.impl;

import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.dao.SwitchMapper;
import com.example.monitor.pojo.Switch;
import com.example.monitor.service.SwitchService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.SwitchListRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/07.
 */
@Service
@Transactional
public class SwitchServiceImpl extends AbstractService<Switch> implements SwitchService {
    @Resource
    private SwitchMapper switchMapper;

    /**
     * 开关柜除湿机温度展示
     * @return
     */
    @Override
    public List<SwitchDTO> list() {
        SwitchDTO switchDTO=new SwitchDTO();
        List<SwitchDTO> query= switchMapper.list(switchDTO);
        return query;
    }

    /**
     * 开关柜除湿机湿度展示
     * @return
     */
    @Override
    public List<SwitchDTO> humidList() {
        SwitchDTO switchDTO=new SwitchDTO();
        List<SwitchDTO>  query= switchMapper.humidList(switchDTO);
        return query;
    }

    /**
     * 首页除湿机温度，湿度展示
     * @return
     */
    @Override
    public List<SwitchDTO> selectList() {
        SwitchDTO switchDTO =new SwitchDTO();
        List<SwitchDTO> switchDTOList=switchMapper.selectList(switchDTO);
        return switchDTOList;
    }

    /**
     * 温度信息查询
     * @param request
     * @return
     */
    @Override
    public List<SwitchDTO> tempList(SwitchListRequest request) {
        SwitchDTO switchDTO =new SwitchDTO();

        if (switchDTO.getSwitchName()==null || switchDTO.getSwitchName().isEmpty()){
            request.setSwitchName("");
        }else{
            switchDTO.setSwitchName("%" + switchDTO.getSwitchName() + "%");
        }
        List<SwitchDTO> list=switchMapper.tempList(switchDTO);
        return list;
    }

    /**
     * 湿度模糊查询
     * @param request
     * @return
     */
    @Override
    public List<SwitchDTO> humList(SwitchListRequest request) {
        SwitchDTO switchDTO =new SwitchDTO();

        if (switchDTO.getSwitchName()==null || switchDTO.getSwitchName().isEmpty()){
            request.setSwitchName("");
        }else{
            switchDTO.setSwitchName("%" + switchDTO.getSwitchName() + "%");
        }
        List<SwitchDTO> list=switchMapper.humList(switchDTO);
        return list;
    }
}
