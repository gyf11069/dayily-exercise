package com.train.www;

public class Demo2 {
        public void getI(){
            Demo1.i+=10;
            Demo1 d=new Demo1();
           d.j+=10;         //若写Demo1.j+=10;则错：不能从静态上下文引用非静态字
            // static int i是全局变量，而j只在Demo1中有用，要用j必须创建新的类
            System.out.println("Demo2中的i值:"+Demo1.i);
            System.out.println("Demo2中的j值:"+d.j);//Demo1.j(j不是静态变量使用报错）
        }

    }

