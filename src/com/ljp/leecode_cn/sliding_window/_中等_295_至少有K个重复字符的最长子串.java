package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.27
 * @author lijunpeng
 * @date 2021/2/27 10:55
395. 至少有K个重复字符的最长子串
给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。



示例 1：

输入：s = "aaabb", k = 3
输出：3
解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2：

输入：s = "ababbc", k = 2
输出：5
解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。


提示：

1 <= s.length <= 104
s 仅由小写英文字母组成
1 <= k <= 105
 */
public class _中等_295_至少有K个重复字符的最长子串 {
    public static void main(String[] args) {
        _中等_295_至少有K个重复字符的最长子串 test = new _中等_295_至少有K个重复字符的最长子串();
        String s = "aaabb";
        int k = 3;
        int result = test.longestSubstring(s, k);
        System.out.println(result);
    }
    /**
     * 官方题解一：分治
     * @param s
     * @param k
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了78.51%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了86.73%的用户
     */
    public int longestSubstring(String s, int k) {
        //调用分治算法
        int n = s.length();
        return dfs(s, 0, n - 1, k);
    }
    //分治算法（深度优先遍历）
    private int dfs(String s, int left, int right, int k) {
        //统计每个字符个数
        int[] cnt = new int[26];
        for(int i = left; i <= right; ++i) {
            cnt[s.charAt(i) - 'a']++;
        }
        //找到第一个个数小于k的字符
        char split = 0;//记录第一个个数小于k的字符
        for(int i = 0; i < 26; ++i) {
            if(cnt[i] > 0 && cnt[i] < k) {
                split = (char)(i + 'a');
                break;
            }
        }
        //如果没有，就直接返回字符长度
        if(split == 0) {
            return right - left + 1;
        }
        int j = left;
        int ret = 0;
        //遍历
        while( j <= right) {
            //跳过开头存在连续split字符的情况
            while(j <= right && s.charAt(j) == split) {
                j++;
            }
            if(j > right) {
                break;
            }
            int start = j;//记录起始位置
            //到下一个split字符位置
            while(j <= right && s.charAt(j) != split) {
                j++;
            }
            //对[start, j)分治调用
            int length = dfs(s, start, j - 1, k);
            //记录最大值
            ret = Math.max(ret, length);
        }
        return ret;
    }
}
