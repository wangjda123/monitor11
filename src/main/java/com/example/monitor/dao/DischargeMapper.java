package com.example.monitor.dao;

import com.example.monitor.DTO.DischargeDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Discharge;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DischargeMapper extends Mapper<Discharge> {
    @Select("\n" +
            " select d.ultrasonic,d.geoacoustic,s.switch_name switchName,s.Interval_name IntervalName,s.voltage_level voltageLevel,sd.name,d.time,d.value_type valueType,d.upload_time uploadTime\n" +
            " FROM switch AS s\n" +
            " LEFT JOIN switchDetail AS sd\n" +
            " ON s.id=sd.switch_id\n" +
            " LEFT JOIN discharge AS d\n" +
            " ON sd.id=d.switchDetail_id")
    List<DischargeDTO> list(DischargeDTO dischargeDTO);


    /**
     * 超声波
     * @return
     */
    @Select("\n" +
            " select \n" +
            " sd.ultrasonic,\n" +
            " sd.time,\n" +
            " s.switch_name\n" +
            " FROM switch AS s\n" +
            " LEFT JOIN discharge AS sd\n" +
            " ON s.id=sd.switch_id\n" +
            "")
    List<DischargeDTO> tempList(DischargeDTO dischargeDTO);

    /**
     * 地声波
     * @return
     */
    @Select(" select \n" +
            " sd.geoacoustic,\n" +
            " sd.time,\n" +
            " s.switch_name\n" +
            " FROM switch AS s\n" +
            " LEFT JOIN discharge AS sd\n" +
            " ON s.id=sd.switch_id\n" +
            " ")
    List<DischargeDTO> humidityList(DischargeDTO dischargeDTO);

    @Select(" SELECT \n" +
            " s.switch_name, \n" +
            " d.name,\n" +
            " d.ultrasonic,\n" +
            " d.time,\n" +
            " s.voltage_level,\n" +
            " s.Interval_name\n" +
            " FROM\n" +
            " switch AS s LEFT JOIN discharge AS d ON \n" +
            " s.id=d.switch_id\n" +
            " where\n" +
            "s.switch_name like #{switchName}")
    List<DischargeDTO> UList(DischargeDTO dischargeDTO);

    @Select(" SELECT \n" +
            " s.switch_name, \n" +
            " d.name,\n" +
            " d.geoacoustic,\n" +
            " d.time,\n" +
            " s.voltage_level,\n" +
            " s.Interval_name\n" +
            " FROM\n" +
            " switch AS s LEFT JOIN discharge AS d ON \n" +
            " s.id=d.switch_id\n" +
            " where\n" +
            "s.switch_name like #{switchName}")
    List<DischargeDTO> GList(DischargeDTO dischargeDTO);
}