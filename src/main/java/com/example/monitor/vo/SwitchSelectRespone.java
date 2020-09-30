package com.example.monitor.vo;


import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
@Data
public class SwitchSelectRespone {
    @Column(name = "switch_name")
    private String switchName;
    /**
     * 温度
     */
    private BigDecimal temp;
    /**
     * 湿度
     */
    private BigDecimal humidity;
}
