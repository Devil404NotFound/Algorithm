package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 每日一题 2020.10.06
 834. 树中距离之和
 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。

 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。

 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。

 示例 1:

 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 输出: [8,12,6,10,10,10]
 解释:
 如下为给定的树的示意图：
 0
 / \
 1   2
 /|\
 3 4 5

 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 */
public class _困难_834_树中距离之和 {
    public static void main(String[] args) {
        int N = 4;
        int[][] edges = {{1,2},{3,2},{3,0}};
        new _困难_834_树中距离之和().sumOfDistancesInTree(N, edges);
    }

    /**
     * 错误答案，路径求和失败
     * @param N
     * @param edges
     * @return
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        int[][] adjacency = new int[N][N];
        //初始化数组
        for (int i = 0; i < N; i++) {
            Arrays.fill(adjacency[i], 10001);
        }
        // 创建邻接矩阵
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            adjacency[x][y] = 1;
            adjacency[y][x] = 1;
        }
        //求每个节点之间的路径
        for (int i = 0; i < N; i++) {
            adjacency[i][i] = 0;
            for (int j = i + 1; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    adjacency[i][j] = Math.min(adjacency[i][j], adjacency[i][k] + adjacency[k][j]);
                }
                adjacency[j][i] = adjacency[i][j];
            }
        }
        //求总和
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i] += adjacency[i][j];
            }
        }
        return ans;
    }
    /**
     * 官方题解
     执行用时：
     19 ms, 在所有 Java 提交中击败了75.33%的用户
     内存消耗：
     48.4 MB, 在所有 Java 提交中击败了5.43%的用户
     */
    int[] ans;
    int[] sz;
    int[] dp;
    List<List<Integer>> graph;

    public int[] sumOfDistancesInTree2(int N, int[][] edges) {
        ans = new int[N];
        sz = new int[N];
        dp = new int[N];
        graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    public void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    public void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }
}
