package com.ljp.leecode_cn.stirng;

/** 每日一题 2021.09.25
 * @author lijunpeng
 * @date 2021/9/25 22:42
 * @Description
583. 两个字符串的删除操作
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。



示例：

输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"


提示：

给定单词的长度不超过500。
给定单词中的字符只含有小写字母。
 */
public class _中等_583_两个字符串的删除操作 {

    /**
     * @Author lijunpeng
     * @Date 2021/9/25 22:48
     * @Description 最长公共子序列
     * 执行用时：
     * 7 ms, 在所有 Java 提交中击败了87.72%的用户
     * 内存消耗：
     * 38.7 MB, 在所有 Java 提交中击败了89.52%的用户
     **/
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return len1 + len2 - 2 * dp[len1][len2];
    }

    /**
     * @Author lijunpeng
     * @Date 2021/9/25 22:55
     * @Description 官方题解二：动态规划
    执行用时：
    6 ms, 在所有 Java 提交中击败了94.95%的用户
    内存消耗：
    39.1 MB, 在所有 Java 提交中击败了36.77%的用户
     **/
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; ++j) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
}