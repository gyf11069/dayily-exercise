package com.youxianji;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-22 19:46
 */
public class PCB {
    //进程名
    private String pcbName;
    //进程到达时间
    private int pcbArrivalTime;
    //进程服务时间
    private int pcbServiceTime;
    //进程初始优先权限
    private int firstNum;
    //进程状态
    private char pcbState;
    //进程间的链接指针
    private PCB nextPCB;

    //进程开始时间
    private int pcbStartTime;
    //进程完成时间
    private int pcbOverTime;
    //进程周转时间
    private int pcbRoundTime;
    //进程带权周转时间
    private double pcbAvgRoundTime;

    //get和set方法

    public String getPcbName() {
        return pcbName;
    }

    public void setPcbName(String pcbName) {
        this.pcbName = pcbName;
    }

    public int getPcbArrivalTime() {
        return pcbArrivalTime;
    }

    public void setPcbArrivalTime(int pcbArrivalTime) {
        this.pcbArrivalTime = pcbArrivalTime;
    }

    public int getPcbServiceTime() {
        return pcbServiceTime;
    }

    public void setPcbServiceTime(int pcbServiceTime) {
        this.pcbServiceTime = pcbServiceTime;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public char getPcbState() {
        return pcbState;
    }

    public void setPcbState(char pcbState) {
        this.pcbState = pcbState;
    }

    public PCB getNextPCB() {
        return nextPCB;
    }

    public void setNextPCB(PCB nextPCB) {
        this.nextPCB = nextPCB;
    }

    public int getPcbStartTime() {
        return pcbStartTime;
    }

    public void setPcbStartTime(int pcbStartTime) {
        this.pcbStartTime = pcbStartTime;
    }

    public int getPcbOverTime() {
        return pcbOverTime;
    }

    public void setPcbOverTime(int pcbOverTime) {
        this.pcbOverTime = pcbOverTime;
    }

    public int getPcbRoundTime() {
        return pcbRoundTime;
    }

    public void setPcbRoundTime(int pcbRoundTime) {
        this.pcbRoundTime = pcbRoundTime;
    }

    public double getPcbAvgRoundTime() {
        return pcbAvgRoundTime;
    }

    public void setPcbAvgRoundTime(double pcbAvgRoundTime) {
        this.pcbAvgRoundTime = pcbAvgRoundTime;
    }

    @Override
    public String toString() {
        return "PCB{" +
                "pcbName='" + pcbName + '\'' +
                ", pcbArrivalTime=" + pcbArrivalTime +
                ", pcbServiceTime=" + pcbServiceTime +
                ", firstNum=" + firstNum +
                ", pcbState=" + pcbState +
                ", nextPCB=" + nextPCB +
                ", pcbStartTime=" + pcbStartTime +
                ", pcbOverTime=" + pcbOverTime +
                ", pcbRoundTime=" + pcbRoundTime +
                ", pcbAvgRoundTime=" + pcbAvgRoundTime +
                '}';
    }

}
