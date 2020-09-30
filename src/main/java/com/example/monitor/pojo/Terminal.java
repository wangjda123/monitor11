package com.example.monitor.pojo;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 端子箱中除湿机名称
     */
    private String name;

    /**
     * 温度
     */
    private BigDecimal temp;

    /**
     * 湿度
     */
    private BigDecimal humidity;

    /**
     * 时间
     */
    private Date time;
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
    private String value;
    private String upload_time;
    @Column(name = "value_type")
    private String valueType;
}