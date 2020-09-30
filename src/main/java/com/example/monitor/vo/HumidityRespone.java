package com.example.monitor.vo;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

public class HumidityRespone {

    /**
     * 除湿机名字
     */
    private String name;

    /**
     * 湿度
     */
    private BigDecimal humidity;

    /**
     * 时间
     */
    @Column(name = "create_time")
    private Date Time;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }
}
