package com.data.sort;

public class MergeSort {
    public static void mergeSort(int[] arr, int size) {
        mergeSortInternally(arr, 0, size - 1);
    }

    public static void mergeSortInternally(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = start + (start + end) / 2;
        mergeSortInternally(arr, start, mid);
        mergeSortInternally(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    /**
     * 申请一个大小和[start,end]同样地数组temp
     * 在[start,mid]和[mid+1,end]中分别放入游标i、j，都指向各自的首元素
     * 向合并两个有序数组那样。
     * 比较两区间各自游标指向的元素，取走较小的元素插入temp，然后该区间的游标后移。
     * 直到一个区间被取完，把剩下的区间全部插入到temp尾部。然后用temp代替原数组。
     */
    private static void merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int k = 0;/*temp随两个区间插入而自增的index*/
        int[] temp = new int[start - end + 1/*index转size*/];

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];/*i、j 插入temp后各自的索引自增。temp被插入索引k也自增*/
            } else {
                temp[k++] = arr[j++];
            }
        }
        /*哪个区间还有剩余*/
        int leaveHead = i;
        int leaveTail = mid;
        if (j <= end) {
            leaveHead = j;
            leaveTail = mid;
        }
        /*把剩余的插入*/
        while (leaveHead <= leaveTail) temp[k++] = arr[leaveHead++];
        /*从temp拷贝回去*/
        for (int l = 0; l < end - start; l++) arr[start + i] = temp[i];
    }

    /**
     * 利用哨兵合并
     */
    private static void mergeBySentry(int[] arr, int start, int mid, int end) {
        int[] leftArr = new int[mid - start + 2];/*+2是size，index是 +1，哨兵再占一个index最大mid-start*/
        /*[start,mid]：包括mid*/
        for (int i = 0; i <= mid - start; i++) leftArr[i] = arr[start + i];
        leftArr[mid - start + 1] = Integer.MAX_VALUE;/*尾部加入哨兵*/

        /*(mid,end]不包括mid*/
        int[] rightArr = new int[end - mid + 1];
        for (int i = 0; i < end - mid; i++) rightArr[i] = arr[mid + 1 + i];
        rightArr[end - mid] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        int k = start;
        while (k <= end) {
            if (leftArr[i] < rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    }
}
