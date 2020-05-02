package com.ljp.leecode_cn.bit_manipulation;

/**
 * 693. 交替位二进制数【简单】
 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。

 示例 1:

 输入: 5
 输出: True
 解释:
 5的二进制数是: 101
 示例 2:

 输入: 7
 输出: False
 解释:
 7的二进制数是: 111
 示例 3:

 输入: 11
 输出: False
 解释:
 11的二进制数是: 1011
 示例 4:

 输入: 10
 输出: True
 解释:
 10的二进制数是: 1010
 */
public class _693_交替位二进制数 {
    public static void main(String[] args) {
        int n = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if(hasAlternatingBits(i)){
                System.out.println(Integer.toBinaryString(i));
            }
            if(hasAlternatingBits(i) != hasAlternatingBits2(i)){
                System.out.println("不相等");
            }
        }
    }

    /**
     *
     * @param n
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    36.6 MB, 在所有 Java 提交中击败了14.29%的用户
     */
    public static boolean hasAlternatingBits(int n) {
        //6就不行
        /*int odd = n & 0x55555555;
        int even = n & 0xaaaaaaaa;
        int temp = (odd << 1) ^ (even >> 1);
        int one = temp ^ n;
        return one != 0 && (one & (one + 1)) == 0;*/
        int test = 1;
        if((n & 1) == 0){
            test = 2;
        }
        while(n != 0){
            if(((n ^ test) & 3) != 0){
                return false;
            }
            n >>= 2;
        }
        return true;
    }

    /**
     * 大佬方法
     * @param n
     * @return
     */
    public static boolean hasAlternatingBits2(int n) {
        int temp=n^(n>>1);
        return (temp&(temp+1))==0;
    }
}
