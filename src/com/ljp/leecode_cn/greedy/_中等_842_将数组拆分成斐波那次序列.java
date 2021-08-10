package com.ljp.leecode_cn.greedy;

import java.util.ArrayList;
import java.util.List;

/** 每日一题 2020.12.08
 * @author ljp
 * @date 2020/12/8 12:25
842. 将数组拆分成斐波那契序列
给定一个数字字符串 S，比如 S = "123456579"，我们可以将它分成斐波那契式的序列 [123, 456, 579]。

形式上，斐波那契式序列是一个非负整数列表 F，且满足：

0 <= F[i] <= 2^31 - 1，（也就是说，每个整数都符合 32 位有符号整数类型）；
F.length >= 3；
对于所有的0 <= i < F.length - 2，都有 F[i] + F[i+1] = F[i+2] 成立。
另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 0 本身。

返回从 S 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 []。



示例 1：

输入："123456579"
输出：[123,456,579]
示例 2：

输入: "11235813"
输出: [1,1,2,3,5,8,13]
示例 3：

输入: "112358130"
输出: []
解释: 这项任务无法完成。
示例 4：

输入："0123"
输出：[]
解释：每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
示例 5：

输入: "1101111"
输出: [110, 1, 111]
解释: 输出 [11,0,11,11] 也同样被接受。


提示：

1 <= S.length <= 200
字符串 S 中只含有数字。
 */
public class _中等_842_将数组拆分成斐波那次序列 {
    public static void main(String[] args) {
        String s = "5511816597";
        _中等_842_将数组拆分成斐波那次序列 test = new _中等_842_将数组拆分成斐波那次序列();
        List<Integer> ans = test.splitIntoFibonacci(s);
        for(int num : ans) {
            System.out.print(num + " ");
        }
    }

    /**
     * 贪心+递归
     * @param S
     * @return
    执行用时：
    执行用时：
    3 ms, 在所有 Java 提交中击败了89.50%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了81.88%的用户
     */
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        if(!solve(ans, S) || ans.size() < 3) {
            return new ArrayList<>();
        }
        return ans;

    }
    private boolean solve(List<Integer> ans, String S) {
        int size = ans.size();
        int a = size > 1 ? ans.get(size - 2) : 0;
        int b = size > 0 ? ans.get(size - 1) : 0;
        int len = S.length();
        boolean flag = false;
        for (int i = 1; i <= len; i++) {
            if(i > 1 && S.charAt(0) == '0') {
                continue;
            }
            long num = Long.valueOf(S.substring(0, i));
            if(num > Integer.MAX_VALUE) {
                return false;
            }
            int c = (int)num;
            if(size < 2 || num == a + b) {
                ans.add(c);
                if(i == len && size + 1 > 2) {
                    return true;
                }
                flag |= solve(ans, S.substring(i));
                if(flag) {
                    return true;
                }
                ans.remove(size);
            } else if(num > a + b) {
                return false;
            }
        }
        return false;
    }

}
