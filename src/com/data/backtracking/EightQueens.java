package com.data.backtracking;

import com.sun.rowset.internal.Row;

/**
 * 在8x8 的棋盘，里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
 * 八皇后问题就是期望找到所有满足这种要求的放棋子方式
 * <p>
 * 把这个问题划分成 8 个阶段，依次将 8 个棋子放到第一行、第二行、第三行……第八行。
 * 在放置的过程中，我们不停地检查当前的方法，是否满足要求。
 * 如果满足，则跳到下一行继续放置棋子；如果不满足，那就再换一种方法，继续尝试。
 */
public class EightQueens {
    int[] result = new int[8];//下标表示行，值表示列，即这行的第几个

    /**
     * 开始放置
     *
     * @param row =0时开始，即从第0行开始放。=8打印结果
     */
    public void cal8queens(int row) {
        if (row == 8) {//0-7行 共8个棋子都放置好了
            printQueens(result);
            return;
        }
        for (int col = 0; col < 8; col++) {//每行有8中放法
            if (isOk(row, col)) {
                result[row] = col;//放置。第r行，在c列
                cal8queens(row + 1);//下一行
            }
        }
    }

    /**
     * 通过是否在同一列，同一对角线来判断该判断点能否放置
     * 简单方法：已放置的列、已占的对角线在放置时求出并存下来，用于和判断点比对。
     *
     * 复杂方法：也可以不存 已放置的列和对角线。当和判断点比对时，根据已放置的位置去计算。
     * 通过已放置位置求已放置的列，
     * 通过倒序遍历插入点的左上、右上来判断是否构成对角线。理解略微复杂
     */
    private boolean isOk(int row, int col) {//判断点
        int leftup = col - 1, rightup = col + 1;
        //从底往上检查，因此对角线只考虑左上右上既可
        for (int i = row - 1; i >= 0; i--) {//从判断点上一行，逐行往上检查每一行。因此只有往上的对角线
            //要考虑行、列、撇、捺 四个方向：当前行不用考虑
            if (result[i] == col) return false;//查找出之前行中已放置的位置，不能与其同列
            //因为不比较同行，所以leftup、rightup是左上右上
            if (leftup >= 0) {//第i行左上对角线已经放置了
                if (result[i] == leftup) return false;//之前行的已放置位置，在判断点的上一列，判断点和之前行放置的位置构成左上对角线
            }
            if (rightup < 8) {//第i行右上对角线已经放置了
                if (result[i] == rightup) return false;//之前行的已放置位置，在判断点的下一列，判断点和之前行放置的位置构成右上对角线
            }
            //行号大于等于2时，才有意义；否则for只循环一次，这里没意义。
            --leftup;//依次往左上找，之前行放置的位置，是否和判断点和构成左上对角线关系
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.printf("第%d行,第%d个\n", i, result[i]);
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (result[row] /*第r行的c位置*/ == col) System.out.print(" Q ");
                else System.out.print(" X ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new EightQueens().cal8queens(0);
    }
}
