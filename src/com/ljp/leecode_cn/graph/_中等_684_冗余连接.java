package com.ljp.leecode_cn.graph;

/**每日一题 2021.01.13
 * @author lijunpeng
 * @date 2021/1/13 23:42
684. 冗余连接
在本问题中, 树指的是一个连通且无环的无向图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。

示例 1：

输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
1
/ \
2 - 3
示例 2：

输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
|   |
4 - 3
注意:

输入的二维数组大小在 3 到 1000。
二维数组中的整数在1到N之间，其中N是输入数组的大小。
更新(2017-09-26):
我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
 */
public class _中等_684_冗余连接 {
    /**
     * 并查集
     * @param edges
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了87.99%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了8.39%的用户
     */
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind unionFind = new UnionFind(n + 1);
        int[] ret = new int[2];
        for(int i = 0; i < n; ++i) {
            int[] edge = edges[i];
            int index1 = edge[0], index2 = edge[1];
            if(unionFind.find(index1) == unionFind.find(index2)) {
                ret = edge;
            }else{
                unionFind.union(index1, index2);
            }
        }
        return ret;
    }
    class UnionFind {
        int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }
        private void union(int index1, int index2) {
            parent[find(index1)] = find(index2);
        }
        private int find(int index) {
            if(parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
    }

    /**
     * 官方题解：并查集
     * @param edges
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了87.99%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了17.62%的用户
     */
    public int[] findRedundantConnection2(int[][] edges) {
        int nodesCount = edges.length;
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < nodesCount; i++) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (find(parent, node1) != find(parent, node2)) {
                union(parent, node1, node2);
            } else {
                return edge;
            }
        }
        return new int[0];
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
