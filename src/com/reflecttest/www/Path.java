package com.reflecttest.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-08-03 16:08
 */
public class Path {
    public static void main(String[] args) {

        /**
         * Thread.currentThread():当前线程对象
         * getContextClassLoader()：是当前线程对象的方法，可以获取当前线程类加载器对象
         * getResource()：(获取资源)是类加载器对象的方法，当前线程的类加载器默认从类的根路径下(从src开始)加载资源
         * getPath(): 获取路径
         */
        String path = Thread.currentThread().getContextClassLoader()
                .getResource("com/reflecttest/www/classinfo.properties").getPath();
        /*/E:/IdeaProjects/train/out/production/train/classinfo.properties*/
        System.out.println(path);
    }
}
