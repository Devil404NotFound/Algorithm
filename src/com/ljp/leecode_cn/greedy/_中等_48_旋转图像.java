package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.12.193
 * @author ljp
 * @date 2020/12/19 13:44
48. 旋转图像
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix =
[
[1,2,3],
[4,5,6],
[7,8,9]
],

原地旋转输入矩阵，使其变为:
[
[7,4,1],
[8,5,2],
[9,6,3]
]
示例 2:

给定 matrix =
[
[ 5, 1, 9,11],
[ 2, 4, 8,10],
[13, 3, 6, 7],
[15,14,12,16]
],

原地旋转输入矩阵，使其变为:
[
[15,13, 2, 5],
[14, 3, 4, 1],
[12, 6, 8, 9],
[16, 7,10,11]
]
 */
public class _中等_48_旋转图像 {
    /**
     *
     * @param matrix
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了91.13%的用户
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] help = new int[n][n];
        for(int i = 0; i < n; ++i) {
            help[i] = matrix[i].clone();
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                matrix[j][n - 1 - i] = help[i][j];
            }
        }
    }
}
