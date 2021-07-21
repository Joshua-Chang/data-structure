package com.data.dynamic;

public class Path {
    /**
     * 求从起点到终点多少种走法，只能向下或向右
     * <p>
     * 递归：。就是二维的Fib
     * 从某个点出发到终点的走法，都可以分解成该点的右边点和该点的下边点的走法和
     * 从起点到终点，和Fib一样大量重复计算
     * 时间复杂度指数级 (可以缓存优化)
     *
     * @param grid [row][col]
     *             t 阻碍格子 f正常格子 f多便于初始化
     *             要boolean初始化一个元素都为true的数组
     *             Arrays.fill(arr, true);可以用Arrays填充true
     *             但是没意义浪费资源，把t、f反过来用就行了
     * @param row
     * @param col
     * @return
     */
    public int countPaths(boolean[][] grid, int row, int col) {
        if (!validSquare(grid, row, col)) return 0;//非有效格子
        if (isAtEnd(grid, row, col)) return 1;//末尾格子
        return countPaths(grid, row + 1, col) + countPaths(grid, row, col + 1);
    }

    /**
     * 终点
     * 到达最后一列，就只有往下走一条路
     * 到达最后一行，就只有往右走一条路
     */
    private boolean isAtEnd(boolean[][] grid, int row, int col) {
        if (row == 7 || col == 7) return true;
        return false;
    }

    /**
     * 有效正常格子
     */
    private boolean validSquare(boolean[][] grid, int row, int col) {
        return !grid[row][col];
    }


    /**
     * 动态规划
     * 求总走法
     * 递推：反过来从终点到起点
     * 终点左上角的点有经过，终点上边点和终点左边点两种走法
     * 状态转移方程每次都是从相邻的状态递推
     * opt[i,j]=opt[i-1,j]+opt[i,j-1] 当前点到终点的走法是下方点走法和右方点走法的和
     * 每个点的是状态是当前点到终点的走法总数，等于其下方点到终点的走法数，加其右方点到终点的走法数之和。
     * 状态 opt[r][c] 在二维数组里保存状态
     * 状态转移变化的方程 opt[r][c] = opt[r + 1][c] + opt[r][c + 1]
     * 最优子结构：解决总问题同时也最优解决了大量的子结构问题
     */
    public int countPaths2(boolean[][] grid, int row, int col) {/*穿的是开始的座标*/
        int[][] opt = new int[grid.length][grid[0].length];/*新建数组传的是length*/
        for (int r = row; r >= 0; r--) {
            for (int c = col; c >= 0; c--) {
                if (validSquare(grid, r, c)) {
                    if (isAtEnd(grid, r, c)) opt[r][c] = 1;
                    else opt[r][c] = opt[r + 1][c] + opt[r][c + 1];/*以左上角为(0,0)的写法*/
                } else opt[r][c] = 0;
            }
        }
        return opt[0][0];

        //以右下角为(0,0)的写法  为了省事，此写法只改了座标系，validSquare里障碍物位置却还是原来座标系的。因此只做理论证明使用
//        for (int r = 0; r <= row; r++) {
//            for (int c = 0; c <= col; c++) {
//                if (validSquare(grid, r, c))
//                    if (r == 0 || c == 0) opt[r][c] = 1;
//                    else opt[r][c] = opt[r - 1][c] + opt[r][c - 1];/*以右下角为(0,0)的写法*/
//                else opt[r][c] = 0;
//            }
//        }
//        return opt[7][7];
    }

    public static void main(String[] args) {
        boolean[][] grid = new boolean[8][8];
        grid[1][2] = true;
        grid[1][6] = true;
        grid[2][4] = true;
        grid[3][0] = true;
        grid[3][2] = true;
        grid[3][5] = true;
        grid[4][2] = true;
        grid[5][3] = true;
        grid[5][4] = true;
        grid[5][6] = true;
        grid[6][1] = true;
        grid[6][5] = true;
        Path path = new Path();
//        System.out.println(path.countPaths(grid, 0, 0));
        System.out.println(path.countPaths2(grid, 7, 7));

    }
}
