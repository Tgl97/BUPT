package com.org.model.stateLog;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/6.
 * 从机日志实体类
 */
@Entity
@Table(name = "slave_state_log_table", schema = "dtcs_db")
public class SlaveStateLog extends StateLog {
    // 从机号
    private int slaveId;
    // 当前温度
    private int slaveNowTemp;
    // 目标温度
    private int slaveTargetTemp;
    // 当前风速
    private int slaveNowWindLevel;
    // 目标风速
    private int slaveTargetWindLevel;
    // 工作模式
    private int slaveWorkMode;
    // 从机状态
    private int slaveState;
    // 总费用
    private int totalFee;
    // 是否服务
    private byte isServed;

    public SlaveStateLog() {
        super();
        this.slaveId = 0;
        this.slaveNowTemp = 0;
        this.slaveTargetTemp = 0;
        this.slaveNowWindLevel = 0;
        this.slaveTargetWindLevel = 0;
        this.slaveWorkMode = 0;
        this.slaveState = 0;
        this.totalFee = 0;
        this.isServed = 0;
    }

    public SlaveStateLog(int keyId, Date powerOnTime, Date nowTime, byte isPowerOn, int slaveId, int slaveNowTemp, int slaveTargetTemp, int slaveNowWindLevel, int slaveTargetWindLevel, int slaveWorkMode, int slaveState, int totalFee, byte isServed) {
        super(keyId, powerOnTime, nowTime, isPowerOn);
        this.slaveId = slaveId;
        this.slaveNowTemp = slaveNowTemp;
        this.slaveTargetTemp = slaveTargetTemp;
        this.slaveNowWindLevel = slaveNowWindLevel;
        this.slaveTargetWindLevel = slaveTargetWindLevel;
        this.slaveWorkMode = slaveWorkMode;
        this.slaveState = slaveState;
        this.totalFee = totalFee;
        this.isServed = isServed;
    }

    public SlaveStateLog(Date powerOnTime, Date nowTime, byte isPowerOn, int slaveId, int slaveNowTemp, int slaveTargetTemp, int slaveNowWindLevel, int slaveTargetWindLevel, int slaveWorkMode, int slaveState, int totalFee, byte isServed) {
        super(powerOnTime, nowTime, isPowerOn);
        this.slaveId = slaveId;
        this.slaveNowTemp = slaveNowTemp;
        this.slaveTargetTemp = slaveTargetTemp;
        this.slaveNowWindLevel = slaveNowWindLevel;
        this.slaveTargetWindLevel = slaveTargetWindLevel;
        this.slaveWorkMode = slaveWorkMode;
        this.slaveState = slaveState;
        this.totalFee = totalFee;
        this.isServed = isServed;
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
    @Column(name = "slave_now_temperature")
    public int getSlaveNowTemp() {
        return slaveNowTemp;
    }

    public void setSlaveNowTemp(int slaveNowTemp) {
        this.slaveNowTemp = slaveNowTemp;
    }

    @Basic
    @Column(name = "slave_target_temperature")
    public int getSlaveTargetTemp() {
        return slaveTargetTemp;
    }

    public void setSlaveTargetTemp(int slaveTargetTemp) {
        this.slaveTargetTemp = slaveTargetTemp;
    }

    @Basic
    @Column(name = "slave_now_wind_level")
    public int getSlaveNowWindLevel() {
        return slaveNowWindLevel;
    }

    public void setSlaveNowWindLevel(int slaveNowWindLevel) {
        this.slaveNowWindLevel = slaveNowWindLevel;
    }

    @Basic
    @Column(name = "slave_target_wind_level")
    public int getSlaveTargetWindLevel() {
        return slaveTargetWindLevel;
    }

    public void setSlaveTargetWindLevel(int slaveTargetWindLevel) {
        this.slaveTargetWindLevel = slaveTargetWindLevel;
    }

    @Basic
    @Column(name = "slave_work_mode")
    public int getSlaveWorkMode() {
        return slaveWorkMode;
    }

    public void setSlaveWorkMode(int slaveWorkMode) {
        this.slaveWorkMode = slaveWorkMode;
    }

    @Basic
    @Column(name = "slave_state")
    public int getSlaveState() {
        return slaveState;
    }

    public void setSlaveState(int slaveState) {
        this.slaveState = slaveState;
    }

    @Basic
    @Column(name = "total_fee")
    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    @Basic
    @Column(name = "is_served")
    public byte getIsServed() {
        return isServed;
    }

    public void setIsServed(byte isServed) {
        this.isServed = isServed;
    }
}
