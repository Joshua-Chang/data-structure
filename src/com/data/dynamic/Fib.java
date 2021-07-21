package com.data.dynamic;

public class Fib {
    /**
     * 递归
     *
     * @param n
     */
    public int fibR(int n) {
        return n <= 1 ? n : fibR(n - 1) + fibR(n - 2);
    }

    /**
     * 递归、加缓存
     */
    private int[] mem;

    public int fibR_mem(int n) {
        if (n <= 0) return 0;
        else if (n == 1) return 1;
        else {
            if (mem[n] != 0) mem[n] = fibR_mem(n - 1) + fibR_mem(n - 2);
            return mem[n];
        }
    }

    /**
     * 递归+记忆化==>递推
     * 递推公式/动态转移方程 F[n]=F[n-1]+F[n-2]
     * 反过来从最小到最大
     */
    public int fib2(int n) {
        int[] F = new int[n];
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i <=n; i++) {
            F[i]=F[i-1]+F[i-2];
        }
        return F[n];
    }
}
