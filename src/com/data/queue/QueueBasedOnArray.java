package com.data.queue;

public class QueueBasedOnArray {
    char[] items;
    int capacity;
    int head;
    int tail;

    public QueueBasedOnArray(int capacity) {
        this.capacity = capacity;
        this.items = new char[capacity];
    }

    public void enqueue(char data) {
        /*经过不停地进行入队、出队操作，head 和 tail 都会持续往后移动
         * 当 tail 移动到最右边，即使数组中还有空闲空间，也无法继续往队列中添加数据了。要数据搬移*/
        if (tail == capacity) {
            if (head==0)return;//真的满了
            for (int i = head; i < tail; i++) {
                items[i-head]=items[i];
            }
            //更新
            tail-=head;//tail-head就是真实长度
            head=0;
        }
        items[tail++] = data;/*插入后加1*/
    }

    public char dequeue() {
        if (head == tail) return '?';
        return items[head++];/*删除后，head向后移动一位*/
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print("[" + items[i] + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueBasedOnArray queue = new QueueBasedOnArray(5);
        queue.enqueue('a');
        queue.enqueue('b');
        queue.enqueue('c');
        queue.enqueue('d');
        queue.printAll();
        queue.dequeue();
        queue.printAll();
    }
}
