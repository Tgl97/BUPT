package com.org.help;

import com.org.model.statistic.RequestStopStatistic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhongwei on 2017/4/16.
 * ���ݹ����ࣺ
 * ����ͳ������
 */
public class StatisticData {
    // ͳ��������ʼʱ��
    private String date;
    // ��������
    private int onCnt;
    // �ػ�����
    private int offCnt;
    // �ͷ���ʱ��
    private int lowWindTime;
    // �з���ʱ��
    private int middleWindTime;
    // �߷���ʱ��
    private int highWindTime;
    // ���ȴ���
    private int scheduleCnt;
    // ��������
    private double energyConsume;
    // �ܷ���
    private double totalFee;
    // ����Ŀ���¶ȴ���
    private int targetCnt;
    // ��������ͳ��
    private List<RequestStopData> requestStopDatas;

    /**
     * @desc Ĭ�Ϲ��캯��
     * @author Created by lizhongwei on 2017/4/16.
     */
    public StatisticData() {
    }

    /**
     * @desc ���캯��
     * @author Created by lizhongwei on 2017/4/16.
     * @param date ͳ��������ʼʱ��
     * @param onCnt ��������
     * @param offCnt �ػ�����
     * @param lowWindTime �ͷ���ʱ��
     * @param middleWindTime �з���ʱ��
     * @param highWindTime �߷���ʱ��
     * @param scheduleCnt ���ȴ���
     * @param energyConsume ��������
     * @param totalFee �ܷ���
     * @param requestStopList ��������ͳ��
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
     * @desc ��ȡͳ��������ʼʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @return ͳ��������ʼʱ��
     */
    public String getDate() {
        return date;
    }

    /**
     * @desc ����ͳ��������ʼʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @param date ͳ��������ʼʱ��
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @desc ��ȡ��������
     * @author Created by lizhongwei on 2017/4/16.
     * @return ��������
     */
    public int getOnCnt() {
        return onCnt;
    }

    /**
     * @desc ���ÿ�������
     * @author Created by lizhongwei on 2017/4/16.
     * @param onCnt ͳ�ƿ�������
     */
    public void setOnCnt(int onCnt) {
        this.onCnt = onCnt;
    }

    /**
     * @desc ��ȡ�ػ�����
     * @author Created by lizhongwei on 2017/4/16.
     * @return �ػ�����
     */
    public int getOffCnt() {
        return offCnt;
    }

    /**
     * @desc ���ùػ�����
     * @author Created by lizhongwei on 2017/4/16.
     * @param offCnt ͳ�ƹػ�����
     */
    public void setOffCnt(int offCnt) {
        this.offCnt = offCnt;
    }

    /**
     * @desc ��ȡ�ͷ���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @return �ͷ���ʱ��
     */
    public int getLowWindTime() {
        return lowWindTime;
    }

    /**
     * @desc ���õͷ���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @param lowWindTime �ͷ���ʱ��
     */
    public void setLowWindTime(int lowWindTime) {
        this.lowWindTime = lowWindTime;
    }

    /**
     * @desc ��ȡ�з���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @return �з���ʱ��
     */
    public int getMiddleWindTime() {
        return middleWindTime;
    }

    /**
     * @desc �����з���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @param middleWindTime �з���ʱ��
     */
    public void setMiddleWindTime(int middleWindTime) {
        this.middleWindTime = middleWindTime;
    }

    /**
     * @desc ��ȡ�߷���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @return �߷���ʱ��
     */
    public int getHighWindTime() {
        return highWindTime;
    }

    /**
     * @desc ���ø߷���ʱ��
     * @author Created by lizhongwei on 2017/4/16.
     * @param highWindTime �߷���ʱ��
     */
    public void setHighWindTime(int highWindTime) {
        this.highWindTime = highWindTime;
    }

    /**
     * @desc ��ȡ���ȴ���
     * @author Created by lizhongwei on 2017/4/16.
     * @return ���ȴ���
     */
    public int getScheduleCnt() {
        return scheduleCnt;
    }

    /**
     * @desc ���õ��ȴ���
     * @author Created by lizhongwei on 2017/4/16.
     * @param scheduleCnt ���ȴ���
     */
    public void setScheduleCnt(int scheduleCnt) {
        this.scheduleCnt = scheduleCnt;
    }

    /**
     * @desc ��ȡ��������
     * @author Created by lizhongwei on 2017/4/16.
     * @return ��������
     */
    public double getEnergyConsume() {
        return energyConsume;
    }

    /**
     * @desc ������������
     * @author Created by lizhongwei on 2017/4/16.
     * @param energyConsume ��������
     */
    public void setEnergyConsume(double energyConsume) {
        this.energyConsume = energyConsume;
    }

    /**
     * @desc ��ȡ�ܷ���
     * @author Created by lizhongwei on 2017/4/16.
     * @return �ܷ���
     */
    public double getTotalFee() {
        return totalFee;
    }

    /**
     * @desc �����ܷ���
     * @author Created by lizhongwei on 2017/4/16.
     * @param totalFee �ܷ���
     */
    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @desc ��ȡ��������ͳ��
     * @author Created by lizhongwei on 2017/4/16.
     * @return ��������ͳ��
     */
    public List<RequestStopData> getRequestStopDatas() {
        return requestStopDatas;
    }

    /**
     * @desc ���õ�������ͳ��
     * @author Created by lizhongwei on 2017/4/16.
     * @param requestStopDatas ��������ͳ��
     */
    public void setRequestStopList(List<RequestStopData> requestStopDatas) {
        this.requestStopDatas = requestStopDatas;
    }

    /**
     * @desc ��ȡ�ﵽĿ���¶ȴ���
     * @author Created by lizhongwei on 2017/4/16.
     * @return �ﵽĿ���¶ȴ���
     */
    public int getTargetCnt() {
        return targetCnt;
    }

    /**
     * @desc ���ôﵽĿ���¶ȴ���
     * @author Created by lizhongwei on 2017/4/16.
     * @param targetCnt �ﵽĿ���¶ȴ���
     */
    public void setTargetCnt(int targetCnt) {
        this.targetCnt = targetCnt;
    }
}
