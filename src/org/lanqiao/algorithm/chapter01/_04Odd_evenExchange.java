package org.lanqiao.algorithm.chapter01;

/**
 * 【将整数的二进制奇偶位互换】
 */
public class _04Odd_evenExchange {
    public static void main(String[] args) {
        int n = 9;
        int odd = n & 0x55555555;
        int even = n & 0xaaaaaaaa;
        int result = (odd<<1) ^ (even>>1);
        System.out.println(result);
    }
}
