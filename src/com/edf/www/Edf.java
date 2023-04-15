package com.edf.www;

import java.util.Scanner;
/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-11-05 20:15
 *
 * 时限调度算法
 */
public class Edf {
    public static void main(String[] args) {

        int A=0,B=0;                           //标记进程A,进程B的到达时间
        int cycA,cycB,serveA,serveB;         //进程的周期时间和服务时间
        float m;
        int i,j,a=0,b=0,ka=0,kb=0;                 //ka,kb为开关，i，j,a,b为进程下标
        int numa=0,numb=0;//服务累计时间
        int T;

        Scanner sc = new Scanner(System.in);

        System.out.println("EDF算法之抢占式调度算法");
        System.out.println("输入进程A的周期时间：       服务时间：");
        cycA=sc.nextInt();
        serveA=sc.nextInt();
        System.out.println("输入进程B的周期时间：      服务时间：");
        cycB=sc.nextInt();
        serveB=sc.nextInt();
        m=(float)serveA/cycA+(float)serveB/cycB;
        for(T=0;T<=100;T++)
        {
            if(m-1>1e-6)
            {
                System.out.println("超出CPU的处理能力!");
                return;
            }
            if(numa==serveA)                                        //进程A完成
            {
                numa=serveA+1;
                System.out.println("当T="+T+"时，");
                System.out.println("进程A"+a+"结束");
                if(numb<serveB)
                {
                    System.out.println("----->调用进程B"+b);
                    kb=1;
                }
                ka=0;
            }
            if(numb==serveB)
            {
                numb=serveB+1;
                System.out.println("当T="+T+"时,");
                System.out.println("进程B"+b+"结束");
                if(numa<serveA)
                {
                    System.out.println("----->调用进程A"+a);
                    ka=1;
                }
                kb=0;
            }
            if(T%cycA==0 && T%cycB==0)
            {
                A=B=T;
                j=++a;
                i=++b;
                System.out.println("当T="+T+"时，进程A"+j+"和进程B"+i+"同时产生，此时：");
                if(cycA<=cycB)
                {
                    System.out.println("调用进程A"+j+"，阻塞进程B"+i);
                    ka=1;
                    kb=0;
                }
                else
                {
                    System.out.println("调用进程B"+i+"，阻塞进程A"+j);
                    ka=0;
                    kb=1;
                }
                numa=numb=0;
            }
            if(T%cycA==0&&T%cycB!=0)
            {
                A=T;
                System.out.println("当T="+T+"时");
                System.out.println("进程A"+(++a)+"产生  ");   //不可能与进程A竞争处理器
                numa=0;
                if(numb<serveB)                //进程B没有完成
                    if(B+cycB>A+cycA)         //若进程B最早截止时间大于进程A的
                    {
                        System.out.println("进程A"+a+"执行。");
                        ka=1;
                        kb=0;
                    }
                    else                     //若进程B最早截止时间小于等于进程A的
                        System.out.println("进程B"+b+"继续执行。");
                else                                   //进程B完成
                {
                    System.out.println("进程A"+a+"执行。");
                    ka=1;
                }
            }
            if(T%cycA!=0&&T%cycB==0)
            {
                B=T;
                System.out.println("当T="+T+"时");
                System.out.println("进程B"+(++b)+"产生，");            //不可能与进程B竞争处理器
                numb=0;
                if(numa<serveA)                         //进程A没有完成
                    if(B+cycB>=A+cycA)                //进程A的最早截止时间不小于B
                        System.out.println("进程A"+a+"继续执行。");
                    else
                    {
                        System.out.println("进程B"+b+"执行。");
                        kb=1;
                        ka=0;
                    }
                else                                //进程A完成
                {
                    System.out.println("进程B"+b+"执行。");
                    kb=1;
                }
            }
            if(ka==1) {
                numa++;}
            if(kb==1) {
                numb++;}
        }
    }

}


