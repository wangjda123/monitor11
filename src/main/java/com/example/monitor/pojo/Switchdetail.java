package com.example.monitor.pojo;

import javax.persistence.*;

public class Switchdetail {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 开关柜的机器（除湿器，局部放电，机械特性，无线测温）
     */
    private String name;

    @Column(name = "switch_id")
    private Integer switchId;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取开关柜的机器（除湿器，局部放电，机械特性，无线测温）
     *
     * @return detail_name - 开关柜的机器（除湿器，局部放电，机械特性，无线测温）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置开关柜的机器（除湿器，局部放电，机械特性，无线测温）
     *
     * @param name 开关柜的机器（除湿器，局部放电，机械特性，无线测温）
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return switch_id
     */
    public Integer getSwitchId() {
        return switchId;
    }

    /**
     * @param switchId
     */
    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }
}