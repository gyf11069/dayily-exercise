package com.threadtest.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-28 10:22
 */
public class Account {
    private String actno;
    private double balance;

    public Account(){

    }

    public Account(String actno,double balance){
        this.actno = actno;
        this.balance = balance;
    }

    public void setActno(String actno){
        this.actno = actno;
    }

    public String getActno(){
        return actno;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    Object obj1 = new Object();

    //取款的方法
    public void widthBalance(double money){
        Object obj2 = new Object();
        //synchronized(obj1){
        //synchronized(obj2){
        synchronized(this) {
            //取款前的账户余额
            double before = this.getBalance();
            //取款后的账户余额
            double after = before - money;

            //模拟网络延迟
            try { //哪个线程先进来，哪个线程先睡1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //更新账户余额
            this.setBalance(after);
        }
    }
}
