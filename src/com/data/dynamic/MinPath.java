package com.data.dynamic;

public class MinPath {
    private int minDist = Integer.MAX_VALUE;

    /**
     * 每次递归都会两个分叉，向下或向右，最短距离响应提升。
     * 直到终点
     * 可以采取缓存优化
     */
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {

    }

    /**
     * 状态是从起点到当前位置的最短距离
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int i = 0; i < n; i++) {//初始化首行，每个都是从上一个累加的和
            sum += matrix[0][i];
            states[0][i] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; i++) {//初始化首列，每个都是从上一个累加的和
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {/*每个点的最短距离，是上方点最短距离和左方点最短距离中的最小值*/
                states[i][j] = matrix[i][j]+Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }

    /**
     * 状态转移方程
     * min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
     * 同时是最优子结构
     */
    private int[][] mem = new int[4][4];

    public int minDist(int i, int j) {
        if (i == 0 && j == 0) return path[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        int minUp = Integer.MAX_VALUE;
        /*在首行首列的情况下，因为只能向右、向下移动，首行首列内的最短路径就是从首行首列走过来
        minLeft/minUp一方越界，没有影响。因为另一方就是在首行首列的最短路径*/
        if (j - 1 >= 0) minLeft = minDist(i, j - 1);
        if (i - 1 >= 0) minUp = minDist(i - 1, j);
        int currMinDist = path[i][j]/*当前点*/ + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    public static int path[][] = {/*因为只能向右向下移动，因此到达首行首列上的元素的最短距离，就是沿着首行首列走*/
            {1, 3, 5, 9},
            {2, 1, 3, 4},
            {5, 2, 6, 7},
            {6, 8, 4, 3}
    };

    public static void main(String[] args) {
        MinPath minPath = new MinPath();
//        System.out.println(minPath.minDistDP(path,4));
//        System.out.println(minPath.minDist(3,3));

    }
}
