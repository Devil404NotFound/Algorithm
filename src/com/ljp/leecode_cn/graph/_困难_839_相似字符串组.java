package com.ljp.leecode_cn.graph;

/** 每日一题 2021.01.31
 * @author lijunpeng
 * @date 2021/1/31 14:11
 */
public class _困难_839_相似字符串组 {
    /**
     * 并查集（参考官方题解check）
     * @param strs
     * @return
    执行用时：
    18 ms, 在所有 Java 提交中击败了70.83%的用户
    内存消耗：
    38.2 MB, 在所有 Java 提交中击败了65.25%的用户
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);
        //关联每个可以连接的节点
        for(int i = 0; i < n; ++i) {
            for(int j = i + 1; j < n; ++j) {
                //检查是否已经连接，是否符合关联条件
                if(!uf.isConnected(i, j) && check(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
    //检查是否符合关联条件
    private boolean check(String str1, String str2) {
        int num = 0;
        for(int i = 0; i < str1.length(); ++i) {
            if(str1.charAt(i) != str2.charAt(i)) {
                ++num;
                if(num > 2) {
                    return false;
                }
            }
        }
        return true;
    }
    //连通图类
    private class UnionFind {
        private int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }
        public void union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if(root1 != root2) {
                parent[root2] = root1;
            }
        }
        public int find(int index) {
            if(parent[index] != index) {
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }
        public boolean isConnected(int index1, int index2) {
            return find(index1) == find(index2);
        }
        //获取不连通图个数
        public int getCount() {
            int count = 0;
            for(int i = 0; i < parent.length; ++i) {
                if(parent[i] == i) {
                    ++count;
                }
            }
            return count;
        }
    }
}
