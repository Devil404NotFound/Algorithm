package com.ljp.leecode_cn.greedy;

/**
 * 738. 单调递增的数字
 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

 （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

 示例 1:

 输入: N = 10
 输出: 9
 示例 2:

 输入: N = 1234
 输出: 1234
 示例 3:

 输入: N = 332
 输出: 299
 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class _中等_738_单调递增的数字 {
    /**
     *
     * @param N
     * @return
     * 执行用时 :
    1 ms, 在所有 Java 提交中击败了99.25%的用户
    内存消耗 :
    36.2 MB, 在所有 Java 提交中击败了33.33%的用户
     */
    public int monotoneIncreasingDigits(int N) {
        int res = N;
        int i = res;
        int digit = 10;//记录第几位
        int last = i % 10;//记录上一位
        i /= 10;
        while (i > 0) {
            if (last < i % 10) {//出现第一次不是递增的情况，向前借位，再将后面的都变成9 eg2332 -> 2329
                res -= res % digit + 1; //eg 2329  2299
                i = res / digit;        //i = 232   229 --- 0
            }
            digit *= 10;
            last = i % 10;
            i /= 10;
        }

        return res;
    }
}
