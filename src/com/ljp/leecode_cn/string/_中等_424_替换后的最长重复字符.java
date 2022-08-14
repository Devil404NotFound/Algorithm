package com.ljp.leecode_cn.string;

/**每日一题 2021.02.02 【双指针】
 * @author lijunpeng
 * @date 2021/2/2 19:27
424. 替换后的最长重复字符
给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。

注意：字符串长度 和 k 不会超过 104。



示例 1：

输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。
示例 2：

输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class _中等_424_替换后的最长重复字符 {
    /**
     * 暴力算法
     * @param s
     * @param k
     * @return
    执行用时：
    1021 ms, 在所有 Java 提交中击败了5.02%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了76.14%的用户
     */
    public int characterReplacement2(String s, int k) {
        //如果K不小于比s的长度-1，意味着全部都可以替换为同一种字符
        int len = s.length();
        if(k >= len - 1) {
            return len;
        }
        int max = 0;
        int i = 0;
        while(i < len) {
            int cur = 1;
            char c = s.charAt(i);
            for(int j = i + 1; j < len && s.charAt(j) == c; ++j) {
                cur++;
            }
            //cur0 统计右边相同字符连续个数
            int cur0 = cur;
            int curK = k;
            for(int j = i - 1; j >= 0; --j) {
                if(s.charAt(j) == c) {
                    cur++;
                }else if(curK > 0) {
                    cur++;
                    curK--;
                }else {
                    break;
                }
            }
            //如果右边还有字符 len >= cur+curK ，并且能够做替换操作 curK > 0
            if(curK > 0 && len >= cur + curK) {
                cur += curK;
            }
            max = Math.max(max, cur);
            i += cur0;//跳到下一个不同字符位置
        }
        return max;
    }

    /**
     * 官方题解：双指针（滑动窗口）
     * @param s
     * @param k
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了60.54%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了45.87%的用户
     */
    public int characterReplacement(String s, int k) {
        int len = s.length();
        if(len < 2) {
            return len;
        }
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = 0;
        int res = 0;
        int maxCount = 0;
        //统计[left, right)内最多替换k个字符可以得到只有一种字符的子串
        int[] freq = new int[26];
        while(right < len) {
            freq[charArray[right] - 'A']++;
            //在这里维护maxCount
            maxCount = Math.max(maxCount, freq[charArray[right] - 'A']);
            right++;
            if(right - left > maxCount + k) {
                // 说明此时 k 不够用
                // 把其它不是最多出现的字符替换以后，都不能填满这个滑动的窗口，这个时候须要考虑左边界向右移动
                // 移出滑动窗口的时候，频数数组须要相应地做减法
                freq[charArray[left] - 'A']--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
