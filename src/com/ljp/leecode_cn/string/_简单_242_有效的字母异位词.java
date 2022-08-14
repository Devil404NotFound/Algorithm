package com.ljp.leecode_cn.string;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2020.11.22
 * @author ljp
 * @date 2020/11/22 9:51
 *
242. 有效的字母异位词
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class _简单_242_有效的字母异位词 {
    /**
     * 哈希表 统计每个字母的个数（只适用于只包含小写字母的写法）
     * @param s
     * @param t
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了48.42%的用户
    内存消耗：
    38.5 MB, 在所有 Java 提交中击败了92.85%的用户
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        int[] hash = new int[26];
        //同时统计s和t字符串
        for(int i = 0; i < s.length(); i++){
            ++hash[s.charAt(i) - 'a'];
            --hash[t.charAt(i) - 'a'];
        }
        for(int i = 0; i < 26; i++) {
            //只要不为0，就说明两个字符串不匹配
            if(hash[i] != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 哈希表 使用map映射统计，适用于有Unicode的字符串
     * @param s
     * @param t
     * @return
    执行用时：
    14 ms, 在所有 Java 提交中击败了21.08%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了14.34%的用户
     */
    public boolean isAnagram2(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        //统计s的字符串
        for(char ch : s.toCharArray()) {
            int num = map.getOrDefault(ch, 0);
            map.put(ch, num + 1);
        }
        //统计t的字符串
        for(char ch : t.toCharArray()) {
            int num = map.getOrDefault(ch, 0);
            if(num == 0){
                return false;
            }
            map.put(ch, num - 1);
        }
        return true;
    }
}
