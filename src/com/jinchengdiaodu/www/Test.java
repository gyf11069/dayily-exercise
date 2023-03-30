package com.jinchengdiaodu.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-21 22:48
 */
public class Test {

    public static void main(String[] args) {
        ProcessMenu pm=new ProcessMenu();
        pm.init();//初始化容器
        //先来先服务
        pm.FCFS();
        pm.printProcess();
        //短进程优先
        pm.SJF();
        pm.printProcess();
        //时间片轮转调度算法
        pm.RR();
        pm.printProcess();
        //优先级调度算法
        pm.HRN();
        pm.printProcess();
    }

}

