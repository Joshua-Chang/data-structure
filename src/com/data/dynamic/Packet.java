package com.data.dynamic;

public class Packet {
    public int knapsack(int[] weight/*物品重量*/, int n/*个数*/, int w/*承重*/) {
        /*这个二维数组的范围足够表示放n个物品承重变化的状态*/
        boolean[][] states = new boolean[n][w + 1];/*w+1是size。即时n个都放进去只要在承重范围内就行。*/
        states[0][0] = true;/*n个物品都不放，承重0的特殊状态*/
        states[0][weight[0]] = true;

        /**
         * 状态：添加第i个物品，重量j出现的情况
         * 每遍历一个新物品i，都在之前  存在的状态的基础上：
         * 不添加i ：新增不添加i，重量也j不变化的状态
         * 添加i：遍历那些添加物品i也不超重状态，在其基础上新增总量
         */
        for (int i = 1; i < n/*size*/; i++) {/*状态变化转移。i=1是怕越界*/ // todo 0 3
            for (int j = 0; j <= w; j++) {/*不放第i个物品*/
                /* =true假如之前有状态记录。上个物品放了的基础上，这个物品不放。所有的状态不发生变化，和上一层一样*/
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];// todo 1 4
            }
            for (int j = 0; j <= w - weight[i]/*添加i也不超重*/; j++) {//放第i个物品
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;// todo 2 5
            }
        }
        for (int i = w; i >= 0; i--) {
            //从承重w递减，第一个可能性
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }

    /**
     * 状态：添加物品i后背包的重量情况
     */
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w - items[i]; j >= 0; j--) {//添加物品i时，不超重的各种重量情况
                //如果之前有过这种重量情况，则在其基础上添加物品i，不会超重。记录下来
                if (states[j] == true) states[j + items[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {/*在出现的重量情况中选最大的*/
            if (states[w] == true) return i;
        }
        return 0;
    }
}
