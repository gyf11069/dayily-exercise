package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-20 16:43
 * Describe 如果在执行过程中，每次循环都会调用sleep或wait等方法，
 * 那么不需要每次迭代都检查是否中断
 */
public class RightWayWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            int num = 0;

            try {
                /*num <= 300 的目的是快速执行进入阻塞状态*/
                while(num <= 10000 ){
                    if(num % 100 == 0){
                        System.out.println(num+"是100的倍数");
                    }
                    num++;

                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
