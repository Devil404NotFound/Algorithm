package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2021.08.24
 * @author lijunpeng
 * @date 2021/8/24 23:03
 * @Description
787. K 站中转内最便宜的航班
有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。

现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。



示例 1：

输入:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
输出: 200
解释:
城市航班图如下


从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
示例 2：

输入:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
输出: 500
解释:
城市航班图如下


从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。


提示：

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
航班没有重复，且不存在自环
0 <= src, dst, k < n
src != dst
 */
public class _中等_787_K站中转内最便宜的航班 {
    /** 官方题解一：二维数组动态规划
    * @Author lijunpeng
    * @Date 2021/8/24 23:09
    * @Description
    执行用时：
    6 ms, 在所有 Java 提交中击败了80.16%的用户
    内存消耗：
    39.7 MB, 在所有 Java 提交中击败了44.20%的用户
    **/
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] f = new int[k + 2][n];
        int INF = 10000 * 101 + 1;
        for(int i = 0; i <= k + 1; ++i) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;
        for(int t = 1; t <= k + 1; ++t) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 0; t <= k + 1; t++) {
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }

    /**  官方题解：一维数组动态规划
    * @Author lijunpeng
    * @Date 2021/8/24 23:11
    * @Description
    执行用时：
    5 ms, 在所有 Java 提交中击败了88.28%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了68.10%的用户
     **/
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] f = new int[n];
        int INF = 10000 * 101 + 1;
        for(int i = 0; i <= k + 1; ++i) {
            Arrays.fill(f, INF);
        }
        f[src] = 0;
        int ans = INF;
        for(int t = 1; t <= k + 1; ++t) {
            int[] g = new int[n];
            Arrays.fill(g, INF);
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                g[i] = Math.min(g[i], f[j] + cost);
            }
            f = g;
            ans = Math.min(ans, f[dst]);
        }
        return ans == INF ? -1 : ans;
    }
}
