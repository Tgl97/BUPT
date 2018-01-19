package com.org.model.stateLog;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/6.
 * ������־���ӻ���־ʵ���ุ��
 */
@MappedSuperclass
public class StateLog implements Serializable{
    protected static final long serialVersionUID = 2L;

    // ����
    protected int keyId;
    // ����ʱ��
    protected Date powerOnTime;
    // ��ǰʱ��
    protected Date nowTime;
    // �Ƿ񿪻�
    protected byte isPowerOn;

    public StateLog() {
        //keyId = 0;
        powerOnTime = null;
        nowTime = null;
        isPowerOn = 0;
    }

    public StateLog(int keyId, Date powerOnTime, Date nowTime, byte isPowerOn) {
        this.keyId = keyId;
        this.powerOnTime = powerOnTime;
        this.nowTime = nowTime;
        this.isPowerOn = isPowerOn;
    }

    public StateLog(Date powerOnTime, Date nowTime, byte isPowerOn) {
        this.powerOnTime = powerOnTime;
        this.nowTime = nowTime;
        this.isPowerOn = isPowerOn;
    }

    @Id
    @Column(name = "key_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public int getKeyId() {
        return keyId;
    }

    @Basic
    @Column(name = "power_on_time")
    public Date getPowerOnTime() {
        return powerOnTime;
    }

    @Basic
    @Column(name = "now_time")
    public Date getNowTime() {
        return nowTime;
    }

    @Basic
    @Column(name = "is_power_on")
    public byte getIsPowerOn() {
        return isPowerOn;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public void setPowerOnTime(Date powerOnTime) {
        this.powerOnTime = powerOnTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public void setIsPowerOn(byte isPowerOn) {
        this.isPowerOn = isPowerOn;
    }
}
