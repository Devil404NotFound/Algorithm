package com.ljp.leecode_cn.greedy;

import java.util.*;

/**
 * @author ljp
 * @date 2020/12/2 19:24
 *
316. 去除重复字母（困难）
321. 拼接最大数（困难）
402. 移掉 K 位数字（中等）
1081. 不同字符的最小子序列（中等）
316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同



示例 1：

输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"


提示：

1 <= s.length <= 104
s 由小写英文字母组成

 */
public class _中等_316_去除重复字母 {
    /**
     * 官方题解一：贪心 - 一个字符一个字符处理
     * @param s
     * @return
    执行用时：
    12 ms, 在所有 Java 提交中击败了11.21%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了38.96%的用户
     */
    public String removeDuplicateLetters(String s) {
        //统计每个字母的个数
        int[] countChar = new int[26];
        for(char c : s.toCharArray()){
            ++countChar[c - 'a'];
        }
        //找到最小数或者第一个字母的最后一个数
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(pos) > s.charAt(i)){
                pos = i;
            }
            if(--countChar[s.charAt(i) - 'a'] == 0){
                break;
            }
        }
        //递归调用
        return s.length() == 0 ? "" : s.charAt(pos)
                + removeDuplicateLetters(s.substring(pos + 1).replace("" + s.charAt(pos), ""));
    }

    /**官方题解二：贪心 + 单调栈
     *
     * @param s
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了49.34%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了42.84%的用户
     */
    public String removeDuplicateLetters2(String s) {
        Deque<Character> deque = new LinkedList<>(); //单调栈
        Set<Character> seen = new HashSet<>(); //选择了的字母集合
        Map<Character, Integer> lastOccurrence = new HashMap<>(); //每个单词最后出现的位置
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!seen.contains(c)) {
                while(!deque.isEmpty() && deque.peek() > c && lastOccurrence.get(deque.peek()) > i){
                    seen.remove(deque.pop());
                }
                deque.push(c);
                seen.add(c);
            }

        }
        //单调栈转换为字符串
        StringBuilder sb = new StringBuilder(deque.size());
        while(!deque.isEmpty()){
            sb.append(deque.removeLast());
        }
        return sb.toString();
    }
}
