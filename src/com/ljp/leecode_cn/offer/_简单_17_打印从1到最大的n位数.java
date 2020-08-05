package com.ljp.leecode_cn.offer;
/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

 示例 1:

 输入: n = 1
 输出: [1,2,3,4,5,6,7,8,9]


 说明：

 用返回一个整数列表来代替打印
 n 为正整数
 */
public class _简单_17_打印从1到最大的n位数 {
    /**
     *
     * @param n
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了99.97%的用户
    内存消耗：
    48 MB, 在所有 Java 提交中击败了32.11%的用户
     */
    public int[] printNumbers(int n) {
        int digit = 1;
        for(int i = 0; i < n; i++){
            digit *= 10;
        }
        digit--;
        int[] res = new int[digit];
        for(int i = 0; i < digit; i++){
            res[i] = i + 1;
        }
        return res;
    }
}
