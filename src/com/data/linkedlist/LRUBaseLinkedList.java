package com.data.linkedlist;

/**
 * 单链表LRU
 */
public class LRUBaseLinkedList<T> {
    public class SNode<T> {
        private T element;
        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    private static final Integer DEFAULT_CAPACITY = 10;
    /**
     * 头结点
     */
    private SNode<T> headNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * 单链表插入，要找到前驱节点，改变指针指向
     */
    public SNode findPreNode(T data) {
        SNode head = headNode;
        while (head.getNext() != null) {
            if (data.equals(head.getNext().getElement())) return head;
            head = head.getNext();
        }
        return null;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);
        if (preNode != null) {/*在链表内*/
            /*删除原节点*/
            SNode next = preNode.getNext();
            preNode.setNext(next.getNext());
            next=null;
            --length;
            /*插到表头*/
            SNode newNode = new SNode(data, headNode.getNext());
            headNode.setNext(newNode);
            ++length;
        } else {/*不在链表内*/
            if (length>=this.capacity){/*满了，先从末尾删一个*/
                SNode ptr = headNode;
                if (ptr.getNext() == null) return;// 空链表直接返回
                // 删除末尾节点，要改变其前驱节点即倒第二个节点的指针指向
                while (ptr.getNext().getNext() != null) ptr = ptr.getNext();
                SNode tmp = ptr.getNext();
                tmp = null;

                ptr.setNext(null);
                length--;
            }
            /*插到表头*/
            SNode newNode = new SNode(data, headNode.getNext());
            headNode.setNext(newNode);
        }
    }
}
