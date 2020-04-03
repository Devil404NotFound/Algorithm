package org.lanqiao.algorithm.chapter13;

/**
 * 有一个X*Y的网格,一个机器人只能走格点且只能向右或向下走,要从左上角走到右下角。
 * 请设计一个算法,计算机器人有多少种走法。
 * 给定两个正整数intx,inty,请返回机器人的走法数目。保证x+y小于等于12。
 */
public class _02机器人走方格 {
    public static void main(String[] args) {
        System.out.println(solve(9,9));
        System.out.println(solve2(9,9));
    }

    /**
     * 传统递归调用
     * @param x
     * @param y
     * @return
     */
    public static int solve(int x, int y){
        if(x < 1 || y < 1){
            return 0;
        }
        if(x == 1 || y == 1){
            return 1;
        }
        return solve(x - 1, y) + solve(x, y -1);
    }

    /**
     * 递推调用，高性能，不会堆栈溢出
     * @param m
     * @param n
     * @return
     */
    public static int solve2(int m, int n){
        if(m < 1 || n < 1){
            return 0;
        }
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            arr[i][0] = 1;
        }
        for (int i = 0; i < n; i++){
            arr[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = arr[i - 1][j]+ arr[i][j - 1];
            }
        }
        return arr[m - 1][n - 1];
    }
}
