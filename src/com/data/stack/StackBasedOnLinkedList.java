package com.data.stack;

/**
 * 自顶向下next
 */
public class StackBasedOnLinkedList {
    private static class Node {
        char data;
        Node next;

        Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node top = null;

    public void push(char value) {
        Node newNode = new Node(value, null);
        if (top==null){
            top=newNode;
        }else {
            /*顺序不能反了*/
            newNode.next=top;
            top=newNode;
        }
    }

    public char pop() {
        if (top==null) return '?';
        char data = top.data;
        top=top.next;
        return data;
    }
}
