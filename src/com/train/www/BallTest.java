package com.train.www;

public class BallTest {
    public static void main(String[] args) {
        System.out.println(Ball.pi);
        Ball a=new Ball(10);
        a.pi=3.142;
        System.out.println(a.getArea());
        Ball b=new Ball(8);
        b.pi=3.1415926;
        System.out.println(b.getArea());
        System.out.println(a.pi);
        System.out.println(b.pi);
    }
}
