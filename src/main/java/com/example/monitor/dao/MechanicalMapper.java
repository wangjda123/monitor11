package com.example.monitor.dao;

import com.example.monitor.DTO.MechanicalDTO;
import com.example.monitor.core.Mapper;
import com.example.monitor.pojo.Mechanical;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MechanicalMapper extends Mapper<Mechanical> {
    @Select("  select \n" +
            " m.closingCurrent,\n" +
            " m.openingCurrent,\n" +
            " m.energyCurrent,\n" +
            " m.closingVoltage,\n" +
            " m.openingVoltage,\n" +
            " m.energyVoltage,\n" +
            " s.switch_name switchName,\n" +
            " s.voltage_level voltageLevel,\n" +
            " s.Interval_name IntervalName,\n" +
            " sd.name,\n" +
            " m.upload_time uploadTime,\n" +
            " m.time\n" +
            " from switch AS s\n" +
            " LEFT JOIN switchDetail AS sd\n" +
            " ON s.id=sd.switch_id\n" +
            " LEFT JOIN mechanical m\n" +
            " ON sd.id=m.switchDetail_id")
    List<MechanicalDTO> selectMechanics(MechanicalDTO mechanicalDTO);

    @Select(" select \n" +
            " m.closingCurrent,\n" +
            " m.openingCurrent,\n" +
            " m.energyCurrent,\n" +
            " m.closingVoltage,\n" +
            " m.openingVoltage,\n" +
            " m.energyVoltage,\n" +
            " s.switch_name,\n" +
            " s.voltage_level,\n" +
            " s.Interval_name,\n" +
            " m.time\n" +
            " from \n" +
            " switch AS s LEFT JOIN mechanical AS  m ON\n" +
            " s.id=m.switch_id\n" +
            "  where\n" +
            "s.switch_name like #{switchName}")
    List<MechanicalDTO> mechanicalList(MechanicalDTO mechanicalDTO);
}
