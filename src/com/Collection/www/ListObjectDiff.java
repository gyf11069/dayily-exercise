package com.Collection.www;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author gyf
 * @date 2022-11-09 19:08
 */
public class ListObjectDiff {
    public static void main(String[] args) {

        /*List<People> listPeople = new ArrayList<People>();
        listPeople.add(new People("张三", 1, "13355556666", 23, "新员工"));
        listPeople.add(new People("张三", 2, "15522223333", 23, "老员工"));
        listPeople.add(new People("李四", 3, "13355556666", 23, "实习生"));
        listPeople.add(new People("提莫", 4, "13311112222", 23, "经理"));
        listPeople.add(new People("张三", 5, "13355556666", 23, "会计"));
        listPeople.add(new People("德玛", 6, "3344", 23, "开发"));
        listPeople.add(new People("卡特", 7, "13355556666", 23, "测试"));
        listPeople.add(new People("提莫", 8, "13355556666", 23, "美工"));
        listPeople.add(new People("提莫", 9, "13311112222", 23, "实施"));
        listPeople.add(new People("卡兹克", 10, "13356786666", 23, "售前"));
        listPeople.add(new People("亚索", 11, "13355556666", 23, "销售"));

        Set<People> setData = new HashSet<People>();
        setData.addAll(listPeople);

        System.out.println("list- size----" + listPeople.size());
        System.out.println("list-----" + listPeople.toString());

        System.out.println("set- size----" + setData.size());
        System.out.println("set-----" + setData.toString());

        for(People pp : setData) {
            System.out.println("p--" + pp.toString());
        }*/


        String s = "asdf";
        String[] strArr = s.split(",");
        for (int i = 0; i < strArr.length; i++) {
            System.out.println(strArr[i]);
        }


    }


     static class People {

        private String name;
        private int id;
        private String phoneNumber;
        private int age;
        private String introduce;

        public People(String name, int id, String phoneNumber, int age,
                      String introduce) {
            super();
            this.name = name;
            this.id = id;
            this.phoneNumber = phoneNumber;
            this.age = age;
            this.introduce = introduce;
        }

         @Override
         public String toString() {
             return "People{" +
                     "name='" + name + '\'' +
                     ", id=" + id +
                     ", phoneNumber='" + phoneNumber + '\'' +
                     ", age=" + age +
                     ", introduce='" + introduce + '\'' +
                     '}';
         }
     }
}
