package com.com.filestream.www;

import java.io.Serializable;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-22 8:44
 */
public class Student implements Serializable {
    private int no;
    private String name;
    private int age;

    public Student(){}

    public Student(int no,String name){
        this.no = no;
        this.name = name;
    }

    public void setNo(int no){
        this.no = no;
    }

    public int getNo(){
        return no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
