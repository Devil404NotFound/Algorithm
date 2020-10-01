package com.ljp.leecode_cn.dynamic_programming;

/**
 LCP 19. 秋叶收藏集
 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

 示例 1：

 输入：leaves = "rrryyyrryyyrr"

 输出：2

 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

 示例 2：

 输入：leaves = "ryr"

 输出：0

 解释：已符合要求，不需要额外操作

 提示：

 3 <= leaves.length <= 10^5
 leaves 中只包含字符 'r' 和字符 'y'
 通过次数7,888提交次数18,457
 在真实的面试中遇到过这道题？

 */
public class _中等_LCP19_秋叶收藏集 {
    /**
     * 官方题解一：动态规划
     * @param leaves
     * @return
    执行用时：
    93 ms, 在所有 Java 提交中击败了6.51%的用户
    内存消耗：
    48.6 MB, 在所有 Java 提交中击败了6.52%的用户
     */
    public int minimumOperations(String leaves){
        char[] chLeaves = leaves.toCharArray();
        int[][] dp = new int[chLeaves.length][3];
        dp[0][0] = chLeaves[0] == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < chLeaves.length; i++) {
            int red = chLeaves[i] == 'r' ? 1 : 0;
            int yellow = chLeaves[i] == 'y' ? 1 : 0;
            dp[i][0] = dp[i - 1][0] + yellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + red;
            if(i > 1){
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + yellow;
            }
        }
        return dp[chLeaves.length - 1][2];
    }

    /**
     * 官方题解一的优化：用三个变量替换动态规划数组
     * @param leaves
     * @return
    执行用时：
    16 ms, 在所有 Java 提交中击败了97.95%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了95.48%的用户
     */
    public int minimumOperations1(String leaves){
        char[] chLeaves = leaves.toCharArray();
        int one, two, three, red, yellow;
        one = chLeaves[0] == 'y' ? 1 : 0;
        two = three = Integer.MAX_VALUE;
        for (int i = 1; i < chLeaves.length; i++) {
            red = chLeaves[i] == 'r' ? 1 : 0;
            yellow = chLeaves[i] == 'y' ? 1 : 0;
            if(i > 1){
                three = Math.min(two, three) + yellow;
            }
            two = Math.min(one, two) + red;
            one = one + yellow;
        }
        return three;
    }

    /**
     * 官方题解二：前缀和+动态规划，用数学方法优化（没看懂）
     * @param leaves
     * @return
    执行用时：
    18 ms, 在所有 Java 提交中击败了91.78%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了98.37%的用户炫耀一下:
     */
    public int minimunOperations2(String leaves){
        int n = leaves.length();
        int g = leaves.charAt(0) == 'y' ? 1 : -1;
        int gmin = g;
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; ++i) {
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            g += 2 * isYellow - 1;
            if (i != n - 1) {
                ans = Math.min(ans, gmin - g);
            }
            gmin = Math.min(gmin, g);
        }
        return ans + (g + n) / 2;
    }
}
