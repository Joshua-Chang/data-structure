package com.data.recursion;

import java.util.HashMap;

public class Step {
    private HashMap<Integer,Integer> hasSolved=new HashMap<>();
    private int depth;

    public Step() {
        depth=0;
    }

    int f(int steps){
//        depth++;
//        if (depth>1000)throw new Exception(); 栈溢出
        if (steps==1)return 1;
        if (steps==2)return 2;
        if (hasSolved.containsKey(steps))return hasSolved.get(steps);//计算过直接取
        int ret = f(steps - 1) + f(steps - 2);
        hasSolved.put(steps,ret);//保存结果，以免重复计算
        return ret;
    }
    public static void main(String[] args) {
    }
}
