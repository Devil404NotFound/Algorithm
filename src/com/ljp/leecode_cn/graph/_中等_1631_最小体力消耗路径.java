package com.ljp.leecode_cn.graph;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2021.01.29
 * @author lijunpeng
 * @date 2021/1/29 11:37
1631. 最小体力消耗路径
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。



示例 1：



输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
输出：2
解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
示例 2：



输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
输出：1
解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
示例 3：


输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
输出：0
解释：上图所示路径不需要消耗任何体力。


提示：

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */
public class _中等_1631_最小体力消耗路径 {
    /**
     * 并查集
     * @param heights
     * @return
    执行用时：
    114 ms, 在所有 Java 提交中击败了41.63%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了24.04%的用户
     */
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int columns = heights[0].length;
        //计算每个网格之间的权值
        List<int[]> weightList = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = i * columns + j;
                if(i != 0) {
                    weightList.add(new int[]{x, x - columns, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if(j != 0) {
                    weightList.add(new int[]{x, x - 1, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        //按照权值升序排序
        weightList.sort((a, b) -> a[2] - b[2]);
        int ans = 0;
        UnionFind uf = new UnionFind(rows * columns);
        for (int[] ints : weightList) {
            int x = ints[0], y = ints[1], v = ints[2];
            uf.union(x, y);
            if(uf.isConnected(0, rows * columns - 1)) {
                ans = v;
                break;
            }
        }
        return ans;
    }
    private class UnionFind {
        private int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public boolean union(int index1, int index2) {
            int root1 = find(index1);
            int root2 = find(index2);
            if(root1 == root2) {
                return false;
            }
            parent[root2] = root1;
            return true;
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
    }
}
