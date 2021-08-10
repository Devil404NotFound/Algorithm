package com.ljp.leecode_cn.graph;

/** 每日一题 2021.01.25
 * @author lijunpeng
 * @date 2021/1/25 23:24
 */
public class _中等_959_由斜杠划分区域 {
    public static void main(String[] args) {
        String[] grid = new String[]{" /", "/ "};
        _中等_959_由斜杠划分区域 test = new _中等_959_由斜杠划分区域();
        int result = test.regionsBySlashes2(grid);
        System.out.println(result);
    }

    /**
     * 报错了！！！（线上的连接方向需要分情况连接，并不都是往左边和上边连接）
     */
    /*
    int newN;
    Map<Character, int[][]> hash;
    UnionFind uf;
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        newN = 2 * n;
        int[] one = {0, 0};
        int[] two = {0, 1};
        int[] third = {1, 0};
        int[] four = {1, 1};
        hash = new HashMap<>();
        hash.put(' ', new int[][]{one, two, third, four});
        hash.put('/', new int[][]{one, four});
        hash.put('\\', new int[][]{two, third, four});
        int newN = 2 * n;
        uf = new UnionFind(newN * newN);
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                myUnion(hash.get(grid[i].charAt(j)), i, j);
            }
        }
        return uf.getCount();
    }
    private void myUnion(int[][] nums, int i, int j) {
        for(int[] num : nums) {
            int newI = 2 * i + num[0];
            int newJ = 2 * j + num[1];
            if(newI > 0) {
                uf.union(getIndex(newI, newJ), getIndex(newI - 1, newJ));
            }
            if(newJ > 0) {
                uf.union(getIndex(newI, newJ), getIndex(newI, newJ - 1));
            }
        }
    }
    private int getIndex(int i, int j) {
        return i * newN + j;
    }
*/

    /****官方题解***/
    /**
     *
     * @param grid
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了72.85%的用户
    内存消耗：
    37.8 MB, 在所有 Java 提交中击败了58.95%的用户
     */
    public int regionsBySlashes2(String[] grid) {
        int N = grid.length;
        int size = 4 * N * N;

        UnionFind unionFind = new UnionFind(size);
        for (int i = 0; i < N; i++) {
            char[] row = grid[i].toCharArray();
            for (int j = 0; j < N; j++) {
                // 二维网格转换为一维表格，index 表示将单元格拆分成 4 个小三角形以后，编号为 0 的小三角形的在并查集中的下标
                int index = 4 * (i * N + j);
                char c = row[j];
                // 单元格内合并
                if (c == '/') {
                    // 合并 0、3，合并 1、2
                    unionFind.union(index, index + 3);
                    unionFind.union(index + 1, index + 2);
                } else if (c == '\\') {
                    // 合并 0、1，合并 2、3
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 2, index + 3);
                } else {
                    unionFind.union(index, index + 1);
                    unionFind.union(index + 1, index + 2);
                    unionFind.union(index + 2, index + 3);
                }

                // 单元格间合并
                // 向右合并：1（当前）、3（右一列）
                if (j + 1 < N) {
                    unionFind.union(index + 1, 4 * (i * N + j + 1) + 3);
                }
                // 向下合并：2（当前）、0（下一行）
                if (i + 1 < N) {
                    unionFind.union(index + 2, 4 * ((i + 1) * N + j));
                }
            }
        }
        return unionFind.getCount();
    }

    private class UnionFind {

        private int[] parent;

        private int count;

        public int getCount() {
            return count;
        }

        public UnionFind(int n) {
            this.count = n;
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            count--;
        }
    }
}
