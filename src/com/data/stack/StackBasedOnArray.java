package com.data.stack;

public class StackBasedOnArray {
    char[] items;
    int count;
    int capacity;

    public StackBasedOnArray(int capacity) {
        this.capacity = capacity;
        this.items=new char[capacity];
        this.count=0;
    }
    public void push(char data){
        if (count==capacity) return ;
        items[count++]=data;/*插入后加1*/
    }
    public char pop(){
        if (count==0)return '?';
        return items[--count];/*先减1防止越界，再返回*/
    }
    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print("["+items[i] + "] ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        StackBasedOnArray stack = new StackBasedOnArray(5);
        stack.push('a');
        stack.push('b');
        stack.push('c');
        stack.push('d');
        stack.printAll();
        stack.pop();
        stack.printAll();
    }
}
