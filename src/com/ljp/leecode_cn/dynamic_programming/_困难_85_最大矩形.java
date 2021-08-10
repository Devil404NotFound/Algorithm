package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2020.12.26
 * @author ljp
 * @date 2020/12/26 15:45
85. 最大矩形
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。



示例 1：


输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [["0"]]
输出：0
示例 4：

输入：matrix = [["1"]]
输出：1
示例 5：

输入：matrix = [["0","0"]]
输出：0


提示：

rows == matrix.length
cols == matrix[0].length
0 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
 */
public class _困难_85_最大矩形 {
    public static void main(String[] args) {
        _困难_85_最大矩形 test = new _困难_85_最大矩形();
        int[][] matrix = {{1,0,1,0},{1,0,1,1},{1,0,1,1},{1,1,1,1}};
        int result = test.maximalRectangle(matrix);
        System.out.println(result);
    }

    /**
     * 答案错误
     * @param matrix
     * @return
     */
    public int maximalRectangle(int[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dpRow = new int[row + 1][col + 1]; //矩形的最上行
        int[][] dpCol = new int[row + 1][col + 1];//矩形的最左列
        int[][] preRow = new int[row + 1][col + 1]; //上面多少行连续为1
        int[][] preCol = new int[row + 1][col + 1]; //左边多少列连续为1
        int maxArea = 0;
        for(int i = 1; i <= row; ++i) {
            for(int j = 1; j <= col; ++j) {
                if(matrix[i - 1][j - 1] == 1) {
                    preRow[i][j] = preRow[i - 1][j];
                    preCol[i][j] = preCol[i][j - 1];
                    dpRow[i][j] = Math.max(dpRow[i - 1][j - 1], preRow[i - 1][j]);
                    dpCol[i][j] = Math.max(dpCol[i - 1][j - 1], preCol[i][j - 1]);
                    maxArea = Math.max(maxArea, (i - dpRow[i][j]) * (j - dpCol[i][j]));
                }else{
                    preRow[i][j] = i;
                    preCol[i][j] = j;
                    dpRow[i][j] = i;
                    dpCol[i][j] = j;
                }
            }
        }
        return maxArea;
    }

    /**
     * 官方题解一：使用柱状图的优化暴力方法
     * @param matrix
     * @return
    执行用时：
    17 ms, 在所有 Java 提交中击败了26.66%的用户
    内存消耗：
    41.7 MB, 在所有 Java 提交中击败了57.14%的用户
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    width = Math.min(width, left[k][j]);
                    area = Math.max(area, (i - k + 1) * width);
                }
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }
}
