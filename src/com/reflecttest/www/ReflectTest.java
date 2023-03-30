package com.reflecttest.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-08-03 11:25
 */
public class ReflectTest {
    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.reflecttest.www.User");

            /*newInstance()方法从JDK9之后过时，该方法会调用User类的无参构造
             ，实例化User对象*/
            Object obj = c.newInstance();

            //返回：com.reflecttest.www.User@6e8dacdf
            System.out.println(obj);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
    }
}

