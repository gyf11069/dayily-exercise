package com.MFQ;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2020-10-24 10:01
 */
public class Node<T> {
    public Process data; // 数据域 存储数据元素
    public Node<Process> next; // 地址域，引用后继节点

    public Node(Process data, Node<Process> next) {
        super();
        this.data = data;
        this.next = next;
    }

    public Node() {
        super();
    }

    /*
     * 调用了Process的toString()方法
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.data.toString();
    }
}
