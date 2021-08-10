package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.11.08
 * @author ljp
 * @date 2020/11/8 10:59
 */
public class _简单_122_买卖股票的最佳时机II {
    /**
     * 贪心算法：
     * @param prices
     * @return
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    38.4 MB, 在所有 Java 提交中击败了85.00%的用户
     */
    public int maxProfit(int[] prices){
        int start = 0;
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i - 1] > prices[i]){
                sum += prices[i - 1] - prices[start];
                start = i;
            }
        }
        sum += prices[prices.length - 1] - prices[start];
        return sum;
    }

    /**
     * 官方题解一：动态规划
     * dp[i][0] 表示第i天手里没有股票的最大利润
     * dp[i][1] 表示第i天手里有股票的最大利润
     * @param prices
     * @return
     `执行用时：
    4 ms, 在所有 Java 提交中击败了12.95%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了69.46%的用户
     */
    public int maxProfit2(int[] prices){
        int[][] dp = new int[prices.length][2];
        dp[0][1] = prices[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     *
     * @param prices
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了26.63%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了81.41%的用户
     */
    public int maxProfit3(int[] prices){
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += Math.max(0, prices[i] - prices[i - 1]);
        }
        return sum;
    }
}
