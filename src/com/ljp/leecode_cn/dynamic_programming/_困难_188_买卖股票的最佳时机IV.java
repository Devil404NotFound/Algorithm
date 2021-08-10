package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2020.12.28
 * @author ljp
 * @date 2020/12/28 23:59
188. 买卖股票的最佳时机 IV
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。



示例 1：

输入：k = 2, prices = [2,4,1]
输出：2
解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
示例 2：

输入：k = 2, prices = [3,2,6,5,0,3]
输出：7
解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。


提示：

0 <= k <= 109
0 <= prices.length <= 1000
0 <= prices[i] <= 1000
 */
public class _困难_188_买卖股票的最佳时机IV {
    /**
     * 官方题解一：动态规划
     * @param k
     * @param prices
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了49.70%的用户
    内存消耗：
    38 MB, 在所有 Java 提交中击败了70.74%的用户
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(n == 0) {
            return 0;
        }
        k = Math.min(n >> 1, k); //k的最大值为10^9次方，而买卖次数最多也就n / 2次，再多也没有意义了，因此将k取k与n/2的最小值
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        buy[0][0] = -prices[0];
        for(int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }
        for(int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for(int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }
}
