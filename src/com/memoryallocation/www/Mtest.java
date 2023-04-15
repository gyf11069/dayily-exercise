package com.memoryallocation.www;

import java.util.Scanner;
/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-11-06 13:26
 */
public class Mtest {
    public static void main(String[] args) {
        Memory m = new Memory(640);
        Scanner sc = new Scanner(System.in);
        System.out.println();
        while(true){
            select();
            int k = sc.nextInt();
            if(k == 1){
                System.out.println("请输入请求分配的内存：");
                int size = sc.nextInt();
                m.allocation(size);
                m.showZones();
            }else if(k == 2){
                System.out.println("请输入要释放的分区的id:");
                int id = sc.nextInt();
                m.collection(id);
                m.showZones();
            }else{
                break;
            }

        }

    }

    public static void select(){
        System.out.println("==========模拟动态分区分配============");
        System.out.println("1、请求分配内存\n"
                          +"2、释放内存\n"
                          +"3、退出\n");
        System.out.println("=====================================");
    }
}
