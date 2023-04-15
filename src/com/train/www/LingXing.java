package com.train.www;

public class LingXing {
    public static void main(String[] args) {
        for(int i=0;i<3;i++){
            for(int j=2;j>i;j--){
                System.out.print(" ");//空格打印
            }
            for(int j=0;j<=2*i;j++){
                System.out.print("*");//星号打印
            }
            System.out.println();//换行
        }
        //下半部分
        for(int i=0;i<2;i++){
            //空格打印
            for(int j=0;j<=i;j++){
                System.out.print(" ");
            }
            //*号打印
            for(int j=2;j>=2*i;j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
