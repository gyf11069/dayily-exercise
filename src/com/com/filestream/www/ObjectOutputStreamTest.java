package com.com.filestream.www;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-22 8:44
 */
public class ObjectOutputStreamTest {
    public static void main(String[] args) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students",true));
        oos.writeObject(new Student(1,"zhangsan"));
        oos.writeObject(new Student(2,"wangwu"));
        oos.writeObject(new Student(3,"lisi"));
        oos.flush();
        oos.close();
    }
}
