package com.org.model.statistic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/6.
 * �ӻ���־ʵ����
 */
@Entity
@Table(name = "on_off_statistic", schema = "dtcs_db")
public class OnOffStatistic implements Serializable{
    private static final long serialVersionUID = 3L;

    // ����
    private int keyId;
    // �ӻ���
    private int slaveId;
    // ����ʱ��
    private Date powerOnTime;
    // �ػ�ʱ��
    private Date powerOffTime;
    // �ͷ���ʱ��
    private int lowLevelWindTime;
    // �з���ʱ��
    private int middleLevelWindTime;
    // �߷���ʱ��
    private int highLevelWindTime;
    // �ܷ���
    private double totalFee;

    public OnOffStatistic() {
        //this.keyId = 0;
        this.slaveId = 0;
        this.powerOnTime = null;
        this.powerOffTime = null;
        this.lowLevelWindTime = 0;
        this.highLevelWindTime = 0;
        this.totalFee = 0;
    }

    public OnOffStatistic(int keyId, int slaveId, Date powerOnTime, Date powerOffTime, int lowLevelWindTime, int highLevelWindTime, int totalFee) {
        this.keyId = keyId;
        this.slaveId = slaveId;
        this.powerOnTime = powerOnTime;
        this.powerOffTime = powerOffTime;
        this.lowLevelWindTime = lowLevelWindTime;
        this.highLevelWindTime = highLevelWindTime;
        this.totalFee = totalFee;
    }

    public OnOffStatistic(int slaveId, Date powerOnTime, Date powerOffTime, int lowLevelWindTime, int middleLevelWindTime, int highLevelWindTime, int totalFee) {
        this.slaveId = slaveId;
        this.powerOnTime = powerOnTime;
        this.powerOffTime = powerOffTime;
        this.lowLevelWindTime = lowLevelWindTime;
        this.middleLevelWindTime = middleLevelWindTime;
        this.highLevelWindTime = highLevelWindTime;
        this.totalFee = totalFee;
    }

    @Id
    @Column(name = "key_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    @Basic
    @Column(name = "slave_id")
    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    @Basic
    @Column(name = "power_on_time")
    public Date getPowerOnTime() {
        return powerOnTime;
    }

    public void setPowerOnTime(Date powerOnTime) {
        this.powerOnTime = powerOnTime;
    }

    @Basic
    @Column(name = "power_off_time")
    public Date getPowerOffTime() {
        return powerOffTime;
    }

    public void setPowerOffTime(Date powerOffTime) {
        this.powerOffTime = powerOffTime;
    }

    @Basic
    @Column(name = "low_level_wind_time")
    public int getLowLevelWindTime() {
        return lowLevelWindTime;
    }

    public void setLowLevelWindTime(int lowLevelWindTime) {
        this.lowLevelWindTime = lowLevelWindTime;
    }

    @Basic
    @Column(name = "middle_level_wind_time")
    public int getMiddleLevelWindTime() {
        return middleLevelWindTime;
    }

    public void setMiddleLevelWindTime(int middleLevelWindTime) {
        this.middleLevelWindTime = middleLevelWindTime;
    }

    @Basic
    @Column(name = "high_level_wind_time")
    public int getHighLevelWindTime() {
        return highLevelWindTime;
    }

    public void setHighLevelWindTime(int highLevelWindTime) {
        this.highLevelWindTime = highLevelWindTime;
    }

    @Basic
    @Column(name = "total_fee")
    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }
}
