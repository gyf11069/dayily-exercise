package com.next_nextline.www;

import java.util.Scanner;
/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-01-24 15:12
 */
public class Test {
   /* public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("使用nextLine()方法，将空格作为间隔符。输入为：");
        while (sc.hasNext()) {
            System.out.print("输出为：");
            String n = sc.nextLine();
            System.out.print(n);
        }
    }*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("请输入字符串（next）：");
        String str = input.next();
        System.out.println(str);

        System.out.println("请输入字符串（nextLine）：");
        String str1 = input.nextLine();
        System.out.println(str1);
    }

}
