package com.data.sort;

public class CountingSort {
    /**
     * 简化一分一段表。假设只有 8 个考生，分数在 0 到 5 分之间。
     * <p>
     * 8人的成绩原序存入数组 `A = {2, 5, 3, 0, 2, 3, 0, 3}` 。
     * <p>
     * 统计考每个分数的人数，存入数组 `C = {2, 0, 2, 3, 0, 1}` 即每个分数一个桶。如考0分的2人、1分的0人
     * <p>
     * 对统计每个分数的人数，的数组，计数累加 `C = {2, 2, 4, 7, 7, 8}`	数组中的元素表示「小于等于该分数的元素的个数」。如小于等于5分的8人，小于等于4分的7人，小于等于3分的7人，
     * <p>
     * 从后到前，依次从保存分数的原序数组 A中取出元素，经过和计数数组C对照得到应存的位置，并放入新数组R的该位置。计数数组C内，值等于该元素的计数减一。
     * <p>
     * 比如，当从A中取出 3分这个元素时，对照数组 C 中分数小于等于 3 的考生有 7 个。就把3分这个元素放入到数组 R 的第7个位置(index=6)。小于等于 3 的元素取走一个，C中相应的计数减 1，`C = {2, 2, 4, 6, 7, 8}`
     *
     * @param arr
     * @param size
     */
    // 计数排序，a是数组，size是数组大小。假设数组中存储的都是非负整数。
    public static void countingSort(int[] arr, int size) {
        if (size <= 1) return;
        //假设arr是只有 8 个考生，分数在 0 到 5 分之间:2, 5, 3, 0, 2, 3, 0, 3的数组
        int max = arr[0];
        for (int i = 0; i < size; i++) if (arr[i] > max) max = arr[i];//找出最高分5
        // TODO: 2021/7/16 没有求最小值
        /*记录每个分数的数量的数组。每一个分数一个桶，因此计数排序是细粒度的桶排序*/
        int countByScoreArr[] = new int[max + 1];//初始化数组传size，比index大1 /*待优化：size应为最大-最小*/

        for (int i = 0; i < size; i++) {
            //int score = arr[i];把分数作为计数数组的索引，每有一个该分数的，计数加1
            //countArr[score]++;
            //简化后：
            countByScoreArr[arr[i]]++;//从里往外运算：以arr[i]的分数为计数数组的索引，每出现一次该分数，计数加1
        }

        /*转化成累加数组*/
        for (int i = 1; i < countByScoreArr.length; i++) {
            countByScoreArr[i] = countByScoreArr[i - 1] + countByScoreArr[i];
        }

        int[] result = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            int index = countByScoreArr[arr[i]] - 1;//该分数的考生数量，-1得到插入位置（size转index）
            result[index] = arr[i];
            countByScoreArr[arr[i]]--;//该分数的考生取走插入了，计数-1
        }
        for (int i = 0; i < size; i++) {
            arr[i] = result[i];
        }
    }
}
