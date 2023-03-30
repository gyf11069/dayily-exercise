package com.youxianji;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-22 19:48
 */
public class DynamicJobFirstUtil {
   /* private  static SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss");*/
    //进程初始化
  /*  @SuppressWarnings("resource")*/
    public static PCB init(PCB pcb) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入进程名称：如（进程1）");
        pcb.setPcbName(scanner.nextLine());
        System.out.println("请输入进程到达时间：如（1）");
        pcb.setPcbArrivalTime(scanner.nextInt());
        System.out.println("请输入进程服务时间：如（3）");
        pcb.setPcbServiceTime(scanner.nextInt());
        System.out.println("请输入进程初始优先权：如（1）");
        pcb.setFirstNum(scanner.nextInt());
        pcb.setPcbState('w');
        return pcb;
    }

    //按照服务时间去排序
    public static void sortByServerTime(List<PCB> pcbs){
        //使用Collections工具类中的自定义条件排序方法
        Collections.sort(pcbs , new Comparator<PCB>() {
            @Override
            public int compare(PCB pcb1, PCB pcb2) {
                return  (pcb1.getPcbServiceTime() - pcb2.getPcbServiceTime());
            }
        });
    }
    //按照到达时间去排序
    public static void sortByArrivalTime(List<PCB> pcbs){
        //使用Collections工具类中的自定义条件排序方法
        Collections.sort(pcbs , new Comparator<PCB>() {
            @Override
            public int compare(PCB pcb1, PCB pcb2) {
                return  (pcb1.getPcbArrivalTime() - pcb2.getPcbArrivalTime());
            }
        });
    }
    //先按照优先度排序，优先度相同的按照到达时间去排序
    public static void sortByStateAndArrivalTime(List<PCB> pcbs){
        //使用Collections工具类中的自定义条件排序方法
        Collections.sort(pcbs , new Comparator<PCB>() {
            @Override
            public int compare(PCB pcb1, PCB pcb2) {
                int cr = 0;
                //先按优先级排降序
                int a = pcb1.getFirstNum() - pcb2.getFirstNum();
                if (a != 0) {
                    cr = (a < 0) ? 3 : -1;
                } else {
                    //再按到达时间排升序
                    a =  pcb1.getPcbArrivalTime() - pcb2.getPcbArrivalTime();
                    if (a != 0) {
                        cr = (a > 0) ? 2 : -2;
                    }
                }
                return cr;
            }
        });
    }

    /*
    该方法主要作用在于找出一条合理的，最高优先权先执行的一条路径。
    方法内容为：设置三个队列，第一个，用户输入的进程队列，
    第二个，中间暂存的进程队列，用于暂存从输入队列中挑选出的进程。
    第三个，最后需要执行的进程队列。
    */
    //调度算法函数，最终将需要按顺序执行的进程放入execpcbs中
    //pcbs，输入队列，
    //execpcbs，执行队列
    public static List<PCB> dispatchpcb(List<PCB> pcbs,List<PCB> execpcbs){
        //中间队列，暂存从输入队列中挑选出的进程
        List<PCB> temppcbs = new ArrayList<>();
        //cpu无工作时，第一个到达的进程先服务，结束时间前到达的进程在执行
        DynamicJobFirstUtil.sortByArrivalTime(pcbs);
        execpcbs.add(pcbs.get(0));
        pcbs.remove(pcbs.get(0));
        //将输入队列中的进程一个一个的移动到执行队列中
        while (pcbs.size()>0) {
            //execpcbs队列的最后一个exexpcb的结束时间
            PCB exexpcb = execpcbs.get((execpcbs.size() - 1));
            int endTime = exexpcb.getPcbArrivalTime() + exexpcb.getPcbServiceTime();
            //使用迭代器，便于输入队列的删除，不会出错
            Iterator<PCB> iterator = pcbs.iterator();
            //迭代判断
            while (iterator.hasNext()){
                PCB pcb = iterator.next();
                //将执行队列中的结束时间大于输入队列的到达时间的所有进程移动到执行队列
                if (endTime >= pcb.getPcbArrivalTime()) {
                    temppcbs.add(pcb);
                    iterator.remove();
                }
            }
            //若遍历后，执行队列结束时间还没有进程到达，则按照到达时间排序得到第一个
            if (temppcbs==null){
                DynamicJobFirstUtil.sortByArrivalTime(pcbs);
                execpcbs.add(pcbs.get(0));
                pcbs.remove(pcbs.get(0));
            }
            //按照服务时间长短进行排序，便于下面移动到执行队列的进程的顺序不出错
            DynamicJobFirstUtil.sortByStateAndArrivalTime(temppcbs);
            execpcbs.addAll(temppcbs);
            temppcbs.clear();
        }
        for (PCB pcb : execpcbs){
            pcb.setPcbState('r');
        }
        return execpcbs;
    }

    //求出周转时间,平均周转时间等其他信息
    public static void turnRoundTime(List<PCB> pcbs){
        //第一个的到达时间
        int temp = pcbs.get(0).getPcbArrivalTime();
        for (PCB pcb : pcbs){
            //如果前一个进程的结束时间小于当前进程的到达时间
            if (temp < pcb.getPcbArrivalTime()){
                temp = pcb.getPcbArrivalTime();
            }
            //设置进程的开始时间.前一个的结束时间等于本次的开始时间
            pcb.setPcbStartTime(temp);
            //得到每个进程的服务时间
            int serviceTime = pcb.getPcbServiceTime();
            temp+=serviceTime;
            //结束时间=开始时间+服务时间
            pcb.setPcbOverTime(temp);
            //周转时间=结束时间-到达时间
            int turnRound = temp-pcb.getPcbArrivalTime();
            pcb.setPcbRoundTime(turnRound);
            //带权周转时间=周转时间/服务时间
            pcb.setPcbAvgRoundTime((1.0*turnRound)/serviceTime);
        }
    }

    //输出进程运行时间
    public static void showTime(List<PCB> pcbs){
        System.out.println("进程名称\t\t到达时间\t\t服务时间\t\t开始时间\t\t优先级\t\t状态\t\t结束时间\t\t周转时间\t\t带权周转时间");
        double turnRound = 0.0;
        double avgTurnRound = 0.0;
        for (PCB pcb : pcbs){
            System.out.println(pcb.getPcbName()+"\t\t\t"+pcb.getPcbArrivalTime()+"\t\t\t"+pcb.getPcbServiceTime()
                    +"\t\t\t"+pcb.getPcbStartTime()+"\t\t\t"+pcb.getFirstNum()+"\t\t\t"+pcb.getPcbState()+"\t\t\t"+pcb.getPcbOverTime()+"\t\t\t"+pcb.getPcbRoundTime()
                    +"\t\t\t"+pcb.getPcbAvgRoundTime());
            turnRound+=pcb.getPcbRoundTime();
            avgTurnRound+=pcb.getPcbAvgRoundTime();
        }
        System.out.println(pcbs.size()+"个进程的平均周转时间为："+String.format("%g %n",(1.0*turnRound)/pcbs.size()));
        System.out.println(pcbs.size()+"个进程的平均带权周转时间为："+String.format("%g %n",(1.0*avgTurnRound)/pcbs.size()));
    }

}
