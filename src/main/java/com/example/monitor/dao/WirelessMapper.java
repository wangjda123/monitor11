package com.example.monitor.dao;

import com.example.monitor.DTO.WirelessDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Wireless;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WirelessMapper extends Mapper<Wireless> {
    @Select(" \n" +
            " select\n" +
            " s.switch_name switchName,\n" +
            " s.voltage_level voltageLevel,\n" +
            " s.Interval_name IntervalName,\n" +
            " w.wireless_name wirelessName,\n" +
            " w.upload_time uploadTime,\n" +
            " sd.name,\n" +
            " w.time,\n" +
            " w.phaseA,\n" +
            " w.phaseB,\n" +
            " w.phaseC\n" +
            " from\n" +
            "  switch AS s LEFT JOIN switchDetail As sd ON \n" +
            " s.id=sd.switch_id\n" +
            " LEFT JOIN wireless AS w ON \n" +
            " sd.id=w.switchDetail_id ")
    List<WirelessDTO> selectWireless(WirelessDTO wirelessDTO);

    @Select("select\n" +
            " s.switch_name,\n" +
            " s.voltage_level,\n" +
            " s.Interval_name,\n" +
            " w.wireless_name,\n" +
            " w.time,\n" +
            " w.phaseA,\n" +
            " w.phaseB,\n" +
            " w.phaseC\n" +
            " from \n" +
            " switch AS s\n" +
            " LEFT JOIN wireless AS w\n" +
            " ON s.id=w.switch_id\n" +
            "  where\n" +
            " s.switch_name like #{switchName}")
    List<WirelessDTO> wirelessList(WirelessDTO wirelessDTO);
}