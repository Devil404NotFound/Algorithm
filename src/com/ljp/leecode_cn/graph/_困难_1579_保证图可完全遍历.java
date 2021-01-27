package com.ljp.leecode_cn.graph;

/** 每日一题 2021.01.27
 * @author lijunpeng
 * @date 2021/1/27 18:06
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
