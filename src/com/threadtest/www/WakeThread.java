package com.threadtest.www;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-31 15:10
 */
public class WakeThread {
    public static void main(String[] args) {
        /**
         *  创建集合
         */
        List list = new ArrayList();
        /**
         * 创建线程
         */
        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Comsumer(list));
        /**
         * 给两个线程起名字
         */
        t1.setName("生产线程");
        t2.setName("消费线程");
        /**
         * 启动线程
         */
        t1.start();
        t2.start();
    }
}

/**
 * 生产线程
 */
class Producer implements Runnable{

    private List list;

    public Producer(List list){
        this.list = list;
    }

    @Override
    public void run(){
        //生产者一直生产
        while(true){
            synchronized (list){
                if(list.size() > 0){
                    try {
                        //集合中有元素生产者线程进入等待状态，并释放list对象锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当集合中没有元素时进行生产
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName()+"生产了元素："+obj);
                //唤醒消费线程消费
                list.notify();
            }
        }
    }
}

/**
 * 消费线程
 */
class Comsumer implements Runnable{

    private List list;

    public Comsumer(List list){
        this.list = list;
    }

    @Override
    public void run(){
        //消费者一直消费
        while(true){
            synchronized(list){
                if(list.size() == 0){
                    try {
                        //当集合中没有元素时，消费线程进入等待状态，并释放list对象锁
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当集合中有元素时，消费线程进行消费
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName()+"消费了元素："+obj);
                //唤醒生产线程生产
                list.notify();
            }
        }
    }
}