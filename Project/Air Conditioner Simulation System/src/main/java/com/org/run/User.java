package com.org.run;

import com.org.model.statistic.OnOffStatistic;
import com.org.model.statistic.RequestStopStatistic;
import com.org.repository.RequestStopStatisticRepository;

import java.io.DataOutputStream;
import java.util.Date;

/**
 * Created by Genglintong, Tiankuo on 2017/4/23.
 * 客户机实例
 * 客户机属性和内部方法实现
 */
public class User {
    private int keyId;
    private int user_id;//客户id
    private int start_temp;//起始温度
    private int former_wind;//之前风速
    private int wind;//风速
    private int now_temp;//当前温度
    private boolean is_dd;//是否调度
    private boolean is_open;//是否开机
    private DataOutputStream out;//给从机发送信息
    private double free;//从机费用
    private int dd_num;//调度时间

    private Date on_time; //开机时间
    private Date off_time; //关机时间
    private int low_wind_time;//低风速时长
    private int medium_wind_time;//中风速时长
    private int high_wind_time;//高风速时长
    private int serv_time;
    private Date start_time;//调度起始时间
    private Date end_time;//调度结束时间
    private RequestStopStatistic intr;
    private OnOffStatistic onOff;

    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getServ_time() {
        return serv_time;
    }

    public void setServ_time(int serv_time) {
        this.serv_time = serv_time;
    }

    public int getDd_num() {
        return dd_num;
    }

    public void setDd_num(int dd_num) {
        this.dd_num = dd_num;
    }

    public double getFree() {
        return free;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public boolean isIs_dd() {
        return is_dd;
    }

    public int getNow_temp() {
        return now_temp;
    }

    public int getWind() {
        return wind;
    }

    public int getFinal_temp() {
        return final_temp;
    }

    public int getStart_temp() {
        return start_temp;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setIs_dd(boolean is_dd) {
        this.is_dd = is_dd;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public void setNow_temp(int now_temp) {
        this.now_temp = now_temp;
    }

    public void setWind(int wind) {
        this.wind = wind;
    }

    public void setFinal_temp(int final_temp) {
        this.final_temp = final_temp;
    }

    public void setStart_temp(int start_temp) {
        this.start_temp = start_temp;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public RequestStopStatistic getIntr() {
        return intr;
    }

    public void setIntr(RequestStopStatistic intr) {
        intr.setID(getKeyId());
        intr.setRequestTime(getStart_time());
        intr.setStopTime(getEnd_time());
        intr.setRequestLevelWind(getWind());
        this.intr = intr;
    }

    public void setFormer_wind(int former_wind) {
        this.former_wind = former_wind;
    }

    private int final_temp;//目标温度

    public int getFormer_wind() {
        return former_wind;
    }

    public Date getOn_time() {
        return on_time;
    }

    public Date getOff_time() {
        return off_time;
    }

    public int getLow_wind_time() {
        return low_wind_time;
    }

    public int getMedium_wind_time() {
        return medium_wind_time;
    }

    public int getHigh_wind_time() {
        return high_wind_time;
    }

    public void setOn_time(Date on_time) {
        this.on_time = on_time;
    }

    public void setOff_time(Date off_time) {
        this.off_time = off_time;
    }

    public void setLow_wind_time(int low_wind_time) {
        this.low_wind_time = low_wind_time;
    }

    public void setMedium_wind_time(int medium_wind_time) {
        this.medium_wind_time = medium_wind_time;
    }

    public void setHigh_wind_time(int high_wind_time) {
        this.high_wind_time = high_wind_time;
    }


    public OnOffStatistic getOnOff() {
        return onOff;
    }

    public void setOnOff(OnOffStatistic onOff) {
        onOff.setKeyId(getKeyId());
        onOff.setLowLevelWindTime(getLow_wind_time());
        onOff.setMiddleLevelWindTime(getMedium_wind_time());
        onOff.setHighLevelWindTime(getHigh_wind_time());
        onOff.setSlaveId(getUser_id());
        onOff.setPowerOnTime(getOn_time());
        onOff.setPowerOffTime(getOff_time());
        onOff.setTotalFee(getFree());
        this.onOff = onOff;
    }

}
