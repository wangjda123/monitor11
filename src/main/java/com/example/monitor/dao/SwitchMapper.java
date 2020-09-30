package com.example.monitor.dao;

import com.example.monitor.DTO.SwitchDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Switch;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SwitchMapper extends Mapper<Switch> {
    //除湿机温度展示
    @Select(" select \n" +
            " n.temp,\n" +
            " n.humidity,\n" +
            " n.time,\n" +
            " sd.name,\n" +
            " n.upload_time uploadTime,\n" +
            " s.switch_name switchName,\n" +
            " s.voltage_level voltageLevel,\n" +
            " s.Interval_name IntervalName\n" +
            " from \n" +
            " switch AS s LEFT JOIN switchDetail As sd ON \n" +
            " s.id=sd.switch_id\n" +
            " LEFT JOIN information AS n ON \n" +
            " sd.id=n.switchDetail_id ")
    List<SwitchDTO> list(SwitchDTO switchDTO);
    //除湿机湿度展示
    List<SwitchDTO> humidList(SwitchDTO switchDTO);
    //首页除湿机温度展示
    List<SwitchDTO> selectList(SwitchDTO switchDTO);

    /**
     * 开关柜信息查询（温度）
     * @param switchDTO
     * @return
     */
    @Select(" SELECT\n" +
            "        s.switch_name,\n" +
            "        N.name,\n" +
            "        N.temp,\n" +
            "        N.time,\n" +
            "        s.voltage_level,\n" +
            "        s.Interval_name\n" +
            "        FROM\n" +
            "        switch AS s LEFT JOIN information AS N ON\n" +
            "        s.id=N.switch_id\n" +
            "        where\n" +
            "        s.switch_name like #{switchName}")
    List<SwitchDTO> tempList(SwitchDTO switchDTO);
    /**
     * 开关柜信息查询（湿度）
     * @param switchDTO
     * @return
     */
    @Select(" SELECT\n" +
            "        s.switch_name,\n" +
            "        N.name,\n" +
            "        N.humidity,\n" +
            "        N.time,\n" +
            "        s.voltage_level,\n" +
            "        s.Interval_name\n" +
            "        FROM\n" +
            "        switch AS s LEFT JOIN information AS N ON\n" +
            "        s.id=N.switch_id\n" +
            "        where\n" +
            "        s.switch_name like #{switchName}")
    List<SwitchDTO> humList(SwitchDTO switchDTO);

}