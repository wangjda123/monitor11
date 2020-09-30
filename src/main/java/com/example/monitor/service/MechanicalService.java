package com.example.monitor.service;
import com.example.monitor.DTO.MechanicalDTO;
import com.example.monitor.pojo.Mechanical;
import com.example.monitor.core.Service;
import com.example.monitor.vo.MechanicalListRequset;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/15.
 */
public interface MechanicalService extends Service<Mechanical> {
    List<MechanicalDTO> selectMechanics();

    List<MechanicalDTO> mechanicalList(MechanicalListRequset requset);
}
