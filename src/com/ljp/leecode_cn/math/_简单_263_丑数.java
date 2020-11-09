package com.ljp.leecode_cn.math;

/**
 263. 丑数
 编写一个程序判断给定的数是否为丑数。

 丑数就是只包含质因数 2, 3, 5 的正整数。

 示例 1:

 输入: 6
 输出: true
 解释: 6 = 2 × 3
 示例 2:

 输入: 8
 输出: true
 解释: 8 = 2 × 2 × 2
 示例 3:

 输入: 14
 输出: false
 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
 说明：

 1 是丑数。
 输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
 * @author ljp
 * @date 2020/11/3 12:09
 */
public class _简单_263_丑数 {
    /**
     * 题解@Angel
     * @param num
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.8 MB, 在所有 Java 提交中击败了83.34%的用户
     */
    public boolean isUgly(int num) {
        if(num == 0) {
            return false;
        }
        boolean flag = true;
        while(flag && num != 1) {
            flag = false;
            if(num % 2 == 0) {
                num = num >> 1;
                flag = true;
            }else if(num % 3 == 0) {
                num = num / 3;
                flag = true;
            }else if(num % 5 == 0 ) {
                num = num / 5;
                flag = true;
            }
        }
        return num == 1? true : false;
    }
}
