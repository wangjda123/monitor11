package com.example.monitor.service;
import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.pojo.Terminal;
import com.example.monitor.core.Service;
import com.example.monitor.vo.ListRequest;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/07/16.
 */
public interface TerminalService extends Service<Terminal> {
     List<TerminalDTO> list();

     /**
      * 端子箱温度展示
      * @return
      */
     List<TerminalDTO> tempList(ListRequest request);

     /**
      * 端子箱湿度展示
      * @return
      */
     List<TerminalDTO> humidityList(ListRequest request);


}
