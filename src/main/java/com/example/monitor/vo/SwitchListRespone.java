package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SwitchListRespone {
    /**
     * 开关柜的名称
     */
    @Column(name = "switch_name")
    private String switchName;
    /**
     * 除湿机名称
     */
   private String name;
    /**
     * 温度
     */
    private BigDecimal temp;
    private Date Time;
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
}
