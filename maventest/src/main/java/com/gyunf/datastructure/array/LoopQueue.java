package com.gyunf.datastructure.array;

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        //会有一个元素的浪费
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        //会有一个元素的浪费
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //判断队列是否满
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("对列为空无法删除元素");
        }
        E datum = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        //出队列后缩容
        if ((size == getCapacity() / 4 ) && (getCapacity() / 2 != 0))
            resize(getCapacity() / 2);

        return datum;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("队列为空");
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        //将旧的数组的元素放到新数组中并且新数组是从0开始的
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue: size = %d,capacity = %d\n", size, getCapacity());
        res.append("front [");
        for (int i = front; i != tail; i = (i+1) % data.length) {
            res.append(data[i]);
            if ((i+1) % data.length != tail)
                res.append("，");
        }
        res.append("] tail");
        return res.toString();
    }
}
