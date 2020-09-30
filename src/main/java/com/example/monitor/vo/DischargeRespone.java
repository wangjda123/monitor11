package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DischargeRespone {
    /**
     * 设备名称
     */
    @Column(name = "switct_name")
    private String switchName;

    /**
     * 超声波
     */
    private Integer ultrasonic;

    /**
     * 地声波
     */
    private Integer geoacoustic;

    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
    @Column(name = "value_type")
    private Integer valueType;
    @Column(name = "upload_time")
    private String uploadTime;
    /**
     * 时间
     */
    private String time;
    private  String name;
}
