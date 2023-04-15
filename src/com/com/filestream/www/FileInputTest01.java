package com.com.filestream.www;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-15 17:20
 */
public class FileInputTest01 {

    public static void main(String[] args){
        //创建文件字节流输入对象
        FileInputStream files = null;
        try{
            /*
            方式一：
             files = new  FileInputStream("E:/java课堂笔记/file");
            * */
            //方式二：文件绝对路径是“E:\java课堂笔记\file”,"\\"其中一个是转义字符表示“\”
            files=new FileInputStream("E:\\java课堂笔记\\file"); //存储内容：abcdef

           /*
               读数据
            int readeData=files.read(); //返回值：读取到的字节本身,读到文件末尾没有数据就返回-1
            System.out.println(readeData);//结果：97 （字符a的ASCII码）*/

            /*while(true){//循环读出
                int readeData=files.read();
                if(readeData == -1){
                    break;
                }
                System.out.println(readeData);
            }*/
            //改造循环
            int readeData;
            while((readeData=files.read()) != -1){
                System.out.println(readeData);
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //确保一定关闭流
            if(files != null){
                //如果files为空则不用关闭流
                try {
                    files.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
