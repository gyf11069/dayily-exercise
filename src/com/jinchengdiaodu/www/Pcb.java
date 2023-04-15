package com.jinchengdiaodu.www;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-21 22:08
 */
public class Pcb {
    /**
     *  进程名
     */
     String name;
    /**
     * 到达时间
     */
     int arriveTime;
    /**
     * 服务时间
     */
     int serveTime;
    /**
     * 开始时间
     */
    int beginTime;
    /**
     * 结束时间
     */
    int finshTime;
    /**
     * 周转时间
     */
    int roundTime;
    /**
     * 带权周转时间
     */
    double aveRoundTime;
    /**
     * 在时间轮转调度算法中，记录该进程真实服务时间已经用时的时长
     */
    double clock=0;
    /**
     * 记录每个进程到达后的等待时间，只用于最高响应比优先调度算法中
     */
    int waitTime;
    /**
     * 在RR算法中标识开始时间是否第一次计算
     */
    boolean firstTimeTag=false;

    public Pcb() {

    }
    public Pcb(String name, int arriveTime, int serveTime, double priority) {
        super();
        this.name = name;
        this.arriveTime = arriveTime;
        this.serveTime = serveTime;
        this.waitTime=0;
    }

    @Override
    public String toString() {
        String info=new String("进程名：P"+this.name);
        return info;
    }

}
