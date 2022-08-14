package com.ljp.leecode_cn.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** 每日一题 2020.12.16
 * @author ljp
 * @date 2020/12/16 0:08
290. 单词规律
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:

输入: pattern = "abba", str = "dog cat cat dog"
输出: true
示例 2:

输入:pattern = "abba", str = "dog cat cat fish"
输出: false
示例 3:

输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
示例 4:

输入: pattern = "abba", str = "dog dog dog dog"
输出: false
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 */
public class _简单_290_单词规律 {
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        _简单_290_单词规律 test = new _简单_290_单词规律();
        boolean ans = test.wordPattern(pattern, s);
        System.out.println(ans);
    }

    /**
     * 哈希表
     * @param pattern
     * @param s
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了98.94%的用户
    内存消耗：
    36.7 MB, 在所有 Java 提交中击败了43.07%的用户
     */
    public boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");
        char[] chars = pattern.toCharArray();
        if(str.length != chars.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();//作一一映射
        Set<String> setValue = new HashSet<>(); //记录已经对应好的字符集（避免多对一的情况）
        for(int i = 0; i < chars.length; ++i) {
            if(!map.containsKey(chars[i])) {//key没有出现过
                if(setValue.contains(str[i])){//如果这个位置的value已经映射了，就返回false
                    return false;
                }
                map.put(chars[i], str[i]);
                setValue.add(str[i]);
            }else{
                String value = map.get(chars[i]);
                if(!value.equals(str[i])) {//如果当前位置的str[i]对应不上chars[i]的value，就返回false
                    return false;
                }
            }
        }
        return true;
    }
}
