package com.ljp.leecode_cn.stirng;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2020.12.27
 * @author ljp
 * @date 2020/12/27 23:57
205. 同构字符串
给定两个字符串 s 和 t，判断它们是否是同构的。

如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。

所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:

输入: s = "egg", t = "add"
输出: true
示例 2:

输入: s = "foo", t = "bar"
输出: false
示例 3:

输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
 */
public class _简单_205_同构字符串 {
    /**
     * 哈希表
     * @param s
     * @param t
     * @return
    执行用时：
    24 ms, 在所有 Java 提交中击败了11.67%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了75.34%的用户
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> mapS = new HashMap<>(); //s->t 映射
        Map<Character, Character> mapT = new HashMap<>(); //t->s 映射
        int len = s.length();
        for(int i = 0; i < len; ++i) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);
            if(!mapS.containsKey(charS) && !mapT.containsKey(charT)) {
                mapS.put(charS, charT);
                mapT.put(charT, charS);
            }else if(mapS.containsKey(charS) && mapT.containsKey(charT)) {
                if(mapS.get(charS) != charT || mapT.get(charT) != charS) {
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }
}
