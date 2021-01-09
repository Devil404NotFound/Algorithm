package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2021.01.09
 * @author lijunpeng
 * @date 2021/1/9 18:17
123. 买卖股票的最佳时机 III
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。



示例 1:

输入：prices = [3,3,5,0,0,3,1,4]
输出：6
解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2：

输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3：

输入：prices = [7,6,4,3,1]
输出：0
解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
示例 4：

输入：prices = [1]
输出：0


提示：

1 <= prices.length <= 105
0 <= prices[i] <= 105
 */
public class _困难_123_买卖股票的最佳时机III {
    public static void main(String[] args) {
        _困难_123_买卖股票的最佳时机III test = new _困难_123_买卖股票的最佳时机III();
        int[] prices = {1,2,3,4,5};
        int ret =  test.maxProfit(prices);
        System.out.println(ret);
    }

    /**
     * 方法一：动态规划（参考买卖股票的最佳时机IV
     * @param prices
     * @return
    执行用时：
    45 ms, 在所有 Java 提交中击败了43.03%的用户
    内存消耗：
    52.7 MB, 在所有 Java 提交中击败了77.60%的用户
     */
    public int maxProfit(int[] prices) {
        //动态规划
        //sell[i][j] 表示第i天第j次交易，不持有股票的最大利润
        //buy[i][j] 表示第i天第j次交易，持有股票的最大利润
        int n = prices.length;
        int[][] sell = new int[n][3];
        int[][] buy = new int[n][3];
        for(int i = 1; i < 3; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }
        buy[0][1] = -prices[0];
        for(int i = 1; i < n; ++i) {
            for(int j = 1; j < 3; ++j) {
                //不持有：选择前一天不持有，或者前一天持有的+今天卖出去获得的钱
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j] + prices[i]);
                //持有：选择前一天的持有，或者前一天不持有的-今天买进来花的钱
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j - 1] - prices[i]);
            }
        }
        int max = 0;
        for(int i = 0; i < 3; ++i) {//找到从交易0次到交易2次的最大值
            max = Math.max(max, sell[n - 1][i]);
        }
        return  max;
    }

    /**
     * 官方题解：动态规划
     * 分为5个状态
     * 1. 不进行任何操作
     * 2. 进行一次买操作
     * 3. 进行一次买操作和一次卖操作，即完成一笔交易
     * 4. 在完成一次交易的情况下，进行一次买操作
     * 5. 完成2笔交易
        第一个状态的利润显然为0，因此我们可以不用将其记录，对于剩下4个状态，分别记录为buy1， sell1， buy2， sell2
     * @param prices
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了87.74%的用户
    内存消耗：
    54.3 MB, 在所有 Java 提交中击败了65.71%的用户
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for(int i = 0; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]); //进行一次买操作
            sell1 = Math.max(sell1, buy1 + prices[i]); //进行一次卖操作
            buy2 = Math.max(buy2, sell1 - prices[i]); //进行第二次买操作
            sell2 = Math.max(sell2, buy2 + prices[i]); //进行第二次卖操作
        }
        return sell2;
    }
}
