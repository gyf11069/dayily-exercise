package com.gyunf.datastructure.array;

import org.docx4j.wml.R;

import java.util.Random;

public class CompareArrayQueueAndLoopQueue {
    //测试使用queue运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
    private static double testQueue(Queue<Integer> queue, int opCount) {
        //开始时计时（纳秒）
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));

        for (int j = 0; j < opCount; j++)
            queue.dequeue();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double arrayTime = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue,time:" + arrayTime + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double loopTime = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue,time:" + loopTime + "s");

        /*
        * 结果输出：主要还是数组队列删除元素时还需要重新移动所有元素的位置的操作比较耗时
        * ArrayQueue,time:1.618267041s
        * LoopQueue,time:0.006528125s
        * */
    }
}
