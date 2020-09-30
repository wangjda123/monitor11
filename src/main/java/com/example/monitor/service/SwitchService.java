package com.example.monitor.service;
import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.pojo.Switch;
import com.example.monitor.core.Service;
import com.example.monitor.vo.SwitchListRequest;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/07.
 */
public interface SwitchService extends Service<Switch> {
    //除湿机温度展示
    List<SwitchDTO> list();
    //除湿机湿度展示
    List<SwitchDTO> humidList();
    //首页除湿机温度展示
    List<SwitchDTO> selectList();


    List<SwitchDTO> tempList(SwitchListRequest request);
    List<SwitchDTO> humList(SwitchListRequest request);
}
