package com.data.tree;

public class BinarySearchTree {
    static private Node tree;

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "[" + data + "]";
        }
    }

    /**
     * 要查找的数据依次从根节点开始比较，小于节点就去左子树，大于节点就去右子树
     *
     * @param data 查找值为data的node
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            System.out.println(p);
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;/*允许重复元素时，这里不返回，而是用数组记录下来*/
        }
        return null;
    }

    /**
     * 要插入的数据依次从根节点开始比较，小于节点就去左子树，大于节点就去右子树，
     * 直到要插入数据比节点大，同时节点右子节点为空，
     * 或要插入的数据比节点小，同时节点左子节点为空，
     * 插入到该空节点。
     *
     * @param data 要插入的值
     * @return
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p != null) {
            System.out.println(p);
            if (p.data < /*<=*/data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else/* (p.data > data)*/ {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除值为data的节点，值为data的节点有三种情况
     * 1、该节点没有子节点：把父节点对该节点的指向删除，并置空该节点
     * 2、该节点只有一个子节点：父节点不在指向该节点，反而指向该节点唯一的子节点，并置空该节点
     * 3、该节点有左右两个子节点：在其右子节点中找最小的节点（大于它的最小值）来替换该节点（要同时调整该节点的父节点指向，和最小节点的父节点指向）
     */
    public void delete(int data) {
    }

    /**
     * 该节点下的最小节点
     */
    public Node findMin(Node node) {
        if (node == null) return null;
        if (node.left != null)
            node = node.left;
        return node;
    }

    public static void main(String[] args) {
        /*初始化*/
        tree = new Node(33);
        tree.left = new Node(17,
                new Node(13, null, new Node(16)),
                new Node(18, null, new Node(25, new Node(19), new Node(27)))
        );
        tree.right = new Node(50, new Node(34), new Node(58, new Node(51), new Node(66)));
        BinarySearchTree tree = new BinarySearchTree();
//        tree.find(19);
        tree.insert(55);
    }
}
