package com.mb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期的一些通用方法
 */
public class DateUtil {

    /**
     * 根据日期获取日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String getDateStrByDate(Date date, String pattern) {
        String dateStr = null;
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            dateStr = sdf.format(date);
        }
        return dateStr;
    }

    /**
     * 获得某一周的周一和周日
     *
     * @param date 某一周
     * @return
     */
    public static String getTimeInterval(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());// 当前周的周一
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());// 当前周的周日
        return imptimeBegin + "," + imptimeEnd;
    }

    /**
     * 获得上一周的周一和周日
     *
     * @return
     */
    public static String getLastTimeInterval() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        String lastBeginDate = sdf.format(calendar1.getTime());// 上周一
        String lastEndDate = sdf.format(calendar2.getTime());// 上周日
        return lastBeginDate + "," + lastEndDate;
    }

    /**
     * 获得某一周的周日
     *
     * @return
     */
    public static String getSundayByDate(Date date) {
        String dateStr = getTimeInterval(date);// 某一周的周一和周日
        String tempSunday = dateStr.split(",")[1];
        return tempSunday;
    }

    /**
     * 获得上周日的日期
     *
     * @return
     */
    public static String getLastSunday() {
        String dateStr = getLastTimeInterval();// 上一周的周一和周日
        String lastSunday = dateStr.split(",")[1];
        return lastSunday;
    }

    public static void main(String[] args) {
        System.out.println(getTimeInterval(new Date()));
        System.out.println(getSundayByDate(new Date()));
        System.out.println(getLastTimeInterval());
        System.out.println(getLastSunday());
    }

}
