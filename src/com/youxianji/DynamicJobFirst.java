package com.youxianji;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-22 19:46
 *
 * 结束时间=开始时间+服务时间
 *
 * 周转时间=结束时间-到达时间
 *
 * 带权周转时间=周转时间/服务时间
 *
 */
public class DynamicJobFirst {
    /*@SuppressWarnings("resource")*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("高优先权调度算法开始：");
        System.out.println("请先输入作业的相关信息：（输入n代表结束）");
        //输入作业队列
        List<PCB> PCBs = new ArrayList<>();
        //执行作业队列
        List<PCB> execPCBs = new ArrayList<>();
        //作业信息初始化
        do {
            PCB pcb = new PCB();
            PCB initPCB = DynamicJobFirstUtil.init(pcb);
            PCBs.add(initPCB);
            System.out.println("是否要继续输入作业相关信息：（是输入y，否输入n）");
        } while (scanner.nextLine().equalsIgnoreCase("y"));
        //equalsIgnoreCase：当输入字符长度相等时忽略大小写
        System.out.println("-----------------");
        //确认初始化成功
        for (PCB pcb : PCBs) {
            System.out.println(pcb.toString());
        }


        //执行调度算法，将输入队列作业按照算法，插入到执行队列中
        execPCBs = DynamicJobFirstUtil.dispatchpcb(PCBs, execPCBs);
        System.out.println("-----------------");

        //求出周转时间和平均周转时间并记录在每一个作业实体中
        DynamicJobFirstUtil.turnRoundTime(execPCBs);
        for (PCB PCB : execPCBs) {
            System.out.println(PCB.toString());
        }

        System.out.println("-----------------");
        DynamicJobFirstUtil.showTime(execPCBs);
    }

}
