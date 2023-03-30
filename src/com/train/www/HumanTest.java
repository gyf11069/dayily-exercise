package com.train.www;

public class HumanTest {
    public static void main(String[] args) {
        Human human=new Human();
        human.age=19;
        human.name="jack";
        System.out.println(human);
        human.sleep();
    }
}
