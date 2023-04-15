package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-27 16:14
 * Describe
 */
public class StopThread implements Runnable {
    @Override
    public void run() {
        /*5个连队，一个连队10人，以连为单位发放武器*/
        for (int i = 0; i < 5; i++) {
            System.out.println("连队"+i+"开始领取武器！");
            for (int j = 0; j < 10; j++) {
                System.out.println("j = " + j);
                try {
                    /*模拟发武器消耗的时间*/
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队"+i+"领取武器完毕！");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            /*一秒后停止领取武器*/
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*停止领取武器*/
        thread.stop();
    }
}
