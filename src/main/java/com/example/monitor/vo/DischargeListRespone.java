package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DischargeListRespone {
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
    private Date Time;

    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;

}
