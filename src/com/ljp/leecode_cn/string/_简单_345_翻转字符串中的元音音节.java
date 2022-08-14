package com.ljp.leecode_cn.string;

import java.util.HashSet;
import java.util.Set;

/** 每日一题 2021.08.19
 * @author lijunpeng
 * @date 2021/8/19 23:29
 * @Description
345. 反转字符串中的元音字母
给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。

元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。



示例 1：

输入：s = "hello"
输出："holle"
示例 2：

输入：s = "leetcode"
输出："leotcede"


提示：

1 <= s.length <= 3 * 105
s 由 可打印的 ASCII 字符组成
 */
public class _简单_345_翻转字符串中的元音音节 {
    /** 双指针
    * @Author lijunpeng
    * @Date 2021/8/19 23:30
    * @Description
    执行用时：
    3 ms, 在所有 Java 提交中击败了83.83%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了54.49%的用户
     **/
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        Set<Character> hash = new HashSet<>();
        hash.add('a');
        hash.add('e');
        hash.add('i');
        hash.add('o');
        hash.add('u');
        hash.add('A');
        hash.add('E');
        hash.add('I');
        hash.add('O');
        hash.add('U');
        int left = 0, right = chars.length - 1;
        while(left < right) {
            while(left < right && !hash.contains(chars[left])) {
                ++left;
            }
            while(left < right && !hash.contains(chars[right])) {
                --right;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            ++left;
            --right;
        }
        return new String(chars);
    }
}
