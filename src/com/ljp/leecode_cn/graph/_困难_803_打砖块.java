package com.ljp.leecode_cn.graph;

import org.lanqiao.Utils.Util;

/** 每日一题 2021.01.16
 * @author lijunpeng
 * @date 2021/1/16 2:36
803. 打砖块
有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：

一块砖直接连接到网格的顶部，或者
至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。

注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。



示例 1：

输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
输出：[2]
解释：
网格开始为：
[[1,0,0,0]，
[1,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
[0,1,1,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
[0,0,0,0]]
因此，结果为 [2] 。
示例 2：

输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
输出：[0,0]
解释：
网格开始为：
[[1,0,0,0],
[1,1,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
[1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0],
[1,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
[0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。


提示：

m == grid.length
n == grid[i].length
1 <= m, n <= 200
grid[i][j] 为 0 或 1
1 <= hits.length <= 4 * 104
hits[i].length == 2
0 <= xi <= m - 1
0 <= yi <= n - 1
所有 (xi, yi) 互不相同 */
public class _困难_803_打砖块 {
    public static void main(String[] args) {
        _困难_803_打砖块 test = new _困难_803_打砖块();
        int[][] gird = new int[][]{{1,0,1},{1,1,1}};
        int[][] hits = new int[][]{{0,0},{0,2},{1,1}};
        int[] ret = test.hitBricks(gird, hits);
        Util.print(ret);
    }

    /**官方题解：并查集
     执行用时：
     22 ms, 在所有 Java 提交中击败了55.66%的用户
     内存消耗：
     52.3 MB, 在所有 Java 提交中击败了21.57%的用户
     */
    private int rows;
    private int cols;
    private final static int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        //为了不修改原数组
        int[][] code = new int[rows][];
        for(int i = 0; i < rows; ++i) {
            code[i] = grid[i].clone();
        }
        //逆向思维，先给需要打的砖块去掉
        for(int i = 0; i < hits.length; ++i) {
            code[hits[i][0]][hits[i][1]] = 0;
        }
        int size = rows * cols;
        UnionFind unionFind = new UnionFind(size + 1); //节点size虚拟为屋顶
        //将所有第一行的值都连接到屋顶上
        for(int i = 0; i < cols; ++i) {
            if(code[0][i] == 1) {
                unionFind.union(i, size);
            }
        }
        //建立连通图
        for(int i = 1; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                if(code[i][j] == 0) {
                    continue;
                }
                int y = getIndex(i, j);
                //查看上方是否可以连接
                if(code[i - 1][j] == 1) {
                    int x = getIndex(i - 1, j);
                    unionFind.union(x, y);
                }
                //查看左方是否可以连接
                if(j > 0 && code[i][j - 1] == 1) {
                    int x = getIndex(i, j - 1);
                    unionFind.union(x, y);
                }
            }
        }
        int[] ret = new int[hits.length];
        for(int i = hits.length - 1; i >= 0; --i) {
            //需要添加的砖块
            int x = hits[i][0];
            int y = hits[i][1];
            if(grid[x][y] == 0) {
                continue;
            }
            //没加上当前砖块时，连接屋顶的砖块数量
            int orgin = unionFind.getSize(size);
            if(x == 0) {
                unionFind.union(y, size);
            }
            for(int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if(isArea(newX, newY) && code[newX][newY] == 1) {
                    unionFind.union(getIndex(x, y), getIndex(newX, newY));
                }
            }
            int current = unionFind.getSize(size);
            ret[i] = Math.max(0, current - orgin - 1);
            code[x][y] = 1;
        }
        return ret;
    }
    //二维坐标转换为一维坐标
    private int getIndex(int x, int y) {
        return x * cols + y;
    }
    //判断边界
    private boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
    //并查集
    private class UnionFind {
        private int[] parent;
        private int[] size;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n]; //记录每个节点的子树数量
            for(int i = 0; i < n; ++i) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }
        public int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public int getSize(int x){
            return size[find(x)];
        }
    }
}
