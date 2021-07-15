package com.data.linkedlist;

import java.util.List;

/**
 * 1) 单链表反转
 * 2) 链表中环的检测
 * 3) 两个有序的链表合并
 * 4) 删除链表倒数第n个结点
 * 5) 求链表的中间结点
 */
public class LinkedListAlgo {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    /*单链表反转*/
    public static Node reverse(Node list) {
        Node curr = list, pre = null;
        while (curr != null) {/*把current的pre和next调换，即指针指向反转*/
            // 0->1->2
            // 0<-1<-2
            Node next = curr.next;
            curr.next = pre;//3、把next设为pre
            pre = curr;//1
            curr = next;//2
        }
        return pre;/*最后一次循环时，pre为[0]，curr最终为null*/
    }

    /*检测环*/
    public static boolean checkCircle(Node list) {
        if (list == null) return false;
        Node fast = list.next;
        Node slow = list;
        while (fast != null && fast.next != null) {
            //0 1 2 3 4 5 6->0->1...
            //0 1
            //  1   3
            //    2     5
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;//一旦存在环，fast在转完第1圈后，一定会在接下来的几圈，从后方追上slow
        }
        return false;
    }

    /*去除倒数第k个*/
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            ++i;
        }
        /*现在fast是正数第k个*/
        if (fast == null) return list;/*正数第k个就越界了，说明链表内不够k个*/
        Node slow = list;
        Node pre = null;//slow的前驱节点
        while (fast.next != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if (pre == null) {//上个循环没发生。即fast是最后一个，没有next了。倒数第k个即下标为0的元素
            list = list.next;//去掉下标为0的元素
        } else {//fast是最后一个元素，slow是倒数第k个。改变slow前驱节点的指针指向既可
            pre.next = pre.next.next;
        }
        return list;
    }

    /*求中间节点*/
    public static Node findMiddleNode(Node list) {
        if (list == null) return null;

        Node fast = list;
        Node slow = list;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;/*当奇数个时slow是中间节点。当偶数个时slow是中间的两个靠前的一个*/
    }


    // 有序链表合并 Leetcode 21
//Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode soldier = new ListNode(0);/*利用哨兵*/
        ListNode p = soldier;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;
        return soldier.next;
    }
}
