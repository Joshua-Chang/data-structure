package com.data.search.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int vertices;//顶点复数 vertex单数
    private LinkedList<Integer> adj[];//邻接表

    public Graph(int vertices) {
        this.vertices = vertices;
        adj = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 建立边
     * 两顶点之间的关系就是边
     *
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        /*无向图 两个点都存*/
        adj[s].add(t);
        adj[t].add(s);

    }

    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[vertices];//顶点数大小的数组，来记录顶点是否被访问过
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();//先进先出的队列，来记录与被访问的顶点，可达相邻的顶点。即下一步可以访问的顶点
        queue.add(s);
        int[] prev = new int[vertices];//记录搜索路径，作为结果 prev[i]=k 表示i顶点是从k访问过来的
        Arrays.fill(prev, -1);
        while (queue.size() != 0) {
            int w = queue.poll();/*最开始是s*/
            for (int i = 0; i < adj[w].size()/*该顶点的边数*/; i++) {/*遍历w顶点的邻接表，即遍历该顶点的边*/
                int q = adj[w].get(i);/*第i个相邻可达顶点q*/
                if (!visited[q]) {/*相邻可达顶点q未被访问过*/
                    prev[q] = w;//是从w顶点访问的q顶点
                    if (q == t) {//到了目的地
                        print(prev, s, t);
                    }
                    visited[q] = true;/*q被访问过*/
                    queue.add(q);//后续的while循环，可以搜索q所有的边
                }
            }
        }
    }

    boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[vertices];/*记录顶点是否被访问过*/
        int[] prev = new int[vertices];/*记录访问路径*/
        Arrays.fill(prev, -1);
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    /**
     * 递归/回溯
     * 递归公式：
     * 终止条件：
     *
     * @param w
     * @param t
     * @param visited
     * @param prev
     */
    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true)
            return;
        visited[w] = true;
        if (w == t) {/*找到了*/
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {/*遍历w顶点的邻接表，找出每个边*/
            int q = adj[w].get(i);//w的相邻可达顶点q
            if (!visited[q]) {
                prev[q] = w;//记录下：q顶点是从w顶点访问而来的，即从w顶点访问量q顶点
                recurDfs(q, t, visited, prev);//递归再把q做为起点找到t的路径
            }
        }
    }


    /**
     * 递归打印
     * 从末尾顶点，依次打印出访问该顶点的顶点
     *
     * @param prev 存储路径的数组，设计很巧妙。prev[i]=k 表示 i顶点是从k顶点访问而来的
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t]/*访问t的顶点*/ != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph=new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
        graph.bfs(0,6);

        graph.dfs(0,6);

    }
}
