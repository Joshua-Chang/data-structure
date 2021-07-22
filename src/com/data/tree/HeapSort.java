package com.data.tree;

import java.util.Arrays;

/**
 * 堆是完全二叉树
 * 对于完全二叉树来说，每增加一层，增加2次方个节点。因此设总节点为n：
 * 用数组从下标1开始存储完全二叉树(堆)时，下标从n/2+1至n的节点都是最后一层的叶子节点。不能作为顶点从上而下堆化
 */
public class HeapSort {

    /**
     * 堆化
     *
     * @param arr
     * @param n   终点位置。对数组中i至n下标的元素堆化。即2n-1（2n+1 1开始时）
     * @param i   对数组中下标为i的元素，作为从上往下堆化时插入的新顶点，往下比较
     */
    private static void heapify(int[] arr, int n, int i) {
        while (true) {
            int maxPos = i;
            //依次和左右子节点比较
            if (2 * i + 1 <= n && arr[i] < arr[2 * i + 1])
                maxPos = 2 * i + 1;
            if (2 * i + 2 <= n && arr[maxPos] < arr[2 * i + 2])
                maxPos = 2 * i + 2;
            //得出当前节点、左、右子节点中最大的节点 所在的位置
            if (maxPos == i) break;//i是较大的。本身就满足堆特性
            swap(arr, i, maxPos);//i的子节点是较大的。交换i与子节点位置的数据
            i = maxPos;//i换到原来子节点的位置，继续往下次比较堆化
        }
    }

    /**
     * 建堆
     * 堆在数组中起始位置为0的写法
     *
     * @param arr
     */
    private static void buildHeap(int[] arr) {
        for (int i = (arr.length / 2 - 1); i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(int[] arr) {
        if (arr.length <= 1) return;
        buildHeap(arr);

        int k = arr.length - 1;//尾部下标
        while (k > 0) {
            swap(arr, 0, k);//把末尾元素和堆顶元素交换，从上往下堆化
            heapify(arr, k--, 0);//先自减，即从顶点到k-1再做堆化，以此类推
        }
    }

    /**
     * topK 维持小顶堆 bottomK维持大顶堆
     */
    public static int[] topK(int[] arr, int k) {
        /*1、取k个元素*/
        int[] topK = Arrays.copyOf(arr, k);
        /*2、对这k个元素减小顶堆*/
        for (int i = 0; i < k; i++) {
            heapifyMin(topK,i+1,0);
        }
        /*3、比较剩余元素*/
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > topK[0]) {
                topK[0] = arr[i];
                heapifyMin(topK,k-1,0);
            }
        }
        return topK;
    }

    /**
     * 堆化：小顶堆化
     * @param arr
     * @param n
     * @param i
     */
    private static void heapifyMin(int[] arr, int n, int i) {
        while (true) {
            int minPos = i;
            //依次和左右子节点比较
            if (2 * i + 1 <= n && arr[i] > arr[2 * i + 1])
                minPos = 2 * i + 1;
            if (2 * i + 2 <= n && arr[minPos] > arr[2 * i + 2])
                minPos = 2 * i + 2;
            //得出当前节点、左、右子节点中最小的节点 所在的位置
            if (minPos == i) break;//i是较小的。本身就满足堆特性
            swap(arr, i, minPos);//i的子节点是较大的。交换i与子节点位置的数据
            i = minPos;//i换到原来子节点的位置，继续往下次比较堆化
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{7, 5, 19, 8, 4, 1, 20, 13, 16};
//        sort(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }

        int[] topK = topK(arr, 3);
        for (int i = 0; i < topK.length; i++) {
            System.out.println(topK[i]);
        }


    }


}
