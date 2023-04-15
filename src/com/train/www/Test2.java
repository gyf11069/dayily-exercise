package com.train.www;

import java.lang.reflect.Field;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-07-04 10:49
 */
public class Test2 {
   /* public static void main(String[] args) {
        int[] a={1,2,3};
        System.out.println(a);
    }*/

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = cache.getDeclaredField("cache"); //2
        myCache.setAccessible(true);//3

        Integer[] newCache = (Integer[]) myCache.get(cache); //4
        newCache[132] = newCache[133]; //5

        int a = 2;
        int b = a + a;
        System.out.printf("%d + %d = %d", a, a, b); //
    }
}
