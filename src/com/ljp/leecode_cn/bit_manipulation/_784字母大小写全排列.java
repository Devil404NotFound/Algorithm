package com.ljp.leecode_cn.bit_manipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列【简单】
 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。

 示例:
 输入: S = "a1b2"
 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]

 输入: S = "3z4"
 输出: ["3z4", "3Z4"]

 输入: S = "12345"
 输出: ["12345"]
 注意：

 S 的长度不超过12。
 S 仅由数字和字母组成。
 */
public class _784字母大小写全排列 {
    public static void main(String[] args) {
        String s = "abc";
        List<String> list = letterCasePermutation(s);
        System.out.println(list);
    }
    public static List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        letterCasePermutationCore(list, S, new StringBuilder(), 0);
        return list;
    }

    /**
     *
     * @param list
     * @param S
     * @param item
     * @param cur
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了87.65%的用户
    内存消耗 :
    40.3 MB, 在所有 Java 提交中击败了8.33%的用户
     */
    public static void letterCasePermutationCore(List<String> list, String S, StringBuilder item, int cur){
        if(cur == S.length()){
            list.add(item.toString());
            return;
        }
        char c = S.charAt(cur);
        char letter;
        char capt;
        if(c >= 'a' && c <= 'z'){
            letter = c;
            capt = (char)(c - 'a' + 'A');
        }else if(c >= 'A' && c <= 'Z'){
            letter = (char)(c - 'A' + 'a');
            capt = c;
        }else{
            item.append(c);
            letterCasePermutationCore(list, S, item, cur + 1);
            item.deleteCharAt(item.length() - 1);
            return;
        }
        item.append(letter);
        letterCasePermutationCore(list, S, item, cur + 1);
        item.deleteCharAt(item.length() - 1);
        item.append(capt);
        letterCasePermutationCore(list, S, item, cur + 1);
        item.deleteCharAt(item.length() - 1);
        return;
    }

}
