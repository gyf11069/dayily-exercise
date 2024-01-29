package com.gyunf;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void distinctList() {
      /*  // create two lists
        List<String> srclst = new ArrayList<String>(5);
//        List<String> destlst = new ArrayList<String>(10);
        List<String> destlst = null;

        // populate two lists
        srclst.add("Java");
        srclst.add("is");
        srclst.add("best");

//        destlst.add("C++");
//        destlst.add("is");
//        destlst.add("older");

        // 注意：srclst或deslst若为null则会报java.lang.NullPointerException空指针异常
        // 判断两个集合是否不相交（是否有相同元素） false：有相同元素，true：没有相同元素
        boolean iscommon = Collections.disjoint(srclst, destlst);

        System.out.println("No commom elements: "+iscommon);*/

       /* *//*不同类型的list集合查看是否有不相交的元素*//*
        List<String> mylist1 = new ArrayList<>();
        mylist1.add("hello");
        mylist1.add("andy");
        mylist1.add("mhd");

        List<String> mylist2 = new Vector<>();
        mylist2.add("test");
        mylist2.add("mhd");

        List mylist3 = new Vector();
        mylist3.add(5);
        mylist3.add("mhd");

        Set<String> mylist4 = new HashSet<>();
        mylist4.add("set");
        mylist4.add("mhd");
        System.out.println("is mylist1 disjoin mylist2:" + Collections.disjoint(mylist1,mylist2));
        System.out.println("is mylist1 disjoin mylist3:" + Collections.disjoint(mylist1,mylist3));
        System.out.println("is mylist1 disjoin mylist4:" + Collections.disjoint(mylist1,mylist4));*/

        /*判断数组是否不相交*/
        Integer[] arr1 = {11,22,33};
        Integer[] arr2 = {33,44,55};
        Double[] arr3 = {11.0,2.2,3.3};

        System.out.println("is arr1 disjoin arr2:" + Collections.disjoint(Arrays.asList(arr1), Arrays.asList(arr2))); //false
        System.out.println("is arr1 disjoin arr3:" + Collections.disjoint(Arrays.asList(arr1), Arrays.asList(arr3))); //true
    }

    @Test
    public void RegexMatches(){
        List<String> textList = new ArrayList<>();
        String text1 = "{\"optType\":1416,\"CPA\":[{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232}]},\"tokenID\":\"1234554\"}" ;
        String text2 = "{\"optType\":1417,\"CPA\":[{\"MAC\":\"22222\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232}]},\"tokenID\":\"1234554\"}" ;
        String text3 =   "{\"optType\":1416,\"CPA\":[{\"MAC\":\"333333\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232},{\"MAC\":\"111111\",\"BSSID\":12312121,\"SN\":13232232}]},\"tokenID\":\"1234554\"}";
        textList.add(text1);
        textList.add(text2);
        textList.add(text3);
        // 定义一个正则表达式，用于提取optType和CPA冒号后的值
        String regex = "\"optType\":(1416),\"CPA\":(\\[.*?\\])";

// 创建一个Pattern对象，用于编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < textList.size(); i++) {
            String text = textList.get(i);
            // 创建一个Matcher对象，用于匹配文本
            Matcher matcher = pattern.matcher(text);

            // 如果找到匹配的结果，就打印出来
            if (matcher.find()) {
                // group(0)表示整个匹配的结果，group(1)表示第一个括号内的分组，group(2)表示第二个括号内的分组
                System.out.println("optType的值是：" + matcher.group(1));
                System.out.println("CPA的值是：" + matcher.group(2));
            } else {
                // 如果没有找到匹配的结果，就提示用户
                System.out.println("没有匹配到optType和CPA的值");
            }
        }

    }
}
