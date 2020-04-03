package org.lanqiao.algorithm.chapter13;

/**
 * 有个小孩正在上楼梯,楼梯有n阶台阶,小孩一次可以上ュ阶、2阶、3阶
 * 请实现一个方法,计算小孩有多少种上楼的方式。
 * 为了防止溢出,请将结果Mod10000007
 */
public class _01优化性能走楼梯问题 {
    public static void main(String[] args) {
        System.out.println(recursion(5));
        System.out.println(recursion2(5));
    }

    /**
     * 原来的递归代码
     * @param n
     * @return
     */
    public static int recursion(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0 || n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return recursion(n - 1) + recursion(n - 2) + recursion(n - 3);
    }
    /**
     * 优化后
     * @param n
     * @return
     */
    public static int recursion2(int n){
        if(n < 0){
            return 0;
        }
        if( n == 0 || n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        int x1 = 1;
        int x2 = 2;
        int x3 = 4;
        int x = x3;
        for (int i = 3; i < n; i++) {
            x = x1 + x2 + x3;
            x1 = x2;
            x2 = x3;
            x3 = x;
        }
        return x;
    }

}
