package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-20 15:26
 * Describe 带有sleep的中断线程写法，在当线程休眠的时候中断
 */
public class RightWayWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;

            try {
                /*num <= 300 的目的是快速执行进入阻塞状态*/
                while(num <= 300 && !Thread.currentThread().isInterrupted()){
                    if(num % 100 == 0){
                        System.out.println(num+"是100的倍数");
                    }
                    num++;
                }
                /*当前线程睡眠一秒,在当线程休眠的时候中断*/
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        /*启动线程*/
        thread.start();
        /*当前线程睡眠0.5秒*/
        Thread.sleep(500);
        /*通知子线程停止*/
        thread.interrupt();
    }
}
