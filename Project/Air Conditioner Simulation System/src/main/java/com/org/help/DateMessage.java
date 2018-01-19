package com.org.help;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lizhongwei on 2017/4/15.
 * 日期管理类：
 * 管理从页面获取的日期
 */
public class DateMessage {
    // 起始日期 形式：yyyy-MM-dd
    public String beginDate;
    // 终止日期 形式：yyyy-MM-dd
    public String endDate;
    // 统计粒度
    public String size;
    // 统计区间 形式：HH:mm:ss
    public String timeInterval;
    // 每次迭代后的起始时间 形式：yyyy-MM-dd HH:mm:ss
    private Date beginTime;

    /**
     * 构造函数
     * @author Created by lizhongwei on 2017/4/15.
     * @param beginDate 起始日期 形式：yyyy-MM-dd
     * @param endDate 终止日期 形式：yyyy-MM-dd
     * @param size 统计粒度
     * @param timeInterval 统计区间 形式：HH:mm:ss
     */
    public DateMessage(String beginDate, String endDate, String size, String timeInterval) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.size = size;
        this.timeInterval = timeInterval;
    }

    /**
     * @desc 获取天数
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return 返回天数
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
     * @desc 获取时间区间
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return 时间区间
     */
    public String[] timeSplice() {
        String be[] = timeInterval.split("-");

        return be;
    }

    /**
     * @desc 迭代天数
     * @auotor Created by lizhongwei on 2017/4/15.
     * @param days 要增加的天数
     * @return Date[0] 为迭代后起始时间，Date[1] 为迭代后终止时间
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

        // 获取当前日期在一年中的位置
        int beDay = beginCalendar.get(Calendar.DAY_OF_YEAR);

        // 设置迭代后日期，相比之前增加days天数
        beginCalendar.set(Calendar.DAY_OF_YEAR, beDay + days);
        endCalendar.set(Calendar.DAY_OF_YEAR, beDay + days);

        beginTime = beginCalendar.getTime();

        Date tmp[] = {beginCalendar.getTime(), endCalendar.getTime()};

        return tmp;
    }

    /**
     * @desc 每次迭代后的起始时间 形式：yyyy-MM-dd HH:mm:ss
     * @auotor Created by lizhongwei on 2017/4/15.
     * @return 迭代后起始时间字符串，形式：yyyy-MM-dd HH:mm:ss
     */
    public String getBeginTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(beginTime);
    }

}
