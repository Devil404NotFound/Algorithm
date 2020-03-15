package org.lanqiao.algorithm.chapter03;

/**
 * 高效的求a的n次幂（a的负数次幂没有实现）
 */

public class _06高效的求a的n次幂 {
    public static void main(String[] args) {
        int a = 2;
        int n = 10;
        int res = pwo0(a,n);
        System.out.println(res);
        res = pow(a, n);
        System.out.println(res);
    }
    //原始方法
    public static int pwo0(int a, int n){
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= a;
        }
        return result;
    }
    //高效方法
    public static int pow(int a, int n){
        if( n == 0){
            return 1;
        }
        int res = a;
        int ex = 1;
        while((ex << 1) <= n){
            res *= res;
            ex = ex << 1;
        }
        return res*pow(a, n - ex);
    }

}
