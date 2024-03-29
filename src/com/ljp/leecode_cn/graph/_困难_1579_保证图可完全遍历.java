package com.ljp.leecode_cn.graph;

/** 每日一题 2021.01.27
 * @author lijunpeng
 * @date 2021/1/27 18:06
1579. 保证图可完全遍历
Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：

类型 1：只能由 Alice 遍历。
类型 2：只能由 Bob 遍历。
类型 3：Alice 和 Bob 都可以遍历。
给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。

返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。



示例 1：



输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
输出：2
解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
示例 2：



输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
输出：0
解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
示例 3：



输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
输出：-1
解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。


提示：

1 <= n <= 10^5
1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
edges[i].length == 3
1 <= edges[i][0] <= 3
1 <= edges[i][1] < edges[i][2] <= n
所有元组 (typei, ui, vi) 互不相同
 */
public class _困难_1579_保证图可完全遍历 {
    public static void main(String[] args) {
        _困难_1579_保证图可完全遍历 test = new _困难_1579_保证图可完全遍历();
        int n = 4;
        int[][] edges = new int[][]{{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        int result = test.maxNumEdgesToRemove(n ,edges);
        System.out.println(result);
    }

    /**
     * 并查集
     * @param n
     * @param edges
     * @return
    执行用时：
    15 ms, 在所有 Java 提交中击败了87.63%的用户
    内存消耗：
    99 MB, 在所有 Java 提交中击败了64.95%的用户
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int count = 0;
        UnionFind ufAlice = new UnionFind(n + 1);
        UnionFind ufBob = new UnionFind(n + 1);
        //把共同的边连起来
        for(int i = 0; i < edges.length; ++i) {
            if(edges[i][0] == 3){
                int index1 = edges[i][1];
                int index2 = edges[i][2];
                if(ufAlice.union(index1, index2)) {
                    ufBob.union(index1, index2);
                }else{
                    ++count;
                }
            }
        }
        //将分别Alice和Bob的边各自在自己的连通图中连起来
        for(int i = 0; i < edges.length; ++i) {
            int type = edges[i][0];
            int index1 = edges[i][1];
            int index2 = edges[i][2];
            if(type ==1) {//Alice
                if(!ufAlice.union(index1, index2)) {
                    ++count;
                }
            }else if(type == 2) {
                if(!ufBob.union(index1, index2)) {
                    ++count;
                }
            }
        }
        if(ufAlice.getLineNum() + 1 < n || ufBob.getLineNum() + 1 < n) {
            return -1;
        }
        return count;
    }
    private class UnionFind {
        private int[] parent;
        private int lineNum;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            lineNum = 0;
        }
        public boolean union(int index1, int index2) {
            int root1 = find(index1), root2 = find(index2);
            if(root1 == root2) {
                return false;
            }
            parent[root1] = root2;
            lineNum++;
            return true;
        }
        public int find(int index) {
            if(parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
        public int getLineNum() {
            return this.lineNum;
        }
    }
}
