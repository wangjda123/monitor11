package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class TerminalTempRespone {
    /**
     * 端子箱名字
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
    @Column(name = "upload_time")
    private String uploadTime;
    @Column(name = "value_type")
    private String valueType;

    private String time;
}
