package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class WirelessDTO {
    /**
     * 无线测温id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * (上臂,下臂，母栏，电缆）
     */
    @Column(name = "wireless_name")
    private String wirelessName;

    /**
     * A相
     */
    @Column(name = "phaseA")
    private BigDecimal phasea;

    /**
     * B相
     */
    @Column(name = "phaseB")
    private BigDecimal phaseb;

    /**
     * C相
     */
    @Column(name = "phaseC")
    private BigDecimal phasec;

    @Column(name = "switch_id")
    private Integer switchId;

    /**
     * 时间
     */
    private Date time;

    @Column(name = "Switch_name")
    private String SwitchName;

    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
    /**
     * 传感器名字
     */
    private String name;
    @Column(name = "upload_time")
    private Date uploadTime;
}
