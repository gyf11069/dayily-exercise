package com.reflecttest.www;

import java.io.FileReader;
import java.util.Properties;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-08-03 17:02
 */
public class ReflectTest02 {
    public static void main(String[] args) throws Exception{
        //通过IO流读取classinfo.properties文件
        FileReader reader = new FileReader("src/com/reflecttest/www/classinfo.properties");
        //创建属性类对象Map,对应的key和value都是String
        Properties p = new Properties();
        //加载
        p.load(reader);
        //关闭流
        reader.close();
        //通过key获取value
        String className = p.getProperty("className");//com.reflecttest.www.User
        //通过反射机制实例化对象
        Class c = Class.forName(className);
        Object obj = c.newInstance();
        System.out.println(obj);
    }
}
