package com.org.help;

import com.org.model.statistic.RequestStopStatistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/16.
 * 数据管理类：
 * 管理统计数据
 */
public class StatisticData {
    // 统计数据起始时间
    private String date;
    // 开机次数
    private int onCnt;
    // 关机次数
    private int offCnt;
    // 低风速时长
    private int lowWindTime;
    // 中风速时长
    private int middleWindTime;
    // 高风速时长
    private int highWindTime;
    // 调度次数
    private int scheduleCnt;
    // 能量消耗
    private double energyConsume;
    // 总费用
    private double totalFee;
    // 到底目标温度次数
    private int targetCnt;
    // 调度数据统计
    private List<RequestStopData> requestStopDatas;

    /**
     * @desc 默认构造函数
     * @author Created by lizhongwei on 2017/4/16.
     */
    public StatisticData() {
    }

    /**
     * @desc 构造函数
     * @author Created by lizhongwei on 2017/4/16.
     * @param date 统计数据起始时间
     * @param onCnt 开机次数
     * @param offCnt 关机次数
     * @param lowWindTime 低风速时长
     * @param middleWindTime 中风速时长
     * @param highWindTime 高风速时长
     * @param scheduleCnt 调度次数
     * @param energyConsume 能量消耗
     * @param totalFee 总费用
     * @param requestStopList 调度数据统计
     */
    public StatisticData(String date, int onCnt, int offCnt, int lowWindTime, int middleWindTime, int highWindTime, int scheduleCnt, double energyConsume, double totalFee, List<RequestStopData> requestStopList) {
        this.date = date;
        this.onCnt = onCnt;
        this.offCnt = offCnt;
        this.lowWindTime = lowWindTime;
        this.middleWindTime = middleWindTime;
        this.highWindTime = highWindTime;
        this.scheduleCnt = scheduleCnt;
        this.energyConsume = energyConsume;
        this.totalFee = totalFee;
        this.requestStopDatas = new ArrayList<>();
        this.requestStopDatas.addAll(requestStopList);
    }

    /**
     * @desc 获取统计数据起始时间
     * @author Created by lizhongwei on 2017/4/16.
     * @return 统计数据起始时间
     */
    public String getDate() {
        return date;
    }

    /**
     * @desc 设置统计数据起始时间
     * @author Created by lizhongwei on 2017/4/16.
     * @param date 统计数据起始时间
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @desc 获取开机次数
     * @author Created by lizhongwei on 2017/4/16.
     * @return 开机次数
     */
    public int getOnCnt() {
        return onCnt;
    }

    /**
     * @desc 设置开机次数
     * @author Created by lizhongwei on 2017/4/16.
     * @param onCnt 统计开机次数
     */
    public void setOnCnt(int onCnt) {
        this.onCnt = onCnt;
    }

    /**
     * @desc 获取关机次数
     * @author Created by lizhongwei on 2017/4/16.
     * @return 关机次数
     */
    public int getOffCnt() {
        return offCnt;
    }

    /**
     * @desc 设置关机次数
     * @author Created by lizhongwei on 2017/4/16.
     * @param offCnt 统计关机次数
     */
    public void setOffCnt(int offCnt) {
        this.offCnt = offCnt;
    }

    /**
     * @desc 获取低风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @return 低风速时长
     */
    public int getLowWindTime() {
        return lowWindTime;
    }

    /**
     * @desc 设置低风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @param lowWindTime 低风速时长
     */
    public void setLowWindTime(int lowWindTime) {
        this.lowWindTime = lowWindTime;
    }

    /**
     * @desc 获取中风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @return 中风速时长
     */
    public int getMiddleWindTime() {
        return middleWindTime;
    }

    /**
     * @desc 设置中风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @param middleWindTime 中风速时长
     */
    public void setMiddleWindTime(int middleWindTime) {
        this.middleWindTime = middleWindTime;
    }

    /**
     * @desc 获取高风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @return 高风速时长
     */
    public int getHighWindTime() {
        return highWindTime;
    }

    /**
     * @desc 设置高风速时长
     * @author Created by lizhongwei on 2017/4/16.
     * @param highWindTime 高风速时长
     */
    public void setHighWindTime(int highWindTime) {
        this.highWindTime = highWindTime;
    }

    /**
     * @desc 获取调度次数
     * @author Created by lizhongwei on 2017/4/16.
     * @return 调度次数
     */
    public int getScheduleCnt() {
        return scheduleCnt;
    }

    /**
     * @desc 设置调度次数
     * @author Created by lizhongwei on 2017/4/16.
     * @param scheduleCnt 调度次数
     */
    public void setScheduleCnt(int scheduleCnt) {
        this.scheduleCnt = scheduleCnt;
    }

    /**
     * @desc 获取能量消耗
     * @author Created by lizhongwei on 2017/4/16.
     * @return 能量消耗
     */
    public double getEnergyConsume() {
        return energyConsume;
    }

    /**
     * @desc 设置能量消耗
     * @author Created by lizhongwei on 2017/4/16.
     * @param energyConsume 能量消耗
     */
    public void setEnergyConsume(double energyConsume) {
        this.energyConsume = energyConsume;
    }

    /**
     * @desc 获取总费用
     * @author Created by lizhongwei on 2017/4/16.
     * @return 总费用
     */
    public double getTotalFee() {
        return totalFee;
    }

    /**
     * @desc 设置总费用
     * @author Created by lizhongwei on 2017/4/16.
     * @param totalFee 总费用
     */
    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @desc 获取调度数据统计
     * @author Created by lizhongwei on 2017/4/16.
     * @return 调度数据统计
     */
    public List<RequestStopData> getRequestStopDatas() {
        return requestStopDatas;
    }

    /**
     * @desc 设置调度数据统计
     * @author Created by lizhongwei on 2017/4/16.
     * @param requestStopDatas 调度数据统计
     */
    public void setRequestStopList(List<RequestStopData> requestStopDatas) {
        this.requestStopDatas = requestStopDatas;
    }

    /**
     * @desc 获取达到目标温度次数
     * @author Created by lizhongwei on 2017/4/16.
     * @return 达到目标温度次数
     */
    public int getTargetCnt() {
        return targetCnt;
    }

    /**
     * @desc 设置达到目标温度次数
     * @author Created by lizhongwei on 2017/4/16.
     * @param targetCnt 达到目标温度次数
     */
    public void setTargetCnt(int targetCnt) {
        this.targetCnt = targetCnt;
    }
}
