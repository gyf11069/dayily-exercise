package com.com.filestream.www;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-22 9:00
 *
 */
/*
* java.io.InvalidClassException:
 * com.com.filestream.www.Student;
 * local class incompatible:
  * stream classdesc serialVersionUID = 415697255338574064,
 * local class serialVersionUID = -425961153909679584
* */
public class ObjectInputStreamTest {
    public static void main(String[] args) throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students"));

            Object obj = ois.readObject();
            //Student stu = (Student) ois.readObject();
            System.out.println(obj);

        ois.close();
    }


}
