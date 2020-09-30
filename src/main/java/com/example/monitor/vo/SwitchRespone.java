package com.example.monitor.vo;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
@Data
public class SwitchRespone {
    /**
     * 开关柜的名称
     */
    @Column(name = "switch_name")
    private String switchName;
    /**
     * 除湿机名字
     */
    private String name;

    /**
     * 温度
     */
    private BigDecimal temp;

    private BigDecimal humidity;
    /**
     * 时间
     */

    private Date time;
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;

    @Column(name = "upload_time")
    private Data uploadTime;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }



    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    public String getVoltageLevel() {
        return voltageLevel;
    }

    public void setVoltageLevel(String voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public String getIntervalName() {
        return IntervalName;
    }

    public void setIntervalName(String intervalName) {
        IntervalName = intervalName;
    }

    public Data getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Data uploadTime) {
        this.uploadTime = uploadTime;
    }
}
