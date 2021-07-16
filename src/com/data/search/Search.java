package com.data.search;

/**
 * 有序数据默认都在正序情况下排列，如1、2、3、4、5、
 * 倒序情况要自行调整
 */
public class Search {
    /**
     * 循环实现
     */
    public int bSearch(int[] arr, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + high / 2;
            if (arr[mid] == value) return mid;
            else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     */
    public int bSearchRecursion(int[] arr, int size, int value) {
        return bSearchInternally(arr, 0, size - 1, value);
    }

    public int bSearchInternally(int[] arr, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1/*位运算比四则原算低，要加括号*/);
        if (arr[mid] == value) return mid;
        else if (arr[mid] < value) return bSearchInternally(arr, mid + 1, high, value);
        else return bSearchInternally(arr, low, mid - 1, value);
    }

    /**
     * 顺序前提下
     * 寻找第一个等于该值的元素
     */
    public int bSearchFirstEq(int[] arr, int size, int value) {
        int low = arr[0];
        int high = arr[size - 1];
        while (low <= high) {
            int mid = low + ((low - high) >> 1);
            if (arr[mid] < value) low = mid + 1;
            else if (arr[mid] > value) high = mid - 1;
            else {
                if (mid == 0 || arr[mid - 1] != value) return mid;//mid没有上一个位置，或mid的上一个位置的值也不是value
                else high = mid - 1;/*mid前还有value，value就在[low,mid-1]间*/
            }
        }
        return -1;
    }

    /**
     * 顺序前提下
     * 寻找最后一个等于该值的元素
     */
    public int bSearchLastEq(int[] arr, int size, int value) {
        int low = arr[0];
        int high = arr[size - 1];
        while (low <= high) {
            int mid = low + ((low - high) >> 1);
            if (arr[mid] < value) low = mid + 1;
            else if (arr[mid] > value) high = mid - 1;
            else {
                if (mid == size - 1 || arr[mid + 1] != value) return mid;//mid没有上一个位置，或mid的上一个位置的值也不是value
                else low = mid + 1;/*mid后还有value，value就在[mid+1，high]间*/
            }
        }
        return -1;
    }

    /**
     * 顺序前提下
     * 寻找第一个大于或等于该值的元素
     */
    public int bSearchFirstGt_Eq(int[] arr, int size, int value) {
        int low = arr[0];
        int high = arr[size - 1];
        while (low <= high) {
            int mid = low + ((low - high) >> 1);
            if (arr[mid] < value) low = mid + 1;
            else {
                if (mid == 0 || arr[mid - 1] < value) return mid;//mid没有上一个位置，或mid的上一个位置的值不大于等于value
                else high = mid - 1;/*mid前还有元素大于等于value，value就在[low,mid-1]间*/
            }
        }
        return -1;
    }


    /**
     * 顺序前提下
     * 寻找最后一个小于等于该值的元素
     */
    public int bSearchLastLt_Eq(int[] arr, int size, int value) {
        int low = arr[0];
        int high = arr[size - 1];
        while (low <= high) {
            int mid = low + ((low - high) >> 1);
            if (arr[mid] > value) high = mid - 1;
            else {
                if (mid == size - 1 || arr[mid + 1] > value) return mid;//mid没有上一个位置，或mid的上一个位置的值也不小于等于value
                else low = mid + 1;/*mid后还有value，value就在[mid+1，high]间*/
            }
        }
        return -1;
    }
}
