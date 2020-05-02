package com.ljp.leecode_cn.bit_manipulation;

/**
 * 476. 数字的补数【简单】
 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。



 示例 1:

 输入: 5
 输出: 2
 解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 示例 2:

 输入: 1
 输出: 0
 解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。


 注意:

 给定的整数保证在 32 位带符号整数的范围内。
 你可以假定二进制数不包含前导零位。
 本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同

 * 执行用时 :
 0 ms, 在所有 Java 提交中击败了100.00%的用户
 内存消耗 :
 36.2 MB, 在所有 Java 提交中击败了7.69%的用户
 */
public class _476数字的补数 {
    public static void main(String[] args) {
        int num = 5;
        System.out.println(findComplement(num));
    }
    public static int findComplement(int num) {
        num = ~num;
        System.out.println(Integer.toBinaryString(num));
        for(int i = 31; i >=0; i--){
            if((num & (1 << i)) == 0){
                System.out.println(Integer.toBinaryString(num<<(31-i)));
                System.out.println(Integer.toBinaryString(num<<(31-i) >> (31 - i)));
                return ((num << (31 - i)) >>> (31 - i));
            }
        }
        return 0;
    }
}
