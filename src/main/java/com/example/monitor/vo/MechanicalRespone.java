package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class MechanicalRespone {

   private String name;

    /**
     * 合闸电流
     */
    @Column(name = "closingCurrent")
    private BigDecimal closingcurrent;

    /**
     * 分闸电流
     */
    @Column(name = "openingCurrent")
    private BigDecimal openingcurrent;

    /**
     * 储能电流
     */
    @Column(name = "energyCurrent")
    private BigDecimal energycurrent;

    /**
     * 合闸电压
     */
    @Column(name = "closingVoltage")
    private BigDecimal closingvoltage;

    /**
     * 分闸电压
     */
    @Column(name = "openingVoltage")
    private BigDecimal openingvoltage;

    /**
     * 储能电压
     */
    @Column(name = "energyVoltage")
    private BigDecimal energyvoltage;

    /**
     * 开关柜设备名称
     */
    @Column(name = "switch_name")
    private String switchName;
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;

    @Column(name = "upload_time")
    private Data uploadTime;
    private Data time;

}
