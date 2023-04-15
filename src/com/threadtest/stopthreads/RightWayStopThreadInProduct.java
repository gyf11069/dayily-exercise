package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-27 14:24
 * Describe 最佳实践: catch 捕获了InterruptedExcetion之后的优先选
 * 择:在方法签名中抛出异常，那么在run( )就会强制try/catch处理异常
 */
public class RightWayStopThreadInProduct implements Runnable {
    @Override
    public void run() {
        /*检查中断是否存在*/
        while(!Thread.currentThread().isInterrupted()){
            System.out.println("run方法执行");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("响应中断！");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        //try {
            /*本线程睡眠两秒，保证主线程发出中断时本线程还在睡眠期间*/
            Thread.sleep(2000);
        // catch (InterruptedException e) {
            //e.printStackTrace();
        //}
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct());
        /*启动线程*/
        thread.start();
        /*主线程睡眠一秒后发出中断*/
        Thread.sleep(1000);
        thread.interrupt();
    }
}
