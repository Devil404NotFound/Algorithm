package com.ljp.leecode_cn.bit_manipulation;

/**
 * 342. 4的幂
 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。

 示例 1:

 输入: 16
 输出: true
 示例 2:

 输入: 5
 输出: false
 进阶：
 你能不使用循环或者递归来完成本题吗？

 执行用时 :
 1 ms, 在所有 Java 提交中击败了100.00%的用户
 内存消耗 :
 37.1 MB, 在所有 Java 提交中击败了7.69%的用户
 */
public class _342_4的幂 {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(isPowerOfFour(n));
        System.out.println(isPowerOfFour2(n));
    }

    //我的方法，
    public static boolean isPowerOfFour(int num) {
        if(num <= 0){
            return false;
        }
        int four = 1;
        //溢出后会four=0，因此需要附加four != 0
        while(four < num && four != 0){
            four <<= 2;
        }
        if(four == num){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 不用循环
     * @param num
     * @return
     */
    public static boolean isPowerOfFour2(int num) {
        if(num <= 0 || (num & (num - 1)) != 0){
            return false;
        }
        return (num & 0x55555555) != 0;
    }
}
