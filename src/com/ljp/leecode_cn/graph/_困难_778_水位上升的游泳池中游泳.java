package com.ljp.leecode_cn.graph;

import java.util.ArrayList;
import java.util.List;

/** 每日一题2021.01.30
 * @author lijunpeng
 * @date 2021/1/30 0:59
778. 水位上升的泳池中游泳
在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？



示例 1:

输入: [[0,2],[1,3]]
输出: 3
解释:
时间为0时，你位于坐标方格的位置为 (0, 0)。
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
示例2:

输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
输出: 16
解释:
0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的


提示:

2 <= N <= 50.
grid[i][j] 是 [0, ..., N*N - 1] 的排列。
 */
public class _困难_778_水位上升的游泳池中游泳 {
    //错误答案
    //排序后有可能起点和终点直接连起来
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int size = n * n;
        UnionFind uf = new UnionFind(size);
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new int[]{i * n + j, grid[i][j]});
            }
        }
        list.sort((a, b) -> a[1] - b[1]);
        int index = 0;
        int ans = 0;
        for (int i = 0; i < size; i++) {
            while(list.get(index)[1] <= i) {
                if(uf.union(list.get(index)[0], 0)) {
                    if(uf.isConnected(0, size - 1)) {
                        ans = i;
                    }
                }
                index++;
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

    /**
     * 官方题解二：并查集
     执行用时：
     8 ms, 在所有 Java 提交中击败了83.61%的用户
     内存消耗：
     38.1 MB, 在所有 Java 提交中击败了95.19%的用户
     */
    private int N;

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater2(int[][] grid) {
        this.N = grid.length;

        int len = N * N;
        // 下标：方格的高度，值：对应在方格中的坐标
        int[] index = new int[len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index[grid[i][j]] = getIndex(i, j);
            }
        }

        UnionFind2 unionFind = new UnionFind2(len);
        for (int i = 0; i < len; i++) {
            int x = index[i] / N;
            int y = index[i] % N;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY) && grid[newX][newY] <= i) {
                    unionFind.union(index[i], getIndex(newX, newY));
                }

                if (unionFind.isConnected(0, len - 1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getIndex(int x, int y) {
        return x * N + y;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private class UnionFind2 {

        private int[] parent;

        public UnionFind2(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int root(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean isConnected(int x, int y) {
            return root(x) == root(y);
        }

        public void union(int p, int q) {
            if (isConnected(p, q)) {
                return;
            }
            parent[root(p)] = root(q);
        }
    }

}
