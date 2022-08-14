package com.ljp.leecode_cn.string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 20. 有效的括号
 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 示例 1:

 输入: "()"
 输出: true
 示例 2:

 输入: "()[]{}"
 输出: true
 示例 3:

 输入: "(]"
 输出: false
 示例 4:

 输入: "([)]"
 输出: false
 示例 5:

 输入: "{[]}"
 输出: true
 */
public class _简单_20_有效的括号 {
    /**
     *
     * @param s
     * @return
     * 执行用时：
    3 ms, 在所有 Java 提交中击败了29.42%的用户
    内存消耗：
    37.9 MB, 在所有 Java 提交中击败了30.85%的用户
     */
    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put('(', 0);
        map.put('{', 1);
        map.put('[', 2);
        map.put(')', 3);
        map.put('}', 4);
        map.put(']', 5);
        int i = 0;
        while(i < ch.length){
            if(map.get(ch[i]) < 3){
                deque.push(ch[i]);
            }else if(deque.isEmpty() || map.get(deque.poll()) + 3 != map.get(ch[i])){
                return false;
            }
            i++;
        }
        return deque.isEmpty();
    }
}
