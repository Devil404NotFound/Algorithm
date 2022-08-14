package com.ljp.leecode_cn.dynamic_programming._中等_926;

/**
 * @author lijunpeng
 * @date 2022/6/11 4:49
 * @description
926. 将字符串翻转到单调递增
如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。

给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。

返回使 s 单调递增的最小翻转次数。



示例 1：

输入：s = "00110"
输出：1
解释：翻转最后一位得到 00111.
示例 2：

输入：s = "010110"
输出：2
解释：翻转得到 011111，或者是 000111。
示例 3：

输入：s = "00011000"
输出：2
解释：翻转得到 00000000。


提示：

1 <= s.length <= 105
s[i] 为 '0' 或 '1'
 **/

public class _将字符串翻转到单调递增 {
    /**
    * @Author lijunpeng
    * @Date 2022/6/11 4:50
    * @Description 前缀和
    */
    public int minFlipsMonoIncr(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int[] zeroSum = new int[n + 1]; // 统计[0,i)区间改为0需要改动的个数
        for(int i = 0; i < n; i++) {
            zeroSum[i + 1] = zeroSum[i] + (charArray[i] - '0');
        }
        int minAns = zeroSum[n];
        for(int i = 0; i < n; i++) {
            // [0,i)改为0，[i+1, n-1]改为1，需要改动的个数
            minAns = Math.min(minAns, zeroSum[i] + (n - i - 1) - (zeroSum[n] - zeroSum[i + 1]));
        }
        return minAns;
    }
    /**
    * @Author lijunpeng
    * @Date 2022/6/11 4:51
    * @Description 官方题解一：前缀和（比我的思路简单）
    */
    public int minFlipsMonoIncr2(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }
    /**
    * @Author lijunpeng
    * @Date 2022/6/11 17:29
    * @Description 动态规划
    */
    public int minFlipsMonoIncr3(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        int[][] dp = new int[n][2]; // dp[i][0]表示0结尾，dp[i][1]表示1结尾
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                dp[i][0] = charArray[0] - '0';
                dp[i][1] = '1' - charArray[0];
            }else {
                dp[i][0] = dp[i - 1][0] + charArray[i] - '0';
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + '1' - charArray[i];
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }
    /**
    * @Author lijunpeng
    * @Date 2022/6/11 17:30
    * @Description 官方题解二：动态规划
    */
    public int minFlipsMonoIncr4(String s) {
        int n = s.length();
        int dp0 = 0, dp1 = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int dp0New = dp0, dp1New = Math.min(dp0, dp1);
            if (c == '1') {
                dp0New++;
            } else {
                dp1New++;
            }
            dp0 = dp0New;
            dp1 = dp1New;
        }
        return Math.min(dp0, dp1);
    }
}
