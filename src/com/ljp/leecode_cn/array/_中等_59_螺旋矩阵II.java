package com.ljp.leecode_cn.array;

/** 2021.03.16
 * @author lijunpeng
 * @date 2021/3/16 23:30
59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。



示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]


提示：

1 <= n <= 20
 */
public class _中等_59_螺旋矩阵II {
    /**
     * 参考【螺旋矩阵】官方题解2：按层遍历
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.5 MB, 在所有 Java 提交中击败了67.38%的用户
     */
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int left =0;
        int right = n -1;
        int top = 0;
        int bottom = n - 1;
        int index = 1;
        while(left <= right && top <= bottom) {
            for(int col = left; col <= right; ++col) {
                ans[top][col] = index++;
            }
            for(int row = top + 1; row <= bottom; ++row) {
                ans[row][right] = index++;
            }
            if(left < right && top < bottom) {
                for(int col = right - 1; col > left; --col) {
                    ans[bottom][col] = index++;
                }
                for(int row = bottom; row > top; --row) {
                    ans[row][left] = index++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return ans;
    }
}
