package com.example.monitor.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Data
public class Wireless {
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

    @Column(name = "switchDetail_id")
    private Integer switchDetailId;

    /**
     * 时间
     */
    private Date time;
    @Column(name = "upload_time")
    private Date uploadTime;

}