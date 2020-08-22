package com.ljp.leecode_cn.math;

/**
 * 9. 回文数
 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

 示例 1:

 输入: 121
 输出: true
 示例 2:

 输入: -121
 输出: false
 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 示例 3:

 输入: 10
 输出: false
 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 进阶:

 你能不将整数转为字符串来解决这个问题吗？
 */
public class _简单_9_回文数 {
    public static void main(String[] args) {
        boolean flag = new _简单_9_回文数().isPalindrome(121);
    }

    /**
     *
     * @param x
     * @return
     * 执行用时：
    12 ms, 在所有 Java 提交中击败了27.48%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了33.44%的用户
     */
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        String s2 = new StringBuilder(s).reverse().toString();
        return s.equals(s2);
    }

    /**
     * 方法二
     * @param x
     * @return
     * 执行用时：
    10 ms, 在所有 Java 提交中击败了64.97%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了38.01%的用户
     */
    public boolean isPalindrome2(int x){
        if(x < 0){
            return false;
        }
        int n = x;
        int num = 0;
        while(n != 0){
            num *= 10;
            num += n % 10;
            n /= 10;
        }
        return x == num;
    }

    /**
     * 官方题解
     * @param x
     * @return
     * 执行用时：
    12 ms, 在所有 Java 提交中击败了27.48%的用户
    内存消耗：
    39.2 MB, 在所有 Java 提交中击败了43.19%的用户
     */
    public boolean isPalindrome3(int x){
        if(x < 0){
            return false;
        }
        if(x == 0){
            return true;
        }
        if(x % 10 == 0){
            //如果末位是0，这里不判断下面会误判为true（10，左边0， 右边1，右边再除10，结果是相等）
            return false;
        }
        int left = x;
        int right = 0;
        while(left > right){
            right *= 10;
            right += left % 10;
            left /= 10;
        }
        //如果是奇数回文数， 例如：12321，left = 12, right=123，此时，需要将right的个位数去掉再比较
        return left == right || left == right / 10;
    }
}
