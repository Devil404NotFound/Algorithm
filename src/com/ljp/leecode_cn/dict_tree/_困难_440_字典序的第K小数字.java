package com.ljp.leecode_cn.dict_tree;

/** 每日一题 2022.03.23
 * @author lijunpeng
 * @date 2022/3/23 23:52
 * @Description 440-字典序的第K小数字
给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。


 

示例 1:

输入: n = 13, k = 2
输出: 10
解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
示例 2:

输入: n = 1, k = 1
输出: 1
 

提示:

1 <= k <= n <= 109

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _困难_440_字典序的第K小数字 {
    public static void main(String[] args) {
        int n = 200;
        int k = 50;
        _困难_440_字典序的第K小数字 test = new _困难_440_字典序的第K小数字();
        int result = test.findKthNumber(n,k);
        System.out.println(result);
    }
    /**
    * @Author lijunpeng
    * @Date 2022/3/24 0:03
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了17.50%的用户
    **/
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }
}
