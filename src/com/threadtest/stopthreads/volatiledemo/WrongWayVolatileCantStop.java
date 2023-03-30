package com.threadtest.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * @author gyunf
 * CreateTime 2022-03-27 16:55
 * Describe 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢，所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费
 */
public class WrongWayVolatileCantStop {
    public static void main(String[] args) throws InterruptedException {
        /*当队列满了之后就放不进去，空的时候阻塞*/
        ArrayBlockingQueue stroage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(stroage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        /*十秒钟让生产者进行生产*/
        Thread.sleep(1000);

        Consumer consumer = new Consumer(stroage);
        /*消费者开始消费*/
        while(consumer.needMoreNums()){
            System.out.println(consumer.storage.take()+"被消费了");
            /*消费消耗的时间*/
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        /*让生产者停止生产*/
        producer.calceled = true;
    }
}

class Producer implements Runnable{
    BlockingQueue storage;
    public boolean calceled = false;

    public Producer(BlockingQueue storage){
        this.storage = storage;
    }

    @Override
    public void run() {
        int num =0;
        try {
            /*当calceled=true时中断倍数计算*/
            while(num <= 10000 && !calceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中了");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者结束运行！");
        }
    }
}

class Consumer{
    BlockingQueue storage;

    public Consumer(BlockingQueue storage){
        this.storage = storage;
    }

    /**
     * 有一定几率返回 false 和 true
     * @return
     */
    public boolean needMoreNums(){
        if(Math.random()>0.95){
            return false;
        }
        return true;
    }
}