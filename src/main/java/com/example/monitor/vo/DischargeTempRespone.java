package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DischargeTempRespone {
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
     * 时间
     */
    private Date time;
}
