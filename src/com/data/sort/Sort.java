package com.data.sort;

public class Sort {
    /**
     * 一次子循环内的遍历交换，为一次冒泡。冒泡排序要多次冒泡，直到冒泡中不再有元素交换
     * 冒泡排序包含两个操作原子，比较和交换。每交换一次，有序度就加 1，逆序度减1。交换次数总是确定的，即为逆序度。
     */
    public static void bubbleSort(int[] arr, int size) {
        if (size <= 1) return;
        for (int i = 0; i < size; i++) {
            boolean flag = false;/*提前推出标识位：冒泡中不再有元素交换则提前退出*/
            /*冒泡*/
            for (int j = 0; j < size - i - 1; j++) {/*size - i - 1 每完成一次冒泡，数组末尾便有一个元素排序完成*/
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;/*本次冒泡中有元素交换*/
                }
            }
            if (!flag) break;
        }
    }

    /**
     * 每次会从未排序区间取一个算素，然后在已排序区间，挪位寻找位置插入
     * 初始已排序区间内有第一个元素，未排序区间是除第一个元素外的其他元素
     */
    public static void insertionSort(int[] arr, int size) {
        if (size <= 1) return;
        for (int i = 1; i < size; i++) {//初始时，数组的第一个元素，在已排序区间。未排序区间从第二个开始
            int injector = arr[i];/*i是未排序区间的第一个，j是i的上一个，即j是已排序区间的最后一个*/
            int j = i - 1;
            for (; j >= 0; j--) {//j从已排序区间的最后一个向第一个，依次遍历，
                if (arr[j] > injector) {/*比较：*/
                    arr[j + 1] = arr[j];/*交换：依次往后挪*/
                } else {//找到了插入位置 跳出循环
                    break;
                }
            }
            arr[j + 1] = injector;//插入：循环--了，要加回来才是真正插入位置
        }
    }

    /**
     * 每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾。
     * 初始已排序区间为空，未排序区间为全部元素
     */
    public static void selectionSort(int[] arr, int size) {
        if (size <= 1) return;
        for (int i = 0; i < size - 1; i++) {/*i是未排序区间的第一个，从0开始。*/
            int minIndex = i;/*i<size-1 i最大取倒数第二个，用来比较的j是i的下一个，才不会越界*/
            for (int j = i + 1; j < size; j++) if (arr[j] < arr[minIndex]) minIndex = j;/*比较：找到最小元素的索引*/
            /*交换*/
            int temp = arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
    }
}
