package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
public class DischargeDTO {
    /**
     * 局部放电id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 超声波
     */
    private Integer ultrasonic;

    /**
     * 地声波
     */
    private Integer geoacoustic;

    /**
     * 时间
     */
    private Date time;

    @Column(name = "switchDetail_id")
    private Integer switchdetailId;
    /**
     * 设备名称
     */
    @Column(name = "switct_name")
    private String switchName;
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
    @Column(name = "value_type")
    private Integer valueType;
    @Column(name = "upload_time")
    private Data uploadTime;
}
