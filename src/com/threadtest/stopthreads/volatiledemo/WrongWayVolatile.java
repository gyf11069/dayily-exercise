package com.threadtest.stopthreads.volatiledemo;

/**
 * @author gyunf
 * CreateTime 2022-03-27 16:35
 * Describe
 */
public class WrongWayVolatile implements Runnable {
    /*对所有线程都是可见的*/
    private volatile boolean calceled =false;

    @Override
    public void run() {
        int num =0;
        try {
            /*当calceled=true时中断倍数计算*/
            while(num <= 10000 && !calceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                /*减慢计算的速度*/
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        /*执行五秒后停止*/
        Thread.sleep(5000);
        /*设置calceled = true以停止程序的执行*/
        wrongWayVolatile.calceled = true;
    }
}
