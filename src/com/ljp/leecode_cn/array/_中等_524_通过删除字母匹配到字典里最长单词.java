package com.ljp.leecode_cn.array;

import java.util.List;

/** 每日一题 2021.09.14
 * @author lijunpeng
 * @date 2021/9/14 23:03
 * @Description
524. 通过删除字母匹配到字典里最长单词
给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。

如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。



示例 1：

输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
输出："apple"
示例 2：

输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"


提示：

1 <= s.length <= 1000
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 1000
s 和 dictionary[i] 仅由小写英文字母组成
 */
public class _中等_524_通过删除字母匹配到字典里最长单词 {
    /** 官方题解：双指针
    * @Author lijunpeng
    * @Date 2021/9/14 23:54
    * @Description
    执行用时：
    20 ms, 在所有 Java 提交中击败了58.99%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了96.24%的用户
    **/
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String t : dictionary) {
            int i = 0, j = 0;
            while (i < t.length() && j < s.length()) {
                if (t.charAt(i) == s.charAt(j)) {
                    ++i;
                }
                ++j;
            }
            if (i == t.length()) {
                if (t.length() > res.length() || (t.length() == res.length() && t.compareTo(res) < 0)) {
                    res = t;
                }
            }
        }
        return res;
    }
}
