package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 * 面试题 17.01. 不用加号的加法(简单）
 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。

 示例:

 输入: a = 1, b = 1
 输出: 2


 提示：

 a, b 均可能是负数或 0
 结果不会溢出 32 位整数
 */
public class _1701不用加号的加法 {
    public static void main(String[] args) {
        int n = Integer.MAX_VALUE >> 1;
        for (int i = -10; i < n; i++) {
            for (int j = -100; j <= i; j++) {
                int res1 = i + j;
                int res2 = add(i, j);
                if(res1 != res2){
                    System.out.println(false);
                }
            }
        }
    }
    public static int add(int a, int b){
        int sum;
        while(b != 0){
            sum = (a ^ b);//保留未进位部分
            b = ((a & b) << 1);//保留进位部分
            a = sum;
        }
        sum = a ^ b;
        return sum;
    }
}
