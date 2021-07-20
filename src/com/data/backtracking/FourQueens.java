package com.data.backtracking;

import java.util.HashSet;
import java.util.Set;

public class FourQueens {
    /**
     * 暴力求解法
     * 4^4次
     */
    public void force() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {/*四层循环代表每行，不会同行*/
                        if ((i != j && i != k && i != l && j != k && j != l && k != l)/*jikl不会同列*/ &&
                                (j != i + 1 && k != i + 2 && l != i + 3 &&
                                        k != j + 1 && l != j + 2 &&
                                        l != k + 1) &&/*撇*/
                                (j != i - 1 && k != i - 2 && l != i - 3 &&
                                        k != j - 1 && l != j - 2 &&
                                        l != k - 1)/*捺*/

                        ) {
                            System.out.printf("%d,%d,%d,%d\n", i, j, k, l);
                        }
                    }
                }
            }
        }
    }

    /**
     * 假设棋盘左上角座标为(0,0)
     * 放置后就去下一行，本行不能再放
     * @param row 先在正在搜索第几行 从0行开始
     */
    public void backtrack(int row) {
        System.out.println("\n查找第" + row + "行");
        for (int col = 0; col < 4; col++) {
            if (can_place_queen(row, col)) {//todo 1、从第0行开始，棋盘是空的(0,0)当然能放  4、(2,1)处可以放 7、第二行都不能放   9、(3,1)处可以放 12、第二行都不能放 14、第一行怎么放第二行都不能放 16、(1,0)处可以放 19、(3,1)处可以放 22、(0,2)处可以放 25、(2,3)处可以放
                place_queen(row, col);//todo 2、在(0,0)处放置棋子  5、在(2,1)处放置 10、在(3,1)处放置 17、在(0,1)处放置 20、在(3,1)处放置 23、在(0,2)处放置 26、在(2,3)处放置
                if (row == 3)
                    printQueens(queues);//放置完成后，又是最后一行，则说明找到一个可行解 todo 27、放置完成 第123行都只有当前1个位置可放置，从123行依次删除到0行(1,0)，0行换下一列(2,0)
                else/*否则继续找下一行*/
                    backtrack(row + 1);//todo 3、去查第1行   6、去查第2行 11、再去查第2行 18、去查找第1行 21、去查找第2行 24、查找第3行
                remove_queen(row, col);//todo 8、删除插入的(2,1)   13、删除插入的(3,1) 15、删除插入的(0,0)
            }
        }
    }
    /*
     * 回溯思想backtrack是最关键的
     * 至于采用哪种数据结构来表示棋盘，并记录皇后的位置。可以用二维数组表示并记录，或者一维数组下标为row 值为col，或者set等
     * 每次放置就把已放置位置、已放置的列、对角线存下来，以便下次查看是否能放的时候可以比较
     * 也可以不存 已放置的列和对角线，通过已放置位置求已放置的列，通过倒序遍历插入点的左上、右上来判断是否构成对角线。理解略微复杂
     * */


    /**
     * 对于一个矩阵而言，一条从左上角到右下角对角线上的所有坐标的横纵值之差是固定的，用上面的例子举例，
     * (0,0),(1,1), (2,2), (3,3) 四个坐标都处于这条对角线上，而它们的横纵值只差都为 0 ；
     * 类似地，一条从右上角到左下角对角线上的所有坐标的横纵值之和是固定的
     * (3,0),(2,1), (1,2), (0,3) 这四个坐标，它们的横纵值之和都为 3 。
     * 这样只需要维护两个数组结构就能表示给定坐标的两条对角线上有没有其他皇后存在。
     */
    /**
     * @param row
     * @param col
     * @return
     */
    private boolean can_place_queen(int row, int col) {
        System.out.printf("(%d,%d)\n", row, col);
        return !cols[col] && !pie.contains(row - col) && !na.contains(row + col);
    }

    int[] queues = new int[4];//下标表示行，值表示列
    boolean[] cols = new boolean[4];//
    //    boolean[] lt = new boolean[7];//
//    boolean[] rt = new boolean[7];//
    Set pie = new HashSet();
    Set na = new HashSet();

    private void place_queen(int row, int col) {
        System.out.printf("放置(%d,%d)", row, col);
        queues[row] = col;
        cols[col] = true;
        pie.add(row - col);/*撇：只要行列差为该常数的位置都被占用*/
        na.remove(row + col);/*捺：只要行列和为该常数的位置都被占用*/
    }

    private void remove_queen(int row, int col) {
        System.out.printf("删除(%d,%d)", row, col);
        queues[row] = 0;
        cols[col] = false;
        pie.remove(row - col);
        na.remove(row + col);
    }

    private void printQueens(int[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.printf("第%d行,第%d个\n", i, result[i]);
        }
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (result[row] /*第r行的c位置*/ == col) System.out.print(" Q ");
                else System.out.print(" X ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        new FourQueens().force();
        new FourQueens().backtrack(0);
    }
}
