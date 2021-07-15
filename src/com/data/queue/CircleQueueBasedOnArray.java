package com.data.queue;

public class CircleQueueBasedOnArray {
    char[] items;
    int capacity;
    int head;
    int tail;

    public CircleQueueBasedOnArray(int capacity) {
        /*tail 指向的位置实际上是没有存储数据的。循环队列会浪费一个数组的存储空间*/
        this.capacity = capacity;
        this.items = new char[capacity+1];/*tail会占据一个位置，+1让容量在数量上和传入的值一样*/
    }

    public void enqueue(char data) {
        /*经过不停地进行入队、出队操作，head 和 tail 都会持续往后移动
         *移到最后即使有空间也无法插入，可以移动数据。也可以循环队列*/
        if ((tail+1) %capacity== head) return;/*循环队列满了*/

        items[tail] = data;
        /*相对位置，往后移动1位*/
        tail=(tail+1)%capacity;
    }

    public char dequeue() {
        if (head == tail) return '?';
        char result = items[head];
        /*删除后，head相对位置向后移动一位*/
        head=(head+1)%capacity;
        return result;
    }

    public void printAll() {
        int i = head;
        while (i%capacity!=tail){
            System.out.print("[" + items[i] + "] ");
            i++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircleQueueBasedOnArray queue = new CircleQueueBasedOnArray(5);
        queue.enqueue('a');
        queue.enqueue('b');
        queue.enqueue('c');
        queue.enqueue('d');
        queue.printAll();
        queue.dequeue();
        queue.printAll();
    }
}
