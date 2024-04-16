package com.gyunf;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //字符串截取
//        String userName = "tt";
//        String str = " OR t.username like"+"'%"+userName+"%'" + " OR t.sfzjh like "+"'%"+userName+"%'" + " OR t.wid like "+"'%"+userName+"%'";
//        String s = StringUtils.substringAfter(str, " OR");
//        System.out.println(s);

        String oldKey = "sss_12_2";
        StringUtils.substringBefore(oldKey, "_");
        String replace = oldKey.replace("_", "_subtobus_");
        System.out.println(replace);

      /*  //字符串替换
        String serviceTypes = "API,ON-LINE,FILE,VIEW,SUB";
        String[] split = serviceTypes.split(",");
//        List<String> split = Arrays.asList(serviceTypes.split(","));
        StringJoiner stringJoiner = new StringJoiner(",");
        for (int i = 0; i < split.length; i++) {
            switch (split[i]) {
                case "ON-LINE":
                    stringJoiner.add("数据查询");
                   break;
                case "SUB":
                    stringJoiner.add("数据订阅");
//                    split.get(i) = "数据订阅";
                    break;
                case "FILE":
                    stringJoiner.add("文件下载");
//                    split.get(i) = "文件下载";
                    break;
                case "API":
                    stringJoiner.add("API接口");
//                    split.get(i) = "API接口";
                    break;
                case "VIEW":
                    stringJoiner.add("视图");
//                    split.get(i) = "视图";
                    break;
            }
        }*/

//        serviceTypes.replaceAll("ON-LINE","数据查询");
//        serviceTypes.replaceAll("SUB","数据订阅");
//        serviceTypes.replaceAll("FILE","文件下载");
//        serviceTypes.replaceAll("API","API接口");
//        serviceTypes.replaceAll("VIEW","视图");
//        System.out.println(stringJoiner.toString());

        //时间字符串比较大小
//        String s = "2024-01";
//        String s2 = "2024-01";
//        System.out.println(s.compareTo(s2));
//        if (s.compareTo(s2) == 0){
//            System.out.println("equal");
//        }

//        char c = 0;
//        System.out.println("char初始化："+c);
//        System.out.println( "Hello World!" );

        // create two lists
//        List<String> srclst = new ArrayList<String>(5);
////        List<String> destlst = new ArrayList<String>(10);
//        List<String> destlst = new ArrayList<>();
//
//        // populate two lists
//        srclst.add("Java");
//        srclst.add("is");
//        srclst.add("best");
//
////        destlst.add("C++");
////        destlst.add("is");
////        destlst.add("older");
//
//        // check elements in both collections
//        boolean iscommon = Collections.disjoint(srclst, destlst);
//
//        System.out.println("No commom elements: "+iscommon);

    }


}

