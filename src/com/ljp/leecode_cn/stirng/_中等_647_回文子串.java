package com.ljp.leecode_cn.stirng;

/**
 * 647. 回文子串
 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。



 示例 1：

 输入："abc"
 输出：3
 解释：三个回文子串: "a", "b", "c"
 示例 2：

 输入："aaa"
 输出：6
 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"


 提示：

 输入的字符串长度不会超过 1000 。
 */
public class _中等_647_回文子串 {
    public static void main(String[] args) {
        String s = "caacc";
        System.out.println(new _中等_647_回文子串().countSubstrings(s));
    }

    /**
     *
     * @param s
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.1 MB, 在所有 Java 提交中击败了48.43%的用户
     */
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for(int i = 0; i < chars.length; ++i){
            //回文字符串长度为单数
            int l = i, r = i;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                ++count;
                --l;
                ++r;
            }
            //回文字符串长度为双数
            l = i;
            r = i + 1;
            while(l >= 0 && r < chars.length && chars[l] == chars[r]){
                ++count;
                --l;
                ++r;
            }
        }
        return count;
    }

    /**
     * 下三角动态规划，dp[j]表示从j位置到当前遍历的到的位置i之间是否为回文串
     *
     时间复杂度：O(n^2)。
     空间复杂度：O(1)。
     * @param s
     * @return
     * 执行用时：
    5 ms, 在所有 Java 提交中击败了61.66%的用户
    内存消耗：
    38 MB, 在所有 Java 提交中击败了54.09%的用户
     */
    public int countSubstrings2(String s) {
        char[] chars = s.toCharArray();
        boolean[] dp = new boolean[chars.length];
        int ans = 0;
        for(int i = 0;  i< chars.length; ++i){
            dp[i] = true;
            ++ans;
            for (int j = 0; j < i; ++j) {
                if(chars[i] == chars[j] && dp[j + 1]){
                    ++ans;
                    dp[j] = true;
                }else{
                    dp[j] = false;
                }
            }
        }
        return ans;
    }

    /**
     * 官方题解二：Manacher算法
     * @param s
     * @return
     */
    public int countSubstrings3(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }
}
