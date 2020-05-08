package com.ljp.leecode_cn.dynamic_programming;

/**
 * 221. 最大正方形
 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

 示例:

 输入:

 1 0 1 0 0
 1 0 1 1 1
 1 1 1 1 1
 1 0 0 1 0

 输出: 4
 */
public class _中等_221_最大正方形 {
    public static void main(String[] args) {
        char[][] matrix = {{'0','0','1','0'},{'1','1','1','1'},{'1','1','1','1'},{'1','1','1','0'},{'1','1','0','0'},{'1','1','1','1'},{'1','1','1','0'}};
        System.out.println(maximalSquare2(matrix));
    }

    /**
     *
     * @param matrix
     * @return
     * 执行用时 :
    6 ms, 在所有 Java 提交中击败了87.12%的用户
    内存消耗 :
    42.6 MB, 在所有 Java 提交中击败了68.75%的用户
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        int[][] help = new int[rowLength][colLength];
        int edge = 0;
        for (int i = 0; i < rowLength; i++) {
            help[i][colLength - 1] = matrix[i][colLength - 1] - '0';
            edge += help[i][colLength - 1];
        }
        for (int i = 0; i < colLength; i++) {
            help[rowLength - 1][i] = matrix[rowLength - 1][i] - '0';
            edge += help[rowLength - 1][i];
        }
        edge = Math.min(1, edge);
        for (int i = help.length - 2; i >= 0; i--) {
            for (int j = help[i].length - 2; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    int row = help[i][j + 1];
                    int col = help[i + 1][j];
                    int min = Math.min(row, col);
                    if (matrix[i][j] == matrix[i + min][j + min]) {
                        help[i][j] = min + 1;
                        edge = Math.max(edge, help[i][j]);
                    } else {
                        help[i][j] = min;
                    }
                }
            }
        }
        return edge * edge;
    }

    /**
     * 评论区的大佬，动态规划
     * @param matrix
     * @return
     * 执行用时 :
    7 ms, 在所有 Java 提交中击败了46.26%的用户
    内存消耗 :
    42.9 MB 在所有 Java 提交中击败了50.00%的用户
     */
    public static int maximalSquare2(char[][] matrix) {
        //判断矩阵是否为空
        if(matrix.length == 0){
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];//重点：dp[1][1]映射的是matrix[0][0]到左上的矩阵最大正方形
        int max = 0;
        //当该位置的值为1时，只需要找到左边，上边，左上当中最小的，再加1
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if(matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
