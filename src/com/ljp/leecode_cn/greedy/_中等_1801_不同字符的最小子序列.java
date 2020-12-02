package com.ljp.leecode_cn.greedy;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**单调栈
 类似题目
 316. 去除重复字母（困难）
 321. 拼接最大数（困难）
 402. 移掉 K 位数字（中等）
 1081. 不同字符的最小子序列（中等）
 * @author ljp
 * @date 2020/12/2 18:30
1081. 不同字符的最小子序列
返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。



示例 1：

输入："cdadabcc"
输出："adbc"
示例 2：

输入："abcd"
输出："abcd"
示例 3：

输入："ecbacba"
输出："eacb"
示例 4：

输入："leetcode"
输出："letcod"


提示：

1 <= text.length <= 1000
text 由小写英文字母组成
注意：本题目与 316 https://leetcode-cn.com/problems/remove-duplicate-letters/ 相同
 */
public class _中等_1801_不同字符的最小子序列 {
    /**
     * 贪心 + 单调栈
     * @param s
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了92.39%的用户
    内存消耗：
    36.9 MB, 在所有 Java 提交中击败了39.08%的用户
     */
    public String smallestSubsequence(String s) {
        char[] chars = s.toCharArray();
        int hash = 0; //标记已经入栈的字母哈希值
        int[] hashChar = new int[chars.length]; //记录字母哈希的后缀， hashChar[i] 表示(i, n)中包含的字母
        for(int i = chars.length - 1; i > 0; --i) {
            hashChar[i - 1] = hashChar[i] | (1 << chars[i]);
        }
        //单调栈
        Deque<Character> deque = new LinkedList<>();
        //维护一个单调栈
        for(int i = 0; i < chars.length; ++i) {
            //栈不为空，当前字母不在队列中，栈顶元素比当前字母大，并且后面还有栈顶元素，就出栈
            while(!deque.isEmpty() && ((hash &(1 << chars[i])) == 0) && deque.peek() > chars[i] && (hashChar[i] & (1 << deque.peek())) != 0) {
                hash &= ~(1 << deque.pop());
            }
            //如果栈中没有该字母，就进栈
            if((hash & (1 << chars[i])) == 0) {
                deque.push(chars[i]);
                hash |= 1 << chars[i];
                if(hash == hashChar[0]){//如果单调栈包含所有字母后，这个一定是最小子序列
                    break;
                }
            }
        }
        //栈转成字符串
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }

    /**
     * 对方法一的思路优化（参考316-去除重复字母的官方题解）
     * @param s
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了40.58%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了23.30%的用户
     */
    public String smallestSubsequence2(String s) {
        Deque<Character> deque = new LinkedList<>(); //单调栈
        Set<Character> seen = new HashSet<>(); //记录进栈字母
        int[] lastOccurrence = new int[26]; //记录字母最后出现的下标
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!seen.contains(c)) {
                while(!deque.isEmpty() && deque.peek() > c && lastOccurrence[deque.peek() - 'a'] > i) {
                    seen.remove(deque.pop());
                }
                seen.add(c);
                deque.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(deque.size());
        while(!deque.isEmpty()) {
            sb.append(deque.removeLast());
        }
        return sb.toString();
    }
}
