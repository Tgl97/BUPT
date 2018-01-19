package com.org.help;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/15.
 * ���ڹ����ࣺ
 * �����ҳ���ȡ������
 */
public class DateMessage {
    // ��ʼ���� ��ʽ��yyyy-MM-dd
    public String beginDate;
    // ��ֹ���� ��ʽ��yyyy-MM-dd
    public String endDate;
    // ͳ������
    public String size;
    // ͳ������ ��ʽ��HH:mm:ss
    public String timeInterval;
    // ÿ�ε��������ʼʱ�� ��ʽ��yyyy-MM-dd HH:mm:ss
    private Date beginTime;

    /**
     * ���캯��
     * @author Created by lizhongwei on 2017/4/15.
     * @param beginDate ��ʼ���� ��ʽ��yyyy-MM-dd
     * @param endDate ��ֹ���� ��ʽ��yyyy-MM-dd
     * @param size ͳ������
     * @param timeInterval ͳ������ ��ʽ��HH:mm:ss
     */
    public DateMessage(String beginDate, String endDate, String size, String timeInterval) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.size = size;
        this.timeInterval = timeInterval;
    }

    /**
     * @desc ��ȡ����
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return ��������
     * @throws ParseException
     */
    public int getDays() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = sdf.parse(beginDate);
        Date ed = sdf.parse(endDate);
        Calendar bc = Calendar.getInstance();
        Calendar ec = Calendar.getInstance();
        bc.setTime(bd);
        ec.setTime(ed);

        return ec.get(Calendar.DAY_OF_YEAR) - bc.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * @desc ��ȡʱ������
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return ʱ������
     */
    public String[] timeSplice() {
        String be[] = timeInterval.split("-");

        return be;
    }

    /**
     * @desc ��������
     * @auotor Created by lizhongwei on 2017/4/15.
     * @param days Ҫ���ӵ�����
     * @return Date[0] Ϊ��������ʼʱ�䣬Date[1] Ϊ��������ֹʱ��
     * @throws ParseException
     */
    public Date[] addDay(int days) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String be[] = timeSplice();
        Date bd = sdf.parse(beginDate + " " + be[0] + ":00");
        Date ed = sdf.parse(beginDate + " " + be[1] + ":00");
        Calendar beginCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();

        beginCalendar.setTime(bd);
        endCalendar.setTime(ed);

        // ��ȡ��ǰ������һ���е�λ��
        int beDay = beginCalendar.get(Calendar.DAY_OF_YEAR);

        // ���õ��������ڣ����֮ǰ����days����
        beginCalendar.set(Calendar.DAY_OF_YEAR, beDay + days);
        endCalendar.set(Calendar.DAY_OF_YEAR, beDay + days);

        beginTime = beginCalendar.getTime();

        Date tmp[] = {beginCalendar.getTime(), endCalendar.getTime()};

        return tmp;
    }

    /**
     * @desc ÿ�ε��������ʼʱ�� ��ʽ��yyyy-MM-dd HH:mm:ss
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return ��������ʼʱ���ַ�������ʽ��yyyy-MM-dd HH:mm:ss
     */
    public String getBeginTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(beginTime);
    }

}
