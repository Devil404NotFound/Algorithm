package com.ljp.leecode_cn.math;

/** 每日一题 2022.04.11
 * @author lijunpeng
 * @date 2022/4/11 22:40
 * @description
357. 统计各位数字都不同的数字个数
给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。


示例 1：

输入：n = 2
输出：91
解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
示例 2：

输入：n = 0
输出：1


提示：

0 <= n <= 8
 **/

public class _中等_257_统计各位数字都不同的数字个数 {
    /**
    * @Author lijunpeng
    * @Date 2022/4/11 22:41
    * @Description 官方题解：排列组合
    */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }

    /**
    * @Author lijunpeng
    * @Date 2022/4/11 22:42
    * @Description 评论区题解：动态规划
    */
    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 10;
        for (int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + (dp[i-1] - dp[i-2])*(10-(i-1));
        }
        return dp[n];
    }
}
