package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class TerminalDTO {
    /**
     * 端子箱
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    /**
     * 端子箱中除湿机名称
     */
    private String name;

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

    /**
     * 时间
     */
    private Date time;

}
