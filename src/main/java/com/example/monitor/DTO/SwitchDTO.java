package com.example.monitor.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.util.Date;

public class SwitchDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    /**
     * 湿度
     */
    private BigDecimal humidity;

    /**
     * 时间
     */
    @Column(name = "time")
    private Date Time;

    @Column(name = "switchid")
    private Integer switchId;
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;

    @Column(name = "upload_time")
    private Data uploadTime;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

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

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
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
