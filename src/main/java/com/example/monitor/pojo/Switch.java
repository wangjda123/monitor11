package com.example.monitor.pojo;

import javax.persistence.*;

public class Switch {
    /**
     * 开关柜id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 开关柜的名称
     */
    @Column(name = "switch_name")
    private String switchName;
    /**
     * 电压等级
     */
    @Column(name = "voltage_level")
    private String voltageLevel;
    /**
     * 间隔名称
     */
    @Column(name = "Interval_name")
    private String IntervalName;
    /**
     * 获取开关柜id
     *
     * @return id - 开关柜id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置开关柜id
     *
     * @param id 开关柜id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取开关柜的名称
     *
     * @return switch_name - 开关柜的名称
     */
    public String getSwitchName() {
        return switchName;
    }

    /**
     * 设置开关柜的名称
     *
     * @param switchName 开关柜的名称
     */
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
}