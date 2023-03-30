package com.train.www;

public class Ball {
    static  double pi=3.14;
    int r;
    public Ball(int r){
        this.r=r;
    }
    public double getArea(){
        return pi*r*r;
    }
}
