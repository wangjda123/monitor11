package com.example.monitor.dao;

import com.example.monitor.DTO.TerminalDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Terminal;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TerminalMapper extends Mapper<Terminal> {
    @Select("select td.name,t.time,td.voltage_level voltageLevel,td.Interval_name IntervalName,t.value_type valueType,value,t.upload_time uploadTime\n" +
            " FROM terminalDetail AS td LEFT JOIN terminal t on td.id=t.terminal_id ")
    List<TerminalDTO> list(TerminalDTO terminalDTO);

    /**
     * 端子箱温度展示
     * @return
     */
  /*  @Select("" +
            "select td.name,t.time,td.voltage_level voltageLevel,td.Interval_name IntervalName,t.value_type valueType,value,t.upload_time uploadTime\n" +
            " FROM terminalDetail AS td LEFT JOIN terminal t on td.id=t.terminal_id \n" +
            " where t.value_type   LIKE \"%温度%\" ")*/
    List<TerminalDTO> tempList(TerminalDTO terminalDTO);

    /**
     * 端子箱湿度展示
     * @return
     */
    @Select("select humidity,time,name FROM terminal")
    List<TerminalDTO> humidityList(TerminalDTO terminalDTO);

}