package com.org.model.stateLog;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/6.
 * 主机日志实体类
 */
@Entity
@Table(name = "host_state_log_table", schema = "dtcs_db")
public class HostStateLog extends StateLog {
    // 介绍请求的从机数
    private int acceptedSlaveRequestNumber;
    // 服务的从机数
    private int nowServeSlaveNumber;
    // 主机工作模式
    private int hostWorkMode;

    public HostStateLog() {
        super();
        acceptedSlaveRequestNumber = 0;
        nowServeSlaveNumber = 0;
        hostWorkMode = 0;
    }

    public HostStateLog(int keyId,
                        Date powerOnTime,
                        Date nowTime,
                        byte isPowerOn,
                        int acceptedSlaveRequstNumber,
                        int nowServeSlaveNumber,
                        int hostWorkMode) {
        super(keyId, powerOnTime, nowTime, isPowerOn);
        this.acceptedSlaveRequestNumber = acceptedSlaveRequstNumber;
        this.nowServeSlaveNumber = nowServeSlaveNumber;
        this.hostWorkMode = hostWorkMode;
    }

    public HostStateLog(Date powerOnTime, Date nowTime, byte isPowerOn, int acceptedSlaveRequestNumber, int nowServeSlaveNumber, int hostWorkMode) {
        super(powerOnTime, nowTime, isPowerOn);
        this.acceptedSlaveRequestNumber = acceptedSlaveRequestNumber;
        this.nowServeSlaveNumber = nowServeSlaveNumber;
        this.hostWorkMode = hostWorkMode;
    }

    @Basic
    @Column(name = "accepted_slave_request_number")
    public int getAcceptedSlaveRequestNumber() {
        return acceptedSlaveRequestNumber;
    }

    @Basic
    @Column(name = "now_serve_slave_number")
    public int getNowServeSlaveNumber() {
        return nowServeSlaveNumber;
    }

    @Basic
    @Column(name = "host_work_mode")
    public int getHostWorkMode() {
        return hostWorkMode;
    }

    public void setAcceptedSlaveRequestNumber(int acceptedSlaveRequstNumber) {
        this.acceptedSlaveRequestNumber = acceptedSlaveRequstNumber;
    }

    public void setNowServeSlaveNumber(int nowServeSlaveNumber) {
        this.nowServeSlaveNumber = nowServeSlaveNumber;
    }

    public void setHostWorkMode(int hostWorkMode) {
        this.hostWorkMode = hostWorkMode;
    }
}
