package com.ljp.leecode_cn.string;

import java.util.Deque;
import java.util.LinkedList;

/** 每日一题 2020.11.15
 单调栈
 类似题目
 316. 去除重复字母（困难）
 321. 拼接最大数（困难）
 402. 移掉 K 位数字（中等）
 1081. 不同字符的最小子序列（中等）
 402. 移掉K位数字
 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。

 注意:

 num 的长度小于 10002 且 ≥ k。
 num 不会包含任何前导零。
 示例 1 :

 输入: num = "1432219", k = 3
 输出: "1219"
 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 示例 2 :

 输入: num = "10200", k = 1
 输出: "200"
 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 示例 3 :

 输入: num = "10", k = 2
 输出: "0"
 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * @author ljp
 * @date 2020/11/15 0:15
 */
public class _中等_402_移掉K位数字 {
    public static void main(String[] args) {
        _中等_402_移掉K位数字 test = new _中等_402_移掉K位数字();
        String num = "5438";
        int k = 2;
        String ans = test.removeKdigits2(num, k);
        System.out.println(ans);
    }

    /**答案错误
     * 思路错误：K位数不一定连续
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        int i = 0, start = 0;
        int len = num.length();
        while(i + k < len){
            char left = num.charAt(i);
            char right = num.charAt(i + k);
            if(left - '0' > right - '0'){
                String ans = num.substring(0, i) + num.substring(i + k);
                int j = 0;
                while(j < ans.length() && ans.charAt(j) == '0'){
                    j++;
                }
                return j == ans.length() ? "0" : ans.substring(j);//全是0的情况
            }
            i++;
        }
        return k >= len ? "0" : num.substring(0, i);//考虑位数都是递增的情况
    }

    /**
     * 贪心+单调栈
     * @param num
     * @param k
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了60.67%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了29.63%的用户
     */
    public String removeKdigits2(String num ,int k) {
        if("".equals(num) || k >= num.length()){
            return "0";
        }
        Deque<Character> deque = new LinkedList<>();
        deque.push(num.charAt(0));
        int len = num.length();
        for (int i = 1; i < len; i++) {
            char ch = num.charAt(i);
            while(k > 0 && !deque.isEmpty() && deque.peek() > ch) {
                deque.poll();
                k--;
            }
            if(!deque.isEmpty() || ch != '0'){
                deque.push(ch);
            }
        }
        while(k > 0){
            deque.poll();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.removeLast());
        }
        return "".equals(sb) ? "0" : sb.toString();
    }
}
