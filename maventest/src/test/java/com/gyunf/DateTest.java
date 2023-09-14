package com.gyunf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by huang on 16/12/12.
 */
public class DateTest {

    private final static int GMT_START_YEAR = 1970;

    private final static String GMT_START_TIME_FOR_BEIJING = "1970-01-01 08:00:00";//东八区

    public static Date formatStrToDate(String dateTime, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("日期时间转换异常");
    }

    public static String dateTimeToStr(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }



    private static void calenderDateCalculate(String dateStr1, String dateStr2, String format) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(formatStrToDate(dateStr1, format));

        int yearC1 = c1.get(Calendar.YEAR);
        int monthC1 = c1.get(Calendar.MONTH);
        int dayC1 = c1.get(Calendar.DATE);
        int hourC1 = c1.get(Calendar.HOUR);
        int minuteC1 = c1.get(Calendar.MINUTE);
        int secondC1 = c1.get(Calendar.SECOND);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(formatStrToDate(dateStr2, format));

        Calendar c2c1 = Calendar.getInstance();
        long c2c1TimeMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
        c2c1.setTimeInMillis(c2c1TimeMillis);
        int yearC2C1 = c2c1.get(Calendar.YEAR);
        int monthC2C1 = c2c1.get(Calendar.MONTH);

        Calendar xx = Calendar.getInstance();
        xx.setTime(formatStrToDate(GMT_START_TIME_FOR_BEIJING, format));
        xx.set(Calendar.YEAR, yearC1 + yearC2C1 - GMT_START_YEAR);
        xx.set(Calendar.MONTH, monthC1 + monthC2C1);

        Calendar yy = Calendar.getInstance();
        yy.setTime(formatStrToDate(GMT_START_TIME_FOR_BEIJING, format));
        yy.set(Calendar.YEAR, yearC1);
        yy.set(Calendar.MONTH, monthC1);

        int day = (int) (c2c1.getTimeInMillis() / 1000L / 60 / 60 / 24) -
                (int) ((xx.getTime().getTime() - yy.getTime().getTime()) / 1000L / 60 / 60 / 24);

        xx.set(Calendar.DATE, dayC1 + day);

        yy.set(Calendar.DATE, dayC1);
        yy.setTimeInMillis(yy.getTime().getTime());

        int hour = (int) (c2c1.getTimeInMillis() / 1000L / 60 / 60) -
                (int) ((xx.getTime().getTime() - yy.getTime().getTime()) / 1000L / 60 / 60);

        xx.set(Calendar.HOUR, hourC1 + hour);

        yy.set(Calendar.HOUR, hourC1);
        yy.setTimeInMillis(yy.getTime().getTime());

        int minute = (int) (c2c1.getTimeInMillis() / 1000L / 60) -
                (int) ((xx.getTime().getTime() - yy.getTime().getTime()) / 1000L / 60);

        xx.set(Calendar.MINUTE, minuteC1 + minute);

        yy.set(Calendar.MINUTE, minuteC1);
        yy.setTimeInMillis(yy.getTime().getTime());

        int second = (int) (c2c1.getTimeInMillis() / 1000L) -
                (int) ((xx.getTime().getTime() - yy.getTime().getTime()) / 1000L);
        xx.set(Calendar.SECOND, secondC1 + second);

        yy.set(Calendar.SECOND, secondC1);
        yy.setTimeInMillis(yy.getTime().getTime());

        int i = c2c1.get(Calendar.YEAR) - GMT_START_YEAR;

        System.out.println("c2和c1相差" +
                (i > 0 ? (i + "年"):"" ) +
                (c2c1.get(Calendar.MONTH) > 0 ? (c2c1.get(Calendar.MONTH) + "个月零") : "") +
                (day > 0 ? (day + "天"):"") +
                (hour > 0 ? (hour + "小时") : "") +
                (minute > 0 ? (minute + "分") : "") +
                (second > 0 ? (second + "秒") : "")
                );

        System.out.println(dateTimeToStr(xx.getTime(), "yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] main) {
        String format = "yyyy-MM-dd HH:mm:ss";
        String dateStr2 = "2023-09-10 23:26:19";
        String dateStr1 = "2023-09-10 22:25:11";
        //c2和c1相差0年0个月零0天1小时1分8秒
        //2023-09-10 10:25:11
        calenderDateCalculate(dateStr1, dateStr2, format);
    }
}
