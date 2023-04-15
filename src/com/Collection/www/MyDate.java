package com.Collection.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-11 16:55
 */
public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(){}

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
