package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-20 17:21
 * Describe
 */
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;

            /*num <= 300 的目的是快速执行进入阻塞状态*/
            while(num <= 10000 && !Thread.currentThread().isInterrupted()){
                if(num % 100 == 0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                /*在循环内捕获异常*/
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        /*启动线程*/
        thread.start();
        /*当前线程睡眠0.5秒*/
        Thread.sleep(5000);
        /*通知子线程停止*/
        thread.interrupt();
    }
}
