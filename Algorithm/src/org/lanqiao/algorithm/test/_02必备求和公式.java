package org.lanqiao.algorithm.test;

/**
 * 一个等差数列∑k= 1+2+3+...+n==============1/2 * n(1+n)
 * 平方和 ∑k^2 = n(n+1)(2n+1)/6
 * 立方和 ∑k^3 = n^2(n+1)^2/4
 * 几何平方∑k = 1 +　ｘ＾２　＋　ｘ^３　+　．．．+　ｘ^ｎ　＝＝＝＝＝＝＝１.　ｘ大于１　ｘ^（ｎ+１）-１　/　ｘ-１　０＜ｘ＜１　　
 */

public class _02必备求和公式 {
    public static void main(String[] args) {

        /*等差数列求和
        测试结果：
        5000000050000000
        0
        5000000050000000
        2914
        int n = 100000000;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
        long  start, end;
        long res = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            res = sum(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            res = sum2(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);*/

        /*平方和
        结果（看不出来）
        674588068435632213
        0
        674588068435632213
        0
        int n = 100000000;
        long  start, end;
        long res = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            res = squareSum(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            res = squareSum(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);*/

        /*立方和
        结果
        250500250000
        0
        250500250000
        656
        int n = 1000;
        long  start, end;
        long res = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            res = cubicSum(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            res = cubicSum2(n);
        }
        end = System.currentTimeMillis();
        System.out.println(res);
        System.out.println(end - start);*/

    }

    /**
     * 求和
     * @param n
     * @return
     */
    public static long sum(int n){
        long res = n;
        res = res*(res + 1) >> 1;
        return res;
    }

    public static long sum2(int n){
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        return res;
    }

    /**
     * 平方和
     * @param n
     * @return
     */
    public static long squareSum(int n){
        long res = n;
        res = res*(res + 1)*(2*res + 2)/6;
        return res;
    }
    public static long squareSum2(int n){
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += i*i;
        }
        return res;
    }

    /**
     * 立方和
     * @param n
     * @return
     */
    public static long cubicSum(int n){
        long res = n;
        res = (res*res*(res+1)*(res+1)) >> 2;
        return res;
    }
    public static long cubicSum2(int n){
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += i*i*i;
        }
        return res;
    }

    public static long square(int n){
        long res = 0;


        return res;
    }


}
