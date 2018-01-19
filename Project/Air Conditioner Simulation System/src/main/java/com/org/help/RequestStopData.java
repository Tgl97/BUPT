package com.org.help;

import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/15.
 * 数据管理类：
 * 管理request_stop_statistic表的数据
 */
public class RequestStopData {
    // 调度开始时间
    private Date requestTime;
    // 调度结束时间
    private Date stopTime;
    // 调度风速
    private int requestLevelWind;
    // 调度时长
    private long intervalTime;

    /**
     * @desc 构造函数
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestTime 调度开始时间
     * @param stopTime 调度结束时间
     * @param requestLevelWind 调度风速
     * @param intervalTime 调度时长
     */
    public RequestStopData(Date requestTime, Date stopTime, int requestLevelWind, long intervalTime) {
        this.requestTime = requestTime;
        this.stopTime = stopTime;
        this.requestLevelWind = requestLevelWind;
        this.intervalTime = intervalTime;
    }

    /**
     * @desc 获取调度开始时间
     * @author Created by lizhongwei on 2017/4/15.
     * @return 调度开始时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * @desc 设置调度开始时间
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestTime 调度开始时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @desc 获取调度结束时间
     * @author Created by lizhongwei on 2017/4/15.
     * @return 调度结束时间
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * @desc 设置调度结束时间
     * @author Created by lizhongwei on 2017/4/15.
     * @param stopTime 调度结束时间
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * @desc 获取调度风速
     * @author Created by lizhongwei on 2017/4/15.
     * @return 调度风速
     */
    public int getRequestLevelWind() {
        return requestLevelWind;
    }

    /**
     * @desc 设置调度风速
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestLevelWind 调度风速
     */
    public void setRequestLevelWind(int requestLevelWind) {
        this.requestLevelWind = requestLevelWind;
    }

    /**
     * @desc 获取调度时长
     * @author Created by lizhongwei on 2017/4/15.
     * @return 调度时长
     */
    public long getIntervalTime() {
        return intervalTime;
    }

    /**
     * @desc 设置调度时长
     * @author Created by lizhongwei on 2017/4/15.
     * @param intervalTime 调度时长
     */
    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }
}
