package com.data.queue;

/**
 *
 */
public class QueueBasedOnLinkedList {
    private static class Node {
        char data;
        Node next;

        Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;
    private Node tail = null;

    public void enqueue(char value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next=newNode;
            tail=tail.next;
        }
    }

    public char dequeue() {
        if (head == null) return '?';
        char data = head.data;
        head = head.next;
        if (head==null) tail=null;
        return data;
    }
}
