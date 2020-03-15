package org.lanqiao.algorithm.chapter03;
/**
 * 小白走楼梯
 * 小白走楼梯一次可以走1阶，2阶，3阶，
 * 问：n阶的楼梯小白有多少种走法？
 */

import java.util.Scanner;

public class _02Stairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = takeStairs(n);
        System.out.println(result);
        sc.close();
    }

    private static int takeStairs(int n) {
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        return takeStairs(n-1) + takeStairs(n - 2) + takeStairs(n - 3);

    }
}
