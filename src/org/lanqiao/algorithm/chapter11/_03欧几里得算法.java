package org.lanqiao.algorithm.chapter11;

/**
 * 欧几里得算法，即辗转相除法
 */
public class _03欧几里得算法 {
    public static void main(String[] args) {
        int m =7;
        int n = 6;
        int res = gcd(m,n);
        System.out.println(res);
    }
    public static int gcd(int m, int n){
        return n==0?m:gcd(n,m%n);
    }
}
