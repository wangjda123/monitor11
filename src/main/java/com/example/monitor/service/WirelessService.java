package com.example.monitor.service;
import com.example.monitor.DTO.WirelessDTO;
import com.example.monitor.pojo.Wireless;
import com.example.monitor.core.Service;
import com.example.monitor.vo.WirelessListRequest;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/21.
 */
public interface WirelessService extends Service<Wireless> {
     List<WirelessDTO>  selectWireless();

     List<WirelessDTO> wirelessList(WirelessListRequest request);
}
