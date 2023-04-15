package com.threadtest.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-28 10:56
 */
public class Test {
    public static void main(String[] args) {
        Account act = new Account("act01",10000);
        Thread t1 =new AccountThread(act);
        Thread t2 =new AccountThread(act);
        t1.start();
        t2.start();
    }
}
