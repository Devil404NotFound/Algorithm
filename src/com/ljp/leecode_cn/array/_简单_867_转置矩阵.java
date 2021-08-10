package com.ljp.leecode_cn.array;

/** 每日一题 2021.02.25
 * @author lijunpeng
 * @date 2021/2/25 11:04
867. 转置矩阵
给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。

矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。





示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[[1,4,7],[2,5,8],[3,6,9]]
示例 2：

输入：matrix = [[1,2,3],[4,5,6]]
输出：[[1,4],[2,5],[3,6]]


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
1 <= m * n <= 105
-109 <= matrix[i][j] <= 109
 */
public class _简单_867_转置矩阵 {
    /**
     *
     * @param matrix
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了58.01%的用户
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] ans = new int[n][m];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }
}
