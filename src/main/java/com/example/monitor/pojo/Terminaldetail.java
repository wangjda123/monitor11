package com.example.monitor.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Data
public class Terminaldetail {
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



}