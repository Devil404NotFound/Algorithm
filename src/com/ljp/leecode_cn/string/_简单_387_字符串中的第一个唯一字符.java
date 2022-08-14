package com.ljp.leecode_cn.string;

/**
 * @author ljp
 * @date 2020/12/23 0:24
387. 字符串中的第一个唯一字符
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。



示例：

s = "leetcode"
返回 0

s = "loveleetcode"
返回 2


提示：你可以假定该字符串只包含小写字母。
 */
public class _简单_387_字符串中的第一个唯一字符 {
    /**
     * 方法一：统计法
     * @param s
     * @return
    执行用时：
    7 ms, 在所有 Java 提交中击败了82.31%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了79.88%的用户
     */
    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        for(int i = 0; i < s.length(); ++i) {
            ++counts[s.charAt(i) - 'a'];
        }
        for(int i = 0; i < s.length(); ++i) {
            if(counts[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
