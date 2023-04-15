package com.MFQ;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-24 10:35
 */
public class MSFQS {
    /*三个队列*/
    private static Queue<Progress> firstQueue = new LinkedList<>();
    private static Queue<Progress> secondQueue = new LinkedList<>();
    private static Queue<Progress> thirdQueue = new LinkedList<>();
    private static int firstTime;  //第一队列cpu时间片
    private static int secondTime; //第二队列cpu时间片
    private static int thirdTime;  //第三队列cpu时间片
    private static int proNum;     //进程数量
    private static Scanner sc = new Scanner(System.in);

    /**
     * 内部进程类：模拟进程
     */
    private static class Progress implements Comparable<Progress> {
        String id;     //进程标识符
        int reachTime; //到达时间
        int cpuTime;   //运行时间
        int needTime;  //仍需时间
        char state;    //进程状态

        /*重排输出格式*/
        @Override
        public String toString() {
            System.out.println();
            return String.format("进程%s: %10d %7d %8d %7c\n", id, reachTime, cpuTime, needTime, state);
        }

        /*重写比较器*/
        @Override
        public int compareTo( Progress b ) {
            //按reachTime从小到大排序
            return Float.compare(reachTime, b.reachTime);
        }
    }
    /**
     * 进程调度算法：Multi-stage feedback queue scheduling algorithm
     */
    private static void progressScheduling(Progress[] pro){
        int firstCpu = firstTime;
        int secondCpu = secondTime;
        int thirdCpu = thirdTime;
        int currentTime = 0;
        int num = 0;
        //System.out.println(Arrays.toString(pro));
        /*当有进程未运行时或进程队列不为空时，以每1时间片为单位*/
        while(num < proNum || !firstQueue.isEmpty() || !secondQueue.isEmpty() || !thirdQueue.isEmpty()){
            /*当前时刻有进程到达，则添加入第一队列*/
            while(num < proNum && pro[num].reachTime == currentTime)
                firstQueue.offer(pro[num++]);
            //打印上一秒各队列进程状态
            viewMenu(currentTime);
            /*当前为队列1在运行进程*/
            if(!firstQueue.isEmpty()){
                if (secondQueue.peek() != null) secondQueue.peek().state = 'R';
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                firstQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                firstTime -= 1;
                //更新当前时间：+1
                currentTime++;
                //进程正在运行，状态：E.
                if(firstQueue.peek().needTime > 0){
                    firstQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(firstTime == 0) {
                        firstQueue.peek().state = 'R';
                        secondQueue.offer(firstQueue.poll());
                        firstTime = firstCpu;
                    }

                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else if(firstQueue.peek().needTime == 0){
                    firstQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(firstQueue.peek());
                    Objects.requireNonNull(firstQueue.poll());
                    firstTime = firstCpu;
                }
            }
            /*当前为队列2在运行进程*/
            else if(!secondQueue.isEmpty()){
                if (thirdQueue.peek() != null) thirdQueue.peek().state = 'R';
                //仍需时间：-1
                secondQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                secondTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程运行完毕，状态：F，记录完成时刻并出队
                if(secondQueue.peek().needTime == 0){
                    secondTime = secondCpu;
                    secondQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(secondQueue.peek());
                    Objects.requireNonNull(secondQueue.poll());
                }
                //进程正在运行，状态：E.
                else if(secondQueue.peek().needTime > 0){
                    secondQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(secondTime == 0) {
                        secondQueue.peek().state = 'R';
                        thirdQueue.offer(secondQueue.poll());
                        secondTime = secondCpu;
                    }
                }
            }
            /*当前为队列3在运行进程*/
            else if(!thirdQueue.isEmpty()){
                //仍需时间：-1
                thirdQueue.peek().needTime -= 1;
                //CPU剩余时间片：-1
                thirdTime -= 1;
                //更新当前时间：+1
                currentTime++;

                //进程正在运行，状态：R.
                if(thirdQueue.peek().needTime > 0){
                    thirdQueue.peek().state = 'E';
                    //当前队列CPU时间片用完而进程仍未运行完时，进程出队，入次优先级队尾
                    if(thirdTime == 0) {
                        thirdQueue.peek().state = 'R';
                        thirdQueue.offer(thirdQueue.poll());
                        thirdTime = thirdCpu;
                    }
                }
                //进程运行完毕，状态：F，记录完成时刻并出队
                else{
                    firstTime = firstCpu;
                    thirdQueue.peek().state = 'F';
                    System.out.printf("\n当前时刻：%d,此进程运行结束：\n",currentTime);
                    System.out.println(thirdQueue.peek());
                    Objects.requireNonNull(thirdQueue.poll());
                }
            }
        }
    }

    /**
     * 输入面板：获取到进程数组
     */
    private static Progress[] operator(){
        System.out.println("-----------------3118004950 柴政-----------------\n");
        System.out.println("欢迎进入多级队列反馈调度模拟系统，队列个数：3。\n\n");
        System.out.println("请按队列优先级从高到低的顺序输入各个队列的时间片长度：");
        firstTime = sc.nextInt();
        secondTime = sc.nextInt();
        thirdTime = sc.nextInt();
        System.out.print( "请输入进程数:" );
        proNum = sc.nextInt();

        /*获取到进程数组*/
        Progress[] pro = new Progress[proNum];
        System.out.println( "请依次输入进程标识符,进程到达时间,进程运行时间:" );
        for( int i = 0; i < proNum; i++ ) {
            pro[i] = new Progress();
            pro[i].id = sc.next();
            pro[i].reachTime = sc.nextInt();
            pro[i].cpuTime = sc.nextInt();
            pro[i].needTime = pro[i].cpuTime;
            pro[i].state = 'R';
        }
        //对进程按照compareTo()的要求按照到达时间排序
        Arrays.sort(pro);
        return pro;
    }
    /**
     * 输出面板：实时输出运行结果
     */
    private static void viewMenu(int currentTime){
        System.out.printf("\n当前时刻：%d\n",currentTime);
        System.out.println("---------------------------------------------");
        System.out.println("            到达时间 运行时间  剩余时间  状态");
        if(firstQueue.isEmpty()) System.out.println("队列一：空");
        else System.out.println("队列一：\n"+ firstQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        if(secondQueue.isEmpty()) System.out.println("队列二：空");
        else System.out.println("队列二：\n"+ secondQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        if(thirdQueue.isEmpty()) System.out.println("队列三：空");
        else System.out.println("队列三：\n"+ thirdQueue.toString()
                .replace("[", "").replace("]", "")
                .replace(", ", ""));
        System.out.println("=============================================");
    }

    /**
     * main()
     */
    public static void main(String[] args) {
        progressScheduling(operator());
    }
}

