package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;

@Data
public class DischargeHuidityRespone {
    /**
     * 设备名称
     */
    @Column(name = "switct_name")
    private String switchName;
    /**
     * 地声波
     */
    private Integer geoacoustic;
    /**
     *
     * 时间
     */
    private String time;

    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
}
