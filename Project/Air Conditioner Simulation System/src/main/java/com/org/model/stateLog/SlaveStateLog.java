package com.org.model.stateLog;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/6.
 * �ӻ���־ʵ����
 */
@Entity
@Table(name = "slave_state_log_table", schema = "dtcs_db")
public class SlaveStateLog extends StateLog {
    // �ӻ���
    private int slaveId;
    // ��ǰ�¶�
    private int slaveNowTemp;
    // Ŀ���¶�
    private int slaveTargetTemp;
    // ��ǰ����
    private int slaveNowWindLevel;
    // Ŀ�����
    private int slaveTargetWindLevel;
    // ����ģʽ
    private int slaveWorkMode;
    // �ӻ�״̬
    private int slaveState;
    // �ܷ���
    private int totalFee;
    // �Ƿ����
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
