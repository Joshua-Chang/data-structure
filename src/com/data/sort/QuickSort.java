package com.data.sort;

public class QuickSort {
    public static void quickSort(int[] arr, int size) {
        quickSortInternally(arr, 0, size - 1);
    }

    private static void quickSortInternally(int[] arr, int start, int end) {
        if (start >= end) return;
        int pivot = partition(arr, start, end);
        quickSortInternally(arr, start, pivot);
        quickSortInternally(arr, pivot, end);
    }

    private static int partition(int[] arr, int start, int end) {
        /**
         * 游标 i 把 A[p…r-1] 分成两部分。
         * A[p…i-1] 的元素都是小于 pivot 的，暂且叫它“已处理区间”，初始为空
         * A[i…r-1] 是“未处理区间”，初始为整个区间
         * 每次都从未处理的区间 A[i…r-1] 中取一个元素 A[j]，
         * 与 pivot 对比，大于pivot则不必处理
         * 如果小于 pivot，则只需要将 A[i] 与 A[j] 交换，就能将其加入到已处理区间的尾部，也就是 A[i] 的位置
         */
        int pivot = arr[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {/*如果从未处理区间取出的j大于pivot，不操作。继续留在大于pivot的右侧区间*/
                if (i == j) {/*此处的判断只是省去i和j相等时，没必要的交换造成的性能损耗。实际上这两种情况i都要自增的*/
                    i++;
                } else {
                    int temp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        /*上面的循环后有++自增，i所在的位置就是pivot应该的位置，交换*/
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;
        return i;
    }
}
