package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2021.08.18
 * @author lijunpeng
 * @date 2021/8/18 22:06
 * @Description
552. 学生出勤记录 II
可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
'A'：Absent，缺勤
'L'：Late，迟到
'P'：Present，到场
如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：

按 总出勤 计，学生缺勤（'A'）严格 少于两天。
学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。



示例 1：

输入：n = 2
输出：8
解释：
有 8 种长度为 2 的记录将被视为可奖励：
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
示例 2：

输入：n = 1
输出：3
示例 3：

输入：n = 10101
输出：183236316


提示：

1 <= n <= 105
 */
public class _困难_552_学生出勤记录II {
    /** 官方题解一：动态规划
    * @Author lijunpeng
    * @Date 2021/8/18 22:28
    * @Description N
    执行用时：
    244 ms, 在所有 Java 提交中击败了26.32%的用户
    内存消耗：
    59.5 MB, 在所有 Java 提交中击败了28.42%的用户
     **/
    public int checkRecord(int n) {
        final int MOD = 1000000007;
        int[][][] dp = new int[n + 1][2][3];// 长度，A 的数量，结尾连续 L 的数量
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            //以P结尾的数量
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            //以A为结尾的数量
            for(int k = 0; k < 3; ++k) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            //以L为结尾的数量
            for (int j = 0; j < 2; j++) {
                for (int k = 1; k < 3; k++) {
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        int sum = 0;
        for(int j = 0; j < 2; ++j) {
            for(int k = 0; k < 3; ++k) {
                sum  = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }

    /** 官方题解二：矩阵快速幂
    * @Author lijunpeng
    * @Date 2021/8/18 22:32
    * @Description logN
    执行用时：
    3 ms, 在所有 Java 提交中击败了99.47%的用户
    内存消耗：
    35.7 MB, 在所有 Java 提交中击败了78.42%的用户
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/student-attendance-record-ii/solution/xue-sheng-chu-qin-ji-lu-ii-by-leetcode-s-kdlm/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    **/
    static final int MOD = 1000000007;

    public int checkRecord2(int n) {
        long[][] mat = {{1, 1, 0, 1, 0, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0}};
        long[][] res = pow(mat, n);
        long sum = Arrays.stream(res[0]).sum();
        return (int) (sum % MOD);
    }

    public long[][] pow(long[][] mat, int n) {
        long[][] ret = {{1, 0, 0, 0, 0, 0}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, mat);
            }
            n >>= 1;
            mat = multiply(mat, mat);
        }
        return ret;
    }

    public long[][] multiply(long[][] a, long[][] b) {
        int rows = a.length, columns = b[0].length, temp = b.length;
        long[][] c = new long[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < temp; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    c[i][j] %= MOD;
                }
            }
        }
        return c;
    }

}
