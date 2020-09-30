package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class TerminalHumiditiyRespone {
    /**
     * 端子箱名字
     */

    private String name;
    /**
     * 首页端子箱设备温湿度显示
     */
    /**
     * 温度
     */
    private BigDecimal humidity;
    /**
     * 时间
     */
    private Date time;

}
