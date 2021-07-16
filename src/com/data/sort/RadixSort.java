package com.data.sort;

/**
 * 基数排序
 */
public class RadixSort {
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) if (arr[i] > max) max = arr[i];

        for (int exp = 1; max / exp > 0; exp *= 10) {//经过循环，exp从个位变成十位、百位、千位...直到变成最大数的位数
            countingSort(arr, exp);
        }

    }

    /**
     * 计数排序-对数组按照"某个位数"进行排序
     *
     * @param arr
     * @param exp 指数
     */
    public static void countingSort(int[] arr, int exp) {
        if (arr.length <= 1) {
            return;
        }

        // 计算0-9每个元素的个数
        int[] c = new int[10];
        for (int i = 0; i < arr.length; i++) {
            c[(arr[i] / exp) % 10/*原数除个十百千万...，然后再与10取余，得到个位*/]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        // 临时数组r，存储排序之后的结果
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            r[c[(arr[i] / exp) % 10] - 1] = arr[i];
            c[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = r[i];
        }
    }
}
