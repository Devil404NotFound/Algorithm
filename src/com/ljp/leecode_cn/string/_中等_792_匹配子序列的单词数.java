package com.ljp.leecode_cn.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 每日一题 2022.11.17
 * 792. 匹配子序列的单词数
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * <p>
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * <p>
 * 例如， “ace” 是 “abcde” 的子序列。
 * <p>
 * 示例 1:
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * <p>
 * Example 2:
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 * <p>
 * 提示:
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 */
public class _中等_792_匹配子序列的单词数 {
    /**
     * 多指针
     */
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<String>> indexMap = new HashMap<>();
        int count = 0;
        for (int i = 0; i < 26; i++) {
            indexMap.put((char) ('a' + i), new ArrayList<>());
        }
        for (String word : words) {
            indexMap.get(word.charAt(0)).add(word.substring(1));
        }
        for (char ch : s.toCharArray()) {
            List<String> list = indexMap.get(ch);
            if (list == null) {
                continue;
            }
            List<String> nextList = list.stream().filter(str -> str != null && str.length() > 0).collect(Collectors.toList());
            count += list.size() - nextList.size();
            list.clear();
            for (String next : nextList) {
                char chStart = next.charAt(0);
                indexMap.get(chStart).add(next.substring(1));
            }
        }
        return count;
    }

    /**
     * 官方题解二：多指针
     */
    public int numMatchingSubseq2(String s, String[] words) {
        Deque<int[]>[] deques = new Deque[26];
        for (int i = 0; i < 26; i++) {
            deques[i] = new ArrayDeque();
        }
        for (int i = 0; i < words.length; i++) {
            deques[words[i].charAt(0) - 'a'].offer(new int[]{i, 0});
        }
        int res = 0;
        for (char ch : s.toCharArray()) {
            int len = deques[ch - 'a'].size();
            for (int i = 0; i < len; i++) {
                int[] t = deques[ch - 'a'].poll();
                if (t[1] == words[t[0]].length() - 1) {
                    ++res;
                }else {
                    ++t[1];
                    deques[words[t[0]].charAt(t[1]) - 'a'].offer(t);
                }
            }
        }
        return res;
    }

}
