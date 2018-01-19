package com.org.help;

import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/15.
 * ���ݹ����ࣺ
 * ����request_stop_statistic�������
 */
public class RequestStopData {
    // ���ȿ�ʼʱ��
    private Date requestTime;
    // ���Ƚ���ʱ��
    private Date stopTime;
    // ���ȷ���
    private int requestLevelWind;
    // ����ʱ��
    private long intervalTime;

    /**
     * @desc ���캯��
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestTime ���ȿ�ʼʱ��
     * @param stopTime ���Ƚ���ʱ��
     * @param requestLevelWind ���ȷ���
     * @param intervalTime ����ʱ��
     */
    public RequestStopData(Date requestTime, Date stopTime, int requestLevelWind, long intervalTime) {
        this.requestTime = requestTime;
        this.stopTime = stopTime;
        this.requestLevelWind = requestLevelWind;
        this.intervalTime = intervalTime;
    }

    /**
     * @desc ��ȡ���ȿ�ʼʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @return ���ȿ�ʼʱ��
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * @desc ���õ��ȿ�ʼʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestTime ���ȿ�ʼʱ��
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @desc ��ȡ���Ƚ���ʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @return ���Ƚ���ʱ��
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * @desc ���õ��Ƚ���ʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @param stopTime ���Ƚ���ʱ��
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * @desc ��ȡ���ȷ���
     * @author Created by lizhongwei on 2017/4/15.
     * @return ���ȷ���
     */
    public int getRequestLevelWind() {
        return requestLevelWind;
    }

    /**
     * @desc ���õ��ȷ���
     * @author Created by lizhongwei on 2017/4/15.
     * @param requestLevelWind ���ȷ���
     */
    public void setRequestLevelWind(int requestLevelWind) {
        this.requestLevelWind = requestLevelWind;
    }

    /**
     * @desc ��ȡ����ʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @return ����ʱ��
     */
    public long getIntervalTime() {
        return intervalTime;
    }

    /**
     * @desc ���õ���ʱ��
     * @author Created by lizhongwei on 2017/4/15.
     * @param intervalTime ����ʱ��
     */
    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }
}
