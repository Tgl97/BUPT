package com.org.model.statistic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lizhongwei on 2017/4/7.
 * 调度信息实例
 * 记录各客户机的调度信息，包括风速变化请求，挂起信息等
 */
@Entity
@Table(name = "request_stop_statistic", schema = "dtcs_db")
public class RequestStopStatistic implements Serializable{
    private static final long serialVersionUID = 4L;

    private int keyId;
    private int ID;
    private Date requestTime;
    private Date stopTime;
    private int requestLevelWind;

    public RequestStopStatistic() {
        this.ID = 0;
        this.requestTime = null;
        this.stopTime = null;
        this.requestLevelWind = 0;
    }

    public RequestStopStatistic(int ID, Date requestTime, Date stopTime, int requestLevelWind) {
        this.ID = ID;
        this.requestTime = requestTime;
        this.stopTime = stopTime;
        this.requestLevelWind = requestLevelWind;
    }
	//写入相关信息
    public RequestStopStatistic(int keyId, int ID, Date requestTime, Date stopTime, int requestLevelWind) {
        this.keyId = keyId;
        this.ID = ID;
        this.requestTime = requestTime;
        this.stopTime = stopTime;
        this.requestLevelWind = requestLevelWind;
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
    @Column(name = "ID")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "request_time")
    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Basic
    @Column(name = "stop_time")
    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    @Basic
    @Column(name = "request_level_wind")
    public int getRequestLevelWind() {
        return requestLevelWind;
    }

    public void setRequestLevelWind(int requestLevelWind) {
        this.requestLevelWind = requestLevelWind;
    }
}
