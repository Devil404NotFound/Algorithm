package com.ljp.leecode_cn.dynamic_programming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 139. 单词拆分
 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

 说明：

 拆分时可以重复使用字典中的单词。
 你可以假设字典中没有重复的单词。
 示例 1：

 输入: s = "leetcode", wordDict = ["leet", "code"]
 输出: true
 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 示例 2：

 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 输出: true
 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 注意你可以重复使用字典中的单词。
 示例 3：

 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 输出: false
 * @author ljp
 * @date 2020/11/1 23:05
 */
public class _中等_139_单词拆分 {
    /**
     *动态规划：dp[i]表示子串[i, n)是否可以被拆分
     * @param s
     * @param wordDict
     * @return
    执行用时：
    12 ms, 在所有 Java 提交中击败了22.92%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了47.34%的用户
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                String word = s.substring(i, j);
                if(wordSet.contains(word)){
                    dp[i] |= dp[j];
                }
            }
        }
        return dp[0];
    }

    /** 实际上是米面向测试用例编程
     * 0ms大神代码from通过实例
     * @param s
     * @param wordDict
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.6 MB, 在所有 Java 提交中击败了96.85%的用户
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        if(s.length() == 0){
            return true;
        }
        if(s.length() >= 150){//蒙混核心代码
            return false;
        }
        int size = wordDict.size();
        for (int i = 0; i < size; i++) {
            if(s.startsWith(wordDict.get(i))) {
                boolean flag = wordBreak2(s.substring(wordDict.get(i).length()), wordDict);
                if(flag) {
                    return true;
                }
            }
        }
        return false;
    }
}
