package com.data.backtracking;

import java.util.Stack;

/**
 * 简单背包：
 * 背包总的承载重量是 Wkg。 n 个重量不等、不可分割的物品，如何装能让背包中物品的总重量最大？
 * 对于 n 个物品来说，每个物品都有装与不装两种情况，总的装法就有 2^n 种，
 * 穷举出这 2^n 种装法
 * 去掉总重量超过 Wkg 的，从剩下的装法中选择总重量最接近 Wkg 的。
 */
public class Packet {
    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10];//缓存共5个物品，最大装载不会超过10

    /**
     * 在已装了cw的基础上，第i个物品，有装与不装2种情况
     * 一直往下分并计算已装重量，无法再分或包满
     *
     * @param i
     * @param cw
     */
    public void f(int i, int cw) { // 调用 f(0, 0)
        if (cw == w /*搜索剪枝*/ || i == n/*无法在分成装与不装*/) { // 2、cw==w 表示装满了，i==n 表示物品都考察完了
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return;
        mem[i][cw] = true;
        f(i + 1, cw); // 1、选择不装第 i 个物品，跳去下一个；直到越界或装满而返回；未满则装入第i个物品(已装cw加重)
        if (cw + weight[i] <= w) {
            f(i + 1, cw + weight[i]); //3、 选择装第 i 个物品
        }
    }

    public static void main(String[] args) {
        //背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中
        Packet packet = new Packet();
        packet.f(0, 0);
        System.out.println(packet.maxW);
    }
}
