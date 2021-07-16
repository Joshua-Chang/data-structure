package com.data.sort;

public class BucketSort {
    public static void bucketSort(int[] arr, int bucketSize) {
        if (arr.length < 2) return;
        int minValue = arr[0];
        int maxValue = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) minValue = arr[i];
            else if (arr[i] > maxValue) maxValue = arr[i];
        }
        /*根据最大最小值求出数据的总范围跨度，总范围/桶大小=桶数量*/
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] elementCountByBucket = new int[bucketCount];/*每个桶中实际的元素数量，即每个桶各自的的大小*/
        for (int i = 0; i < arr.length; i++) {
            int indexOfBucket = (arr[i] - minValue) / bucketSize;/*该存入第n个桶*/
            if (elementCountByBucket[indexOfBucket] == buckets[indexOfBucket].length/*不用bucketSize因为可能扩容过多次*/)/*实际count=容量时扩容*/
                expandCapacity(buckets, indexOfBucket);
            /*存入该桶内数组的末尾*/
            buckets[indexOfBucket][elementCountByBucket[indexOfBucket]++/*第n个桶里的元素数量做下标，即该桶的末尾*/] = arr[i];
        }
        /*对每个桶内做快排*/
        int k = 0;/*最终索引*/
        for (int i = 0; i < buckets.length; i++) {
            if (elementCountByBucket[i] == 0) continue;/*i号桶为空桶*/
            quickSortC(buckets[i], 0, elementCountByBucket[i] - 1);
            for (int j = 0; j < elementCountByBucket[i]/*小于i号桶内元素数量*/; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    private static void expandCapacity(int[][] buckets, int index) {
        int[] origin = buckets[index];
        int[] result = new int[origin.length * 2];
        for (int i = 0; i < origin.length; i++) result[i] = origin[i];
        buckets[index] = result;
    }

    private static void quickSortC(int[] arr, int p, int r) {
        if (p >= r) return;
        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];/*先取末尾元素的值做pivot，遍历其他元素来与其比较*/
        int i = p;/*分区点，pivot最终位置*/
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] <= pivot) {//比较：
                swap(arr, i, j);//交换：
                i++;//已排序区增1
            }
        }
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
