package com.threadtest.stopthreads;

/**
 * @author gyunf
 * CreateTime 2022-03-27 14:24
 * Describe 最佳实践2: 在非run()方法中捕获中断异常后，使用Thread.currentThread().interrupt()
 * 来恢复设置中断状态，以便在后续执行中仍然能检测到刚才发生的中断
 */
public class RightWayStopThreadInProduct2 implements Runnable {
    @Override
    public void run() {
        /*检查中断是否存在*/
        while(true){
            /*发生中断*/
            if(Thread.currentThread().isInterrupted()){
                System.out.println("发生中断，程序运行结束！");
                break;
            }
            throwInMethod();
        }
    }

    private void throwInMethod(){
        try {
            /*本线程睡眠两秒，保证主线程发出中断时本线程还在睡眠期间*/
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct2());
        /*启动线程*/
        thread.start();
        /*主线程睡眠一秒后发出中断*/
        Thread.sleep(1000);
        thread.interrupt();
    }
}
