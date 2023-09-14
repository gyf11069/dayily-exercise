package com.gyunf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void timeTransfrom() {
        /*时间戳转换为时间格式*/
        //
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong("1694359579000")));      // 时间戳转换成时间
        System.out.println("格式化结果：" + sd);

        /*时间加减计算
        * Calendar cal = Calendar.getInstance();
cal.setTime(new Date());
cal.add(Calendar.HOUR_OF_DAY, 1);
Date newDate = cal.getTime();
*
* LocalDateTime now = LocalDateTime.now();
LocalDateTime newTime = now.plusHours(1);
        *
        * */
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        Date newDate = cal.getTime();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        Date newDatePlus = cal.getTime();
        System.out.println(newDate); //Sun Sep 10 23:52:55 CST 2023
        System.out.println(newDatePlus); //Mon Sep 11 00:52:55 CST 2023
        System.out.println(newDate.getTime() +""); //1694361175559
        System.out.println(newDatePlus.getTime() +""); //1694364775559

    }
}
