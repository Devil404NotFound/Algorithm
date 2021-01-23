package com.ljp.leecode_cn.graph;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2021.01.23
 * @author lijunpeng
 * @date 2021/1/23 11:53
1319. 连通网络的操作次数
用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。

网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。

给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。



示例 1：



输入：n = 4, connections = [[0,1],[0,2],[1,2]]
输出：1
解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
示例 2：



输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
输出：2
示例 3：

输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
输出：-1
解释：线缆数量不足。
示例 4：

输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
输出：0


提示：

1 <= n <= 10^5
1 <= connections.length <= min(n*(n-1)/2, 10^5)
connections[i].length == 2
0 <= connections[i][0], connections[i][1] < n
connections[i][0] != connections[i][1]
没有重复的连接。
两台计算机不会通过多条线缆连接。
 */
public class _中等_1319_连通网络的操作次数 {
    /**
     * 并查集
     * @param n
     * @param connections
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了60.80%的用户
    内存消耗：
    52.6 MB, 在所有 Java 提交中击败了56.13%的用户
     */
    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if(len < n - 1) {
            return -1;
        }
        //创建连通图
        UnionFind uf = new UnionFind(n);
        for(int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }
        return n - 1 - uf.getCountLine();
    }
    class UnionFind {
        private int[] parent;
        private int countLine;

        public UnionFind (int n) {
            parent = new int[n];
            for(int i = 0; i < n; ++i) {
                parent[i] = i;
            }
            countLine = 0;
        }
        public void union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if(root1 == root2) {
                return;
            }
            parent[root1] = root2;
            ++countLine;
        }
        public int find(int index) {
            if(parent[index] != index){
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
        public int getCountLine(){
            return this.countLine;
        }
    }

    /**
     * 官方题解一：深度优先搜索
     * @param n
     * @param connections
     * @return
    执行用时：
    13 ms, 在所有 Java 提交中击败了39.04%的用户
    内存消耗：
    54.6 MB, 在所有 Java 提交中击败了23.87%的用户
     */
    List<Integer>[] edges;
    boolean[] used;
    public int makeConnected2(int n, int[][] connections) {
        if(connections.length < n - 1) {
            return -1;
        }
        //初始化邻接列表
        edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for(int[] conn : connections) {
            int a = conn[0], b = conn[1];
            edges[a].add(b);
            edges[b].add(a);
        }
        used = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if(!used[i]) {
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }
    private void dfs(int u) {
        used[u] = true;
        for(int v : edges[u]) {
            if(!used[v]) {
                dfs(v);
            }
        }
    }
}
