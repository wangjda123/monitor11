package com.example.monitor.vo;

import javax.persistence.Column;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;

@lombok.Data
public class ListRespone {
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
    private BigDecimal temp;

    private Date Time;


}
