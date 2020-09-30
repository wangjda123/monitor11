package com.example.monitor.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
public class Discharge {
    /**
     * 局部放电id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 传感器名称
     */
    private String name;
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
    private Integer switchDetailId;

    @Column(name = "value_type")
    private Integer valueType;
    @Column(name = "upload_time")
    private Date uploadTime;
}