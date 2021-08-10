package com.ljp.leecode_cn.graph;

import java.util.*;

/** 每日一题 2021.01.21
 * @author lijunpeng
 * @date 2021/1/21 12:26
1489. 找到最小生成树里的关键边和伪关键边
给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。

请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。

请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。



示例 1：



输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
输出：[[0,1],[2,3,4,5]]
解释：上图描述了给定图。
下图是所有的最小生成树。

注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
示例 2 ：



输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
输出：[[],[0,1,2,3]]
解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。


提示：

2 <= n <= 100
1 <= edges.length <= min(200, n * (n - 1) / 2)
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti <= 1000
所有 (fromi, toi) 数对都是互不相同的。
 */
public class _困难_1489_找到最小生成树的关键边和伪关键边 {
    /**
     * 模仿官方题解一：Kruskal算法+并查集
     * @param n
     * @param edges
     * @return
    执行用时：
    37 ms, 在所有 Java 提交中击败了20.56%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了10.28%的用户
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int len = edges.length;
        int[][] newEdges = new int[len][];
        for (int i = 0; i < len; i++) {
            newEdges[i] = new int[4];
            for (int j = 0; j < 3; j++) {
                newEdges[i][j] = edges[i][j];
            }
            newEdges[i][3] = i;
        }
        Arrays.sort(newEdges, (a, b) -> a[2] - b[2]);
        int value = 0;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < len; i++) {
            int[] edge = newEdges[i];
            int x = edge[0], y = edge[1], weight = edge[2];
            if(uf.union(x, y)) {
                value += weight;
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        ret.add(new ArrayList<>());
        ret.add(new ArrayList<>());
        for (int i = 0; i < len; i++) {
            //判断关键边
            uf = new UnionFind(n);
            int v = 0;
            for (int j = 0; j < len ; j++) {
                if(i != j && uf.union(newEdges[j][0], newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(uf.getSize() < n - 1 || (uf.getSize() == n - 1 && v > value)) {
                ret.get(0).add(newEdges[i][3]);
                continue;
            }
            //判断伪关键边
            uf = new UnionFind(n);
            v = newEdges[i][2];
            uf.union(newEdges[i][0], newEdges[i][1]);
            for (int j = 0; j < len; j++) {
                if(i != j && uf.union(newEdges[j][0], newEdges[j][1])){
                    v += newEdges[j][2];
                }
            }
            if(uf.getSize() == n - 1 && v == value) {
                ret.get(1).add(newEdges[i][3]);
            }
        }
        return ret;
    }
    class UnionFind {
        private int[] parent;
        private int size;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int getSize() {
            return this.size;
        }
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return false;
            }
            parent[rootX] = rootY;
            ++size;
            return true;
        }
        public void disjoint(int index) {
            parent[index] = index;
            --size;
        }
        public int find(int index) {
            if(parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }
}
