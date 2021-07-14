package com.data.array;

import org.omg.CORBA.Object;

public class GenericArray<T> {
    private T[]data;
    private int size;
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }
    }
    public GenericArray(int capacity) {
        data= (T[]) new Object[capacity];
        this.size=0;
    }
    private void checkIndexForAdd(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed! Require index >=0 and index <= size.");
        }
    }

    public GenericArray() {
        this(10);
    }
    public int getCapacity(){
        return data.length;
    }
    public int count(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public void set(int index,T e){
        checkIndex(index);
        data[index]=e;
    }
    public T get(int index){
        checkIndex(index);
        return data[index];
    }
    public boolean contains(T e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return true;
        }
        return false;
    }
    public int find(T e){
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) return i;
        }
        return -1;
    }
    public void add(int index,T e){
        checkIndexForAdd(index);
        if (size==data.length) {
            resize(2*data.length);
        }
        for (int i = size-1; i >index; i--) {//i=size时越界
            data[i+1]=data[i];
        }
        data[index]=e;
        size++;
    }
    // 向数组头插入元素
    public void addFirst(T e) {
        add(0, e);
    }

    // 向数组尾插入元素
    public void addLast(T e) {
        add(size, e);
    }
    private void resize(int capacity) {
        T[] newData= (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i]=data[i];
        }
        data=newData;
    }

    public T remove(int index){
        checkIndex(index);
        T ret = data[index];
//        for (int i = index; i <size; i++) {
//            data[i]=data[i+1];//在size=cap时，i+1最大=cap会越界
//        }
        for (int i = index+1; i <size; i++) {
            data[i-1]=data[i];//在size=cap时，i最大=cap-1不越界
        }
        size--;
        data[size]=null;//向前挪位，index被覆盖，多出的尾部要置空

        //缩容：在只占用1/4容量，且容量可以分割时
        if (size==data.length/4&&data.length/2!=0)resize(data.length/2);
        return ret;
    }
    public T removeFirst() {
        return remove(0);
    }

    // 删除末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除指定元素
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, data.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
