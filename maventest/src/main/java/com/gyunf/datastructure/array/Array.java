package com.gyunf.datastructure.array;

public class Array<E> {
    private E[] data;
    //数组实际元素的个数,同时指向的是第一个没有元素的位置
    private int size;

    public Array(int capacity) {
        //因为没有 new E[]; 所以先创建 Object[]再强置转换成 E[]
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //获取数组容量大小
    public int getCapacity() {
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //向所有元素后添加一个新元素
    public void addLast(E element){
        add(size, element);
    }

    //向所有元素前添加一个新元素
    public void addFirst(E element){
        add(0, element);
    }

    //向指定位置添加一个新元素
    public void add(int index ,E element){
        /*  //添加了resize方法就可以不用判断数组的元素是否满了
        if (size == data.length)
            throw new IllegalArgumentException("添加元素失败，数组已经满了");
            */
        if (index <0 || index > size)
            throw new IllegalArgumentException("添加元素失败，索引越界");

        if (size == data.length)
            //每次扩容两倍
            resize(2 * data.length);

        //将index和index之后的元素都往后移动一个位置
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //将新元素添加到index位置
        data[index] = element;
        size++;
    }

    //获取index索引位置的元素
    //这样获取数组元素的好处是可以保证索引的合法性，防止用户访问没有使用的空间而造成数据安全问题
    public E get(int index) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("添加元素失败，索引越界");
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    //修改index索引位置的元素为element
    public void set(int index,E elment) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("修改元素失败，索引越界");
        data[index] = elment;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    //删除index索引的元素,返回删除的元素
    public E remove(int index) {
        if (index <0 || index > size)
            throw new IllegalArgumentException("删除元素失败，索引越界");
        E res = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i +1];
        }
        size --;
        /*此时data[size]中存在一个元素，下次使用该空间时就会重新赋值，所以不重置该位置的元素逻辑也是对的*/
        data[size] = null; //  loitering objects (闲逛的对象) != memory leak (内存泄漏)

        //当数组元素个数少于数组长度二分之一时，将数组缩容为原来的二分之一，为了解决复杂度震荡当元素少于四分之一时才需要将数组缩容
//        if (size == (data.length / 2))
        if (size == (data.length / 4) && (data.length / 2 != 0))
            resize(data.length / 2);
        return res;
    }

    // 从数组中删除元素e
    public void removeElement(E elment) {
        int index = find(elment);
        if (index != -1)
            remove(index);
    }

    public E removeLast() {
        return remove(size);
    }

    public E removeFirst() {
        return remove(0);
    }

    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d,capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append("，");
        }
        res.append("]");
        return res.toString();
    }
}
