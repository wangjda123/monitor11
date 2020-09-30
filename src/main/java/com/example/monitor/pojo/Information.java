package com.example.monitor.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Data
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 除湿机名字
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

    @Column(name = "switchDetail_id")
    private Integer switchDetailId;

    @Column(name = "upload_time")
    private Data uploadTime;

}