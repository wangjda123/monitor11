package com.example.monitor.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Data
public class Mechanical {
    /**
     * 机械特性id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
     * 机械特性编号名称
     */
    private String name;

    /**
     * 时间
     */
    private Date time;

    @Column(name = "switchDetail_id")
    private Integer switchDetailId;
    @Column(name = "upload_time")
    private Date uploadTime;

}