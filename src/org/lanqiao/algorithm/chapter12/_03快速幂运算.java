package org.lanqiao.algorithm.chapter12;

public class _03快速幂运算 {
    public static void main(String[] args) {
        System.out.println(power(2,10));
    }

    /**
     * 求幂运算
     * 利用位运算，当幂的第n个二进制位数为1时，乘上a的n次方
     * @param a
     * @param b
     * @return
     */
    public static long power(long a, long b){
        long squar = a;
        long res = 1;
        while(b > 0){
            if((b & 1) == 1){
                res *= squar;
            }
            squar *= squar;
            b >>= 1;
        }
        return res;
    }
}
