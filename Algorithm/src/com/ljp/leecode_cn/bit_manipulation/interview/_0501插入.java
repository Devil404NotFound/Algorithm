package com.ljp.leecode_cn.bit_manipulation.interview;

/**
 *面试题 05.01. 插入【简单】
 插入。给定两个32位的整数N与M，以及表示比特位置的i与j。编写一种方法，将M插入N，使得M从N的第j位开始，到第i位结束。假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。

 示例1:

 输入：N = 10000000000, M = 10011, i = 2, j = 6
 输出：N = 10001001100
 示例2:

 输入： N = 0, M = 11111, i = 0, j = 4
 输出：N = 11111
 */
public class _0501插入 {
    public static void main(String[] args) {
        int N = 0b1000111100;
        int M = 0b1011;
        int i = 2;
        int j = 6;
        System.out.println(Integer.toBinaryString(insertBits(N, M, i, j)));
        System.out.println(Integer.toBinaryString(insertBits2(N, M, i, j)));
        System.out.println(Integer.toBinaryString(255));
    }
    /**
     *
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int insertBits(int N, int M, int i, int j) {
        int bit = 1;
        for(int k = i; k < j; k++){
            bit <<= 1;
            bit |= 1;
        }
        System.out.println(Integer.toBinaryString(bit));
        bit <<= i;
        System.out.println(Integer.toBinaryString(bit));
        return (N & ~bit) | (M << i);
    }

    /**
     * 小优化
     * @param N
     * @param M
     * @param i
     * @param j
     * @return
     *  执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int insertBits2(int N, int M, int i, int j) {
        //将一个32位二进制数的i-j的位置置为1
        int bit = ((1<<(j - i + 1)) - 1) << i;
        //让N的i-j位全为0，再将M左移到i-j这一段，最后相与
        return (N & ~bit) | (M << i);
    }
}
