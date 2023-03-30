package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-20 12:02
 * Describe run方法内没有sleep、wait方法时停止线程
 */
public class RightWayWithoutSleep implements Runnable{
    @Override
    public void run() {
        int num = 0;
        /*加上“!Thread.currentThread().isInterrupted()” 来判断是否通知中断 */
        while(!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2){
            if(num % 10000 == 0){
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayWithoutSleep());
        /*启动新线程*/
        thread.start();
        /*主线程睡眠1秒*/
        Thread.sleep(1000);
        /*通知子线程停止运行*/
        thread.interrupt();
    }
}
