package com.ljp.leecode_cn.dynamic_programming;

import java.util.Arrays;

/** 每日一题 2021.03.08
 * @author lijunpeng
 * @date 2021/3/8 22:32
132. 分割回文串 II
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

返回符合要求的 最少分割次数 。



示例 1：

输入：s = "aab"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
示例 2：

输入：s = "a"
输出：0
示例 3：

输入：s = "ab"
输出：1


提示：

1 <= s.length <= 2000
s 仅由小写英文字母组成
 */
public class _困难_132_分割回文字符串II {
    /**
     * 官方题解：动态规划
     * @param s
     * @return
    执行用时：
    17 ms, 在所有 Java 提交中击败了42.69%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了47.72%的用户
     */
    public int minCut(String s) {
        int n = s.length();
        //初始化g （g:判断[i, j]是否为回文
        boolean[][] g = new boolean[n][n];
        for(int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }
        //预处理，判断g[i, j]是否为回文
        for(int i = n - 1; i >= 0; --i) {
            for(int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        //动态规划
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i = 0; i < n; ++i) {
            if(g[0][i]) {
                dp[i] = 0;
            }else{
                for(int j = 0; j < i; ++j) {
                    if(g[j +1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
