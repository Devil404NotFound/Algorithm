package com.ljp.leecode_cn.array;

/** 每日一题 2021.03.02
 * @author lijunpeng
 * @date 2021/3/2 21:44
304. 二维区域和检索 - 矩阵不可变
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。

Range Sum Query 2D
上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。



示例：

给定 matrix = [
[3, 0, 1, 4, 2],
[5, 6, 3, 2, 1],
[1, 2, 0, 1, 5],
[4, 1, 0, 1, 7],
[1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12


提示：

你可以假设矩阵不可变。
会多次调用 sumRegion 方法。
你可以假设 row1 ≤ row2 且 col1 ≤ col2 。
 */
public class _中等_304_二维区域和检索 {
    public static void main(String[] args) {
        _中等_304_二维区域和检索 test = new _中等_304_二维区域和检索();
        int[][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        int row1 = 2;
        int col1 = 1;
        int row2 = 4;
        int col2 = 3;
        int result = numMatrix.sumRegion(row1, col1, row2, col2);
        System.out.println(result);
    }

    /**
     * 利用二维前缀和
     * 官方题解还有一个一维前缀和
     执行用时：
     14 ms, 在所有 Java 提交中击败了98.81%的用户
     内存消耗：
     43.9 MB, 在所有 Java 提交中击败了85.95%的用户
     */
    static class NumMatrix {
        private int[][] preMatrix;
        private int m, n;
        public NumMatrix(int[][] matrix) {
            m = matrix.length;
            if(m > 0) {
                n = matrix[0].length;
                preMatrix = new int[m + 1][n + 1];//矩阵的前缀和
                for(int i = 1; i <= m; ++i) {
                    for(int j = 1; j <= n; ++j) {
                        //前缀 = 当前节点 + 上一个 + 左一个 - 左上方
                        preMatrix[i][j] = preMatrix[i - 1][j] + preMatrix[i][j - 1] - preMatrix[i - 1][j - 1] + matrix[i - 1][j - 1];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if(row1 < 0 || col1 < 0 || row2 >= n || col2 >= n) {
                return 0;
            }
            //返回右下角 - 左下角 - 右上角 + 左上角
            return preMatrix[row2 + 1][col2 + 1] - preMatrix[row1][col2 + 1] - preMatrix[row2 + 1][col1] + preMatrix[row1][col1];
        }
    }

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
}
