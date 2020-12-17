package com.ljp.leecode_cn.greedy;

/** 每日一题 2020.12.17
 * 714. 买卖股票的最佳时机含手续费【中等】
 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

 返回获得利润的最大值。

 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

 示例 1:

 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 输出: 8
 解释: 能够达到的最大利润:
 在此处买入 prices[0] = 1
 在此处卖出 prices[3] = 8
 在此处买入 prices[4] = 4
 在此处卖出 prices[5] = 9
 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 注意:

 0 < prices.length <= 50000.
 0 < prices[i] < 50000.
 0 <= fee < 50000.
 */
public class _中等_714_买卖股票的最佳时机含手续费 {
    public static void main(String[] args) {
        int[] prices = {3,10, 8,6,8,12,5,2};
        int fee = 2;
        System.out.println(new _中等_714_买卖股票的最佳时机含手续费().maxProfit(prices,fee));
    }

    /**
     *
     * @param prices
     * @param fee
     * @return
     * 执行用时 :
    6 ms, 在所有 Java 提交中击败了68.75%的用户
    内存消耗 :
    48.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxProfit(int[] prices, int fee) {
        int start = 0;//最低点
        int sum = 0;
        int end = 0;//最高点
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < prices[end] - fee){//遇到下一个比上一个减去手续费还少
                int profit = prices[end] - prices[start] - fee;
                if(profit > 0){
                    sum += profit;
                    start = i;
                }

                end = i;//最高点必须更新
            }else if(prices[i] > prices[end]){
                end = i;
            }
            if(prices[i] < prices[start]){
                start = i;
            }
        }
        sum += Math.max(0, prices[end] - prices[start] - fee);
        return sum;
    }

    /**
     * 评论区，动态规划
     * @param prices
     * @param fee
     * @return
     * 执行用时 :
    6 ms, 在所有 Java 提交中击败了68.75%的用户
    内存消耗 :
    50.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxProfit2(int[] prices, int fee) {
        if(prices.length == 0){
            return 0;
        }
        int[] hold = new int[prices.length];//持有股票
        int[] none = new int[prices.length];//没有买入
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(hold[i - 1], none[i - 1] - prices[i]);
            none[i] = Math.max(none[i - 1], hold[i - 1] + prices[i] - fee);
        }
        return hold[prices.length - 1];
    }

    /**
     * 官方题解二： 贪心
     * @param prices
     * @param fee
     * @return
     * 2020.12.17
    执行用时：
    5 ms, 在所有 Java 提交中击败了64.88%的用户
    内存消耗：
    47.6 MB, 在所有 Java 提交中击败了78.43%的用户
     */
    public int maxProfit3(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; i++) {
            //如果买入价小于之前的买入价，就更新买入价
            if(prices[i] + fee < buy) {
                buy = prices[i] + fee;
            }else if(prices[i] > buy){//如果卖出价格比买入价+手续费高，说明有得赚
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}
