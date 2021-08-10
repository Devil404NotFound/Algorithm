package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.10
 * @author lijunpeng
 * @date 2021/2/10 21:00
567. 字符串的排列
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").


示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False


注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间
 */
public class _中等_567_字符串的排列 {
    /**
     * 滑动窗口
     * @param s1
     * @param s2
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了97.54%的用户
    内存消耗：
    38.6 MB, 在所有 Java 提交中击败了40.79%的用户
     */
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length(); //滑动窗口大小
        int len = s2.length();
        if(k > len) {
            return false;
        }
        int[] freq = new int[26];
        for(char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        int count = 0; //统计与s1不匹配的字符的个数
        int left = 0, right = 0;
        //初始化窗口
        while(right < k) {
            if((--freq[s2.charAt(right) - 'a']) < 0){
                count++;
            }
            right++;
        }
        if(count == 0) {
            return true;
        }
        //滑动窗口
        while(right < len) {
            if((--freq[s2.charAt(right) - 'a']) < 0){
                count++;
            }
            right++;
            if((freq[s2.charAt(left) - 'a']++) < 0) {
                count--;
                if(count == 0) {
                    return true;
                }
            }
            left++;
        }
        return false;
    }
}
