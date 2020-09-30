package com.example.monitor.service.impl;

import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.DTO.MechanicalDTO;
import com.example.monitor.dao.MechanicalMapper;
import com.example.monitor.pojo.Mechanical;
import com.example.monitor.service.MechanicalService;
import com.example.monitor.core.AbstractService;
import com.example.monitor.vo.MechanicalListRequset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/15.
 */
@Service
@Transactional
public class MechanicalServiceImpl extends AbstractService<Mechanical> implements MechanicalService {
    @Resource
    private MechanicalMapper mechanicalMapper;

    @Override
    public List<MechanicalDTO> selectMechanics() {
         MechanicalDTO mechanical=new MechanicalDTO();
         List<MechanicalDTO> list=mechanicalMapper.selectMechanics(mechanical);
        return list;
    }

    /**
     * 机械特性
     * @param requset
     * @return
     */
    @Override
    public List<MechanicalDTO> mechanicalList(MechanicalListRequset requset) {

       MechanicalDTO mechanicalDTO=new MechanicalDTO();
        if (mechanicalDTO.getSwitchName()==null || mechanicalDTO.getSwitchName().isEmpty()){
            requset.setSwitchName("");
        }else{
            mechanicalDTO.setSwitchName("%" + mechanicalDTO.getSwitchName() + "%");
        }
        List<MechanicalDTO> list=mechanicalMapper.mechanicalList(mechanicalDTO);
        return list;

    }


}
