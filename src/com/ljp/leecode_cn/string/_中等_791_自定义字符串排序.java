package com.ljp.leecode_cn.string;

/**
 * 每日一题 2022.11.13
 * 791. 自定义字符串排序
 * 给定两个字符串 order 和 s 。order 的所有单词都是 唯一 的，并且以前按照一些自定义的顺序排序。
 * 对 s 的字符进行置换，使其与排序的 order 相匹配。更具体地说，如果在 order 中的字符 x 出现字符 y 之前，那么在排列后的字符串中， x 也应该出现在 y 之前。
 * 返回 满足这个性质的 s 的任意排列 。
 * <p>
 * 示例 1:
 * 输入: order = "cba", s = "abcd"
 * 输出: "cbad"
 * 解释:
 * “a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
 * 因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
 * 示例 2:
 * <p>
 * 输入: order = "cbafg", s = "abcd"
 * 输出: "cbad"
 * <p>
 * 提示:
 * 1 <= order.length <= 26
 * 1 <= s.length <= 200
 * order 和 s 由小写英文字母组成
 * order 中的所有字符都 不同
 */

public class _中等_791_自定义字符串排序 {
    public String customSortString(String order, String s) {
        int[] dict = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++dict[s.charAt(i) - 'a'];
        }
        StringBuilder sb = new StringBuilder();
        for(char ch : order.toCharArray()) {
            sb.append(repeatChar(ch, dict[ch - 'a']));
            dict[ch - 'a'] = 0;
        }
        for(char i = 0; i < dict.length; ++i) {
            if(dict[i] == 0) {
                continue;
            }
            sb.append(repeatChar((char)(i + 'a'), dict[i]));
        }
        return sb.toString();
    }
    private String repeatChar(char ch, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
