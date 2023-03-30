package com.Collection.www;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gyf
 * @date 2022-11-09 15:21
 */
public class ListTest {
    public static void main(String[] args) {
        List L1 = new ArrayList();
        L1.add(1);
        L1.add("qwe");
        L1.add(2);
        List L2 = new ArrayList();
        L2.add(3);
        L2.add("qwasae");
        L2.add(4);
        L2.addAll(L1);
        System.out.println(L2);
        System.out.println("================================");



    }

}
