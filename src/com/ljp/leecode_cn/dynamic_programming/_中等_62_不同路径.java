package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2020.12.09
 * @author ljp
 * @date 2020/12/9 13:35
62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？



示例 1：


输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6


提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 109
 */
public class _中等_62_不同路径 {
    /**
     * 动态规划
     * @param m
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.3 MB, 在所有 Java 提交中击败了71.93%的用户
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(j != 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }else{
                    dp[i][j] = 1;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 官方题解二：数学方法 排列组合
     总共需要走m + n - 2步，而向下需要走 m - 1步，因此问题变成了从m + n - 2 种选择m-1个，有多少种方法
     * @param m
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.1 MB, 在所有 Java 提交中击败了88.60%的用户
     */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; x++, y++) {
            ans = ans * x / y;
        }
        return (int)ans;
    }
}
