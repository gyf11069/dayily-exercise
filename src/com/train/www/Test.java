package com.train.www;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[]args){
        Demo2 d2=new Demo2();
        d2.getI();
        System.out.println("Demo1中的i值:"+Demo1.i);

       /*动态添加集合元素
        A an = new A();
        List<A> l =new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("请输入添加的进程数：");
        int n =input.nextInt();
        for (int i = 0; i <n ; i++) {
            System.out.println("请输入进程的id：");
            an.id=input.nextInt();
            System.out.println("请输入进程的name：");
            an.name=input.next();
            l.add(an);
        }
        for (A a:l){
            System.out.println(a);
        }
        */


    }
}

/*
class A{
    int id;
    String name;
    public A(){}

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}*/
