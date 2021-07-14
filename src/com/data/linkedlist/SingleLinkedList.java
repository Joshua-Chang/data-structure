package com.data.linkedlist;

/**
 * 对于难以读懂的循环语句如 {@link SingleLinkedList#inverseLinkList(Node)}
 * 可以把循环内的语句，往下错位，找到真正的起始语句。
 */
public class SingleLinkedList {
    public static class Node {
        private char data;
        private Node next;

        public Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) p = p.next;
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    public void insertToHead(char value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            /*注意顺序，反过来链表会断裂*/
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertTail(char value) {
        Node newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while (q.next != null) q = q.next;
            newNode.next = q.next;
//            newNode.next= null;//这时q.next=null
            q.next = newNode;
        }
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) return;
        /*注意顺序，反过来链表会断裂*/
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertAfter(Node p, char value) {
        //        if (p == null) return;
        //        Node newNode = new Node(value, p.next);
        //        p.next=newNode;
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) q = q.next;/*找到p的前驱节点q*/
        if (q == null) return;/*遍历完没有next是p的前驱节点*/

//        insertAfter(q,newNode);
        newNode.next = q.next;
        q.next = newNode;
    }

    public void insertBefore(Node p, char value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void add(Node p) {
        insertAfter(head, p);
    }

    public void add(char value) {
        add(new Node(value, null));
    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) return;
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) q = q.next;/*找到p的前驱节点q*/
        if (q == null) return;
        q.next = q.next.next;/*q.next=p*/
    }

    public void deleteByValue(char value) {
        if (head == null) return;
        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;/*q成了待删除的节点的前驱节点*/
            p = p.next;
        }
        if (p == null) return;/*没找到*/
        /*找到了*/
        if (q == null) {/*没发生遍历，链表中仅有head一个，且head.value=value*/
            head = head.next;
        } else {
            q.next = q.next.next;
        }

        // 可重复删除指定value的代码
//        if (head != null && head.data == value) {
//            head = head.next;
//        }
//
//        Node pNode = head;
//        while (pNode != null) {
//            if (pNode.next.data == value) {
//                pNode.next = pNode.next.next;
//                continue;
//            }
//            pNode = pNode.next;
//        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        char[] data = {'a', 'b', 'c', 'd', 'e'};
        for (int i = 0; i < data.length; i++) {
            linkedList.insertTail(data[i]);
        }
        linkedList.printAll();
    }

    /*“回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。*/
    public boolean palindrome() {
        if (head == null) {
            return false;
        } else {
            Node p = head;
            Node q = head;
            while (q.next != null && q.next.next != null) {/*通过自增快慢寻找中间点*/
                p = p.next;
                q = q.next.next;
                //假设有10个节点，下标0-9，在遍历过程中
                //q = 0 2 4 6 8
                //p = 0 1 2 3 4 偶数时，遍历后p为中间的俩节点靠左的一个

                //假设有11个节点，下标0-10，在遍历过程中
                //q = 0 2 4 6 8 10
                //p = 0 1 2 3 4 5  奇数时，遍历后p为中间节点
            }
            Node leftLinkedList = null;
            Node rightLinkedList = null;
            /*将左半链表反转，和右半链表 一起遍历比较。即从中心到两边比较*/
            if (q.next == null) {/*奇数个节点*/
                rightLinkedList = p.next;
                leftLinkedList = inverseLinkList(p).next;
            } else {/*偶数个节点*/
                rightLinkedList = p.next;
                leftLinkedList = inverseLinkList(p);
            }
            return TFResult(leftLinkedList, rightLinkedList);
        }
    }

    /*无头节点的链表反转*/
    public Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        Node next = null;
        /*
         * 将指针指向反转既可
         * 原序：0->1->2->3->4
         * 现序：0<-1<-2<-3<-4
         * 在单向链表里没有前指针，
         * */
        while (r != p) {//四次循环各自做如下的指针调转：[0<-1]、[1<-2]、[2<-3]、[3<-4]
            next = r.next;

            r.next = pre;//3、r->pre:[i+1]->[i] 索引自增后指向的元素，next指向索引自增前的元素。即指向调转
            pre = r;//1、原r自增前存为pre /*错位后真正的起始语句*/

            r = next;//2、r递增
        }


        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;
    }

    /*判断是否是回文串，遍历比较左右两个链表的索引，查看元素是否完全相同*/
    public boolean TFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        boolean flag = true;
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;/*判断下个位置*/
            } else {
                flag = false;
                break;
            }
        }
        return flag;
        /*if (l==null&&r==null) {
            return true;
        }else {
            return false;
        }*/
    }
}
