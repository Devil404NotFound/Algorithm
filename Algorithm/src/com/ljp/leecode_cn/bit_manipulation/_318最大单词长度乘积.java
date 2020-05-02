package com.ljp.leecode_cn.bit_manipulation;

/**
 * 318. 最大单词长度乘积
 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。

 示例 1:

 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 输出: 16
 解释: 这两个单词为 "abcw", "xtfn"。
 示例 2:

 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 输出: 4
 解释: 这两个单词为 "ab", "cd"。
 示例 3:

 输入: ["a","aa","aaa","aaaa"]
 输出: 0
 解释: 不存在这样的两个单词。

 执行用时 :
 12 ms, 在所有 Java 提交中击败了54.05%的用户
 内存消耗 :
 39.3 MB, 在所有 Java 提交中击败了20.00%的用户
 */
public class _318最大单词长度乘积 {
    public static void main(String[] args) {
        String[] words = {"a","aa","aaa","aaaa"};
        System.out.println(maxProduct(words));
    }
    public static int maxProduct(String[] words) {
        int[] hash = new int[words.length];
        for (int i = 0; i < hash.length; i++) {
            int length = words[i].length();
            for (int j = 0; j < length; j++) {
                hash[i] |= 1 << (int)(words[i].charAt(j)-'a');
            }
        }
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if((hash[i] & hash[j]) == 0){
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
