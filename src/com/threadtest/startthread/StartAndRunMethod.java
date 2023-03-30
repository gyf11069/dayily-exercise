package com.threadtest.startthread;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2022-03-20 9:56
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        /*输出：main；表示主线程，与我们想输出子线程相反*/
        runnable.run();

        /*输出：Thread-0；表示子线程*/
        new Thread(runnable).start();
    }

}
