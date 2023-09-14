package com.gyunf;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import org.apache.commons.lang3.StringUtils;


/**
 * cron表达式解析成中文 工具类
 */
public class CronExpParser
{
    /**
     * 解析corn表达式，生成指定日期的时间序列
     *
     * @param cronExpression cron表达式
     * @param cronDate cron解析日期
     * @param result crom解析时间序列
     * @return 解析成功失败
     */
//    public static boolean parser(String cronExpression, String cronDate, List<String> result)
//    {
//        if (cronExpression == null || cronExpression.length() < 1 || cronDate == null || cronDate.length() < 1)
//        {
//            return false;
//        }
//        else
//        {
//            CronExpression exp = null;
//            // 初始化cron表达式解析器
//            try
//            {
//                exp = new CronExpression(cronExpression);
//            }
//            catch (ParseException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                return false;
//            }
//
//            // 定义生成时间范围
//            // 定义开始时间，前一天的23点59分59秒
//            Calendar c = Calendar.getInstance();
//            String sStart = cronDate + " 00:00:00";
//            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date dStart = null;
//            try
//            {
//                dStart = sdf.parse(sStart);
//            }
//            catch (ParseException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            c.setTime(dStart);
//            c.add(Calendar.SECOND, -1);
//            dStart = c.getTime();
//
//            // 定义结束时间，当天的23点59分59秒
//            c.add(Calendar.DATE, 1);
//            Date dEnd = c.getTime();
//
//            // 生成时间序列
//            java.util.Date dd = dStart;
//            dd = exp.getNextValidTimeAfter(dd);
//            while ((dd.getTime() >= dStart.getTime()) && (dd.getTime() <= dEnd.getTime()))
//            {
//                result.add(sdf.format(dd));
//                dd = exp.getNextValidTimeAfter(dd);
//            }
//            exp = null;
//        }
//        return true;
//    }

    public static String translateToChinese(String cronExp) {
        try{
            if (cronExp == null || cronExp.length() < 1) {
                return "cron表达式为空";
            }
            // 初始化cron表达式解析器
            //CronExpression exp = new CronExpression(cronExp);
            String[] tmpCorns = cronExp.split(" ");
            StringBuffer sBuffer = new StringBuffer();
            if(tmpCorns.length == 6){
                //解析月
                if(!tmpCorns[4].equals("*")){
                    sBuffer.append(singleNode(tmpCorns[4],"月")) ;
                } else  {
                    sBuffer.append("每月");
                }
                //解析周
                if(!tmpCorns[5].equals("*") && !tmpCorns[5].equals("?")){
                    sBuffer.append(singleNode(tmpCorns[5], "周"));
                }
                //解析日
                if(!tmpCorns[3].equals("?")) {
                    if(!tmpCorns[3].equals("*")){
                        sBuffer.append(singleNode(tmpCorns[3],"日"));
                    } else{
                        sBuffer.append("每日");
                    }
                }
                //解析时
                if(!tmpCorns[2].equals("*")) {
                    sBuffer.append(singleNode(tmpCorns[2],"时") ) ;
                } else {
                    sBuffer.append("每时");
                }
                //解析分
                if(!tmpCorns[1].equals("*")) {
                    sBuffer.append(singleNode(tmpCorns[1],"分"))  ;
                } else {
                    sBuffer.append("每分");
                }

            }
            return sBuffer.toString().replace("每月每日","每日");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private final static String[] cronIndexToChinese = {"分", "时", "日", "月", "周"};

    private final  static String[] weekEn  = {"SUN","MON","TUE","WED","THU","FRI","SAT"};

    private final  static String[] weekNum  = {"1","2","3","4","5","6","7"};

    private final static String[] weekChina = {"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};

    private static String singleNode(String node,String unit)throws Exception{
        if (StringUtils.isBlank(node)){
            return "";
        }
        if ("*".equals(node)){
            return "";
        }
        //中文语法易读
        String midWord = "";
        if("月".equals(unit)){
            midWord = "个";
        }
        if ("时".equals(unit)){
            midWord = "小";
        }
        for (int i = 0; i < weekEn.length; i++) {
            if (node.contains(weekEn[i])){
                node =   node.replace(weekEn[i],weekChina[i]);
            }
        }

        if ("周".equals(unit)){
            for (int i = 0; i < weekNum.length; i++) {
                if (node.contains(weekNum[i])){
                    node =   node.replace(weekNum[i],weekChina[i]);
                }
            }
            //重置周单位
            unit = "";
        }

        if (node.contains("/")){
            String [] temp = node.split("/");
            if (temp.length != 2 ){
                throw new Exception("表达式格式错误:" + node);
            }
            return (!"0".equals(temp[0].trim())? temp[0] +unit+ "开始":"")+"每隔" +temp[1] +midWord+ unit;
        }
        //区间值
        if (node.contains("-")){
            String [] temp = node.split("-");
            return temp[0] + unit + "至" + temp[1] + unit;
        }
        //时间点
        if (node.contains(",")){
            String [] temp = node.split(",");
            return StringUtils.join(temp, unit + ",")+unit;
        }
        //在星期中表示7，月分钟表示最后一天31或30，6L表示这个月倒数第6天
        if ( "日".equals(unit) && node.contains("L")){
            return "每月最后一日";
        }
        if ("月".equals(unit) && node.contains("W")){
            return "最近工作日";
        }
        if ("?".equals(node)){
            return "";
        }
        return ("*".equals(node)?"每":"")+ node + unit;
    }


    //测试方法
    public static void main(String[] args)
    {
        //每日6-20时开始每隔2小时0分
        String CRON_EXPRESSION = "0 0 6-20/2 * * *";
        // 生成指定日期的CRON时间序列
        String CRON_DATE = "2016-04-26";
        //System.out.println(CRON_EXPRESSION);
        try {
            String s = CronExpParser.translateToChinese(CRON_EXPRESSION);
            System.out.println(s);
            System.out.println(s.substring(s.indexOf("小时") -1,s.indexOf("小时") ));
            System.out.println(s.substring(s.indexOf("分") -1,s.indexOf("分") ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*List<String> lTime = new ArrayList<String>();
        if(!CronExpParser.parser(CRON_EXPRESSION, CRON_DATE, lTime)){
            System.out.println("无法生成Cron表达式：日期,"+CRON_DATE+";不符合规则cron表达式："+CRON_EXPRESSION);
        }
        for(int i=0;i<lTime.size();i++){
            System.out.println(lTime.get(i));
        }*/

    }
}
