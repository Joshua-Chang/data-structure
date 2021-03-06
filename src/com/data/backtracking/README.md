# 八皇后问题

<img src="八皇后.png" alt="八皇后" style="zoom: 33%;" />

1. 从一个空白的棋盘开始，我们尝试放置第一个皇后，因为现在棋盘是空的，所以任何位置都是可以放的，那么直接放到第一行第一列（坐标 `(0,0)` ）。
2. 这时候第一行和第一列都被占了，根据国际象棋的规则， `(1,1)` 这个位置也不能放，所以第二行只能从 `(2,1)` 开始放置。这时的棋盘如下图所示。
3. 这个时候我们发现，第三个皇后无论放在第三行的哪个位置，都会受到前两个皇后的攻击，所以这明显不是一条通路，所以回到上层发现，第二行还有一个可行的位置 `(3,1)`，那么我们把第二个皇后放在该位置尝试一下。
4. 很不幸，这个时候整个第三行也处于前两个皇后的攻击范围之内，而我们已经穷尽了第二行的所有可能性，这个时候只能回到第一行，尝试把第一个皇后放置到下一个允许的位置 `(1,0)` 上。
5. 这时第二行只有一个可以放置皇后的位置 `(3,1)` 。
6. 接下来把第三个皇后放到第三行的 `(0,2)` 。
7. 第四行还有一个可行的位置 `(2,3)` 。

到此找到了一个『四皇后』问题的可行解。转换成代码：

因为两个皇后不可能出现在同一行，所以我们一次只处理一行的情况，放置完毕后，如果此行已经是最后一行，说明我们成功找到了一个可行解，否则继续搜索下一行。当下一行搜索完毕后，把当前行的皇后挪到下一个位置并重复上述过程。

回溯思想backtrack是最关键的
至于采用哪种数据结构来表示棋盘，并记录皇后的位置。可以用二维数组表示并记录，或者一维数组下标为row 值为col，或者set等。

要全局记录的有皇后的位置、某列上有没有皇后、对角线有没有皇后。可用set、数组等存储。
 - 某个位置的撇对角线上的所有位置纵横座标之和固定为某个常数
 - 某个位置的捺对角线上的所有位置纵横座标之查固定为某个常数

通过是否在同一列，同一对角线来判断该判断点能否放置
简单方法：已放置的列、已占的对角线在放置时求出并存下来，用于和判断点比对。
复杂方法：也可以不存 已放置的列和对角线。当和判断点比对时，根据已放置的位置去计算。
- 通过已放置位置求已放置的列
- 通过倒序遍历插入点的左上、右上来判断是否构成对角线。理解略微复杂

# 0-1背包

## 回溯法

假设物品重量分别为{7,7,5}  背包承重为15，如何装最多？

每个商品都有装与不装2种状态，有$i$个商品时，就有$2^i$种选法	

回溯法就是穷举出所有的装法，计算每种装法的装载量，求得最接近背包最大承重的值。

- 递归函数`f(i,cw)`，在已装cw的基础上，该物品i可以装或不装。i为第几个物品，cw为已装载量。调用装与不装两种方向，累加第$i$个商品去递归。
  - `f(i + 1, cw)`不装第 i 个商品，跳下一个	
  - `f(i + 1, cw + weight[i])`装第 i 个商品，跳下一个
- 递归停止条件为遍历完$i$个商品，或累加的装载量超出背包承重。然后比较装载量最大值。
  - i == length*遍历到末尾* 	cw == w *搜索剪枝*  	

```java
public void f(int i, int cw) { // 调用 f(0, 0)
    if (cw == w /*搜索剪枝*/ || i == n/*遍历到末尾，无法在分成装与不装*/) { 
        if (cw > maxW) maxW = cw;
        return;
    }
    if (mem[i][cw]) return;
    mem[i][cw] = true;
    f(i + 1, cw); // 1、选择不装第 i 个物品；
    if (cw + weight[i] <= w) {
        f(i + 1, cw + weight[i]); //3、 选择装第 i 个物品；
    }
}
```

![](背包.png	)

每层的节点（状态）指数级增长

优化

1. 可以利用搜索剪枝的技巧，当发现超过承重时停止探索剩下的物品。
2. 可以标记之前的计算，免去重复计算。（如图中`f(2,7)` 从`f(1,0)`递归到`f(2,7)`记录下，从`f(1,7)`递归到`f(1,7)`时免去计算）。类似缓存思想，但不记录值，只标记为曾经计算过。

