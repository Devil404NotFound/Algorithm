package com.ljp.leecode_cn.math;

/**
 * 258. 各位相加
 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

 示例:

 输入: 38
 输出: 2
 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 进阶:
 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
public class _简单_258_各位相加 {
    /**
     * 方法一：循环
     * @param num
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37 MB, 在所有 Java 提交中击败了52.53%的用户
     */
    public int addDigits(int num) {
        int res;
        while(num > 9){
            res = num;
            num = 0;
            while(res != 0){
                num += res % 10;
                res /= 10;
            }
        }
        return num;
    }
}
