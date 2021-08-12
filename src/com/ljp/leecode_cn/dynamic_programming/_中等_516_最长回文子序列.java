package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2021.08.12
 * @author lijunpeng
 * @date 2021/8/12 23:09
 * @Description
516. 最长回文子序列
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。



示例 1：

输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
示例 2：

输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。


提示：

1 <= s.length <= 1000
s 仅由小写英文字母组成
 */
public class _中等_516_最长回文子序列 {
    /**
     * 官方题解：动态规划
     * @param s
     * @return
    执行用时：
    33 ms, 在所有 Java 提交中击败了84.52%的用户
    内存消耗：48.3 MB
    , 在所有 Java 提交中击败了73.60%的用户
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = n - 1; i >= 0; --i) {
            char c1 = s.charAt(i);
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                char c2 = s.charAt(j);
                if(c1 == c2) {//s[i] == s[j]时，同时加上收尾
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else{//s[i] != s[j]时，不可能同时作为同一个回文子序列的首尾
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
