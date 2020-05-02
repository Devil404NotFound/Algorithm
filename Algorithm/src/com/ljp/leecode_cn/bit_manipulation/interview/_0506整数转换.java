package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 * 面试题 05.06. 整数转换【简单】
 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。

 示例1:

 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 输出：2
 示例2:

 输入：A = 1，B = 2
 输出：2
 提示:

 A，B范围在[-2147483648, 2147483647]之间
 */
public class _0506整数转换 {
    public int convertInteger(int A, int B) {
        int bit = A ^ B;    //找出A，B不同的地方
        int count = 0;
        //计算bit中1的个数
        while(bit != 0){
            count++;
            bit &= bit -1;
        }
        return count;
    }
}
