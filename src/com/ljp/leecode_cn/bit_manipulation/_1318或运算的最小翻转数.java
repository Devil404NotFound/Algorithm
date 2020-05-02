package com.ljp.leecode_cn.bit_manipulation;

/**
 * 1318. 或运算的最小翻转次数【中等】
 给你三个正整数 a、b 和 c。

 你可以对 a 和 b 的二进制表示进行位翻转操作，返回能够使按位或运算   a OR b == c  成立的最小翻转次数。

 「位翻转操作」是指将一个数的二进制表示任何单个位上的 1 变成 0 或者 0 变成 1 。



 示例 1：



 输入：a = 2, b = 6, c = 5
 输出：3
 解释：翻转后 a = 1 , b = 4 , c = 5 使得 a OR b == c
 示例 2：

 输入：a = 4, b = 2, c = 7
 输出：1
 示例 3：

 输入：a = 1, b = 2, c = 3
 输出：0


 提示：

 1 <= a <= 10^9
 1 <= b <= 10^9
 1 <= c <= 10^9
 */
public class _1318或运算的最小翻转数 {
    public static void main(String[] args) {
        int a = 4;
        int b = 2;
        int c = 7;
        System.out.println(minFlips(a, b, c));
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    36.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int minFlips(int a, int b, int c) {
        int and = a & b;
        int bit = (a | b) ^ c;//bit位数为1就是不同的地方
        int count = 0;
        while(bit != 0){
            if((bit & 1) == 1){
                count++;
                //当a&b在该位都是1时，就需要翻转2次
                if((and & 1) == 1){
                    count++;
                }
            }
            bit >>= 1;
            and >>= 1;
        }
        return count;
    }
}
