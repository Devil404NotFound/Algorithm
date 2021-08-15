package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2021.08.15
 * @author lijunpeng
 * @date 2021/8/15 23:03
 * @Description
给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。

给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。

 

示例 1：


输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
输出：6
示例 2：


输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
输出：12
 

提示：

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/out-of-boundary-paths
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _中等_576_出界的路径数 {
    /** 官方题解：动态规划
    * @Author lijunpeng
    * @Date 2021/8/15 23:17
    * @Description
    执行用时：
    6 ms, 在所有 Java 提交中击败了78.22%的用户
    内存消耗：
    37.5 MB, 在所有 Java 提交中击败了77.61%的用
    **/
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][][] dp = new int[maxMove + 1][m][n]; //移动i次后到达坐标(j, k)的次数
        int outCounts = 0;
        dp[0][startRow][startColumn] = 1; //移动0步到达开始位置次数为1
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[i][j][k];
                    if(count > 0) {
                        for (int[] direction : directions) { //每个方向都走一步
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if(j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dp[i + 1][j1][k1] = (dp[i + 1][j1][k1] + count) % MOD;
                            } else{
                                outCounts = (outCounts + count) % MOD;
                            }
                        }
                    }
                }
            }
        }
        return outCounts;
    }
    /** 官方题解优化：动态规划（降维）
    * @Author lijunpeng
    * @Date 2021/8/15 23:19
    * @Description
    执行用时：
    4 ms, 在所有 Java 提交中击败了92.33%的用户
    内存消耗：
    37.5 MB, 在所有 Java 提交中击败了77.91%的用户
    **/
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] dp = new int[m][n];
        int outCounts = 0;
        dp[startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            int[][] dpNew = new int[m][n];
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    int count = dp[j][k];
                    if(count > 0) {
                        for (int[] direction : directions) {
                            int j1 = j + direction[0], k1 = k + direction[1];
                            if(j1 >= 0 && j1 < m && k1 >= 0 && k1 < n) {
                                dpNew[j1][k1] = (dpNew[j1][k1] + count) % MOD;
                            }else {
                                outCounts = (outCounts + count) % MOD;
                            }
                        }
                    }
                }
            }
            dp = dpNew;
        }
        return outCounts;
    }
}
