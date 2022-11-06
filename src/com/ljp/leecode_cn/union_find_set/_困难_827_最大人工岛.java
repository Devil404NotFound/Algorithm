package com.ljp.leecode_cn.union_find_set;

/**
 * @author lijunpeng
 * @date 2022/9/18 17:12
 * @description
 **/

public class _困难_827_最大人工岛 {
    public int largestIsland(int[][] grid) {
        return -1;
    }

    private class UnionFindSet {
        private int[] parent;
        public UnionFindSet(int n) {
            this.parent = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }
        public int find(Integer num) {
            if(parent[num] != num) {
                // 路径压缩
                parent[num] = find(parent[num]);
            }
            return parent[num];
        }
        public void union(Integer a, Integer b) {
            this.parent[find(a)] = find(b);
        }
    }

}