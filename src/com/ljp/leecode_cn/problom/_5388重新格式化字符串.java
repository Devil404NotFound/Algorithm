package com.ljp.leecode_cn.problom;

/**
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。

 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。

 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 示例 1：

 输入：s = "a0b1c2"
 输出："0a1b2c"
 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
 示例 2：

 输入：s = "leetcode"
 输出：""
 解释："leetcode" 中只有字母，所以无法满足重新格式化的条件。
 示例 3：

 输入：s = "1229857369"
 输出：""
 解释："1229857369" 中只有数字，所以无法满足重新格式化的条件。
 示例 4：

 输入：s = "covid2019"
 输出："c2o0v1i9d"
 示例 5：

 输入：s = "ab123"
 输出："1a2b3"


 提示：

 1 <= s.length <= 500
 s 仅由小写英文字母和/或数字组成。
 */
public class _5388重新格式化字符串 {
    public static void main(String[] args) {
        String str = "ab123";
        System.out.println(solve(str));
    }

    public static String solve(String s){
        StringBuilder res = new StringBuilder();
        char[] ch = s.toCharArray();
        char[] ch1 = new char[ch.length];
        char[] ch2 = new char[ch.length];
        int one = 0;
        int two = 0;
        for (int i = 0; i < ch.length; i++) {
            if(ch[i] >= '0' && ch[i] <= '9'){
                ch2[two++] = ch[i];
            }else{
                ch1[one++] = ch[i];
            }
        }
        if(Math.abs(one - two) > 1){
            return "";
        }
        int i = 0;
        if (one >= two){
            while(i < two){
                res.append(ch1[i]).append(ch2[i]);
                i++;
            }
            if(one > two){
                res.append(ch1[i]);
            }
        }else{
            while(i < one){
                res.append(ch2[i]).append(ch1[i]);
                i++;
            }
            res.append(ch2[i]);
        }
        return res.toString();
    }
}
