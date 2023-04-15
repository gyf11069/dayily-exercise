package com.train.www;

public class Human {
    int age;
    String name;

@Override
      public String toString(){
    return "Human[age="+age+".name="+name+"]";
        }

    public void sleep(){
        System.out.println("睡觉");
    }
}
