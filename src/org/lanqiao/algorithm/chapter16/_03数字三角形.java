package org.lanqiao.algorithm.chapter16;

import java.util.Scanner;

/**
 题目描述
 7
 3 8
 8 1 0
 2 7 4 4
 4 5 2 6 5

 如上表示一个5行的数字三角形。假设给定一个n行数字三角形,计算出从三角形顶至底的一条路径，使该路径经过的数字总和最大。
 每一步只能由当前位置向左下或右下。

 输入描述
 你的程序要能接受标准输入。第一行包含一个整数T，表示总的测试次数。
 对于每一种情况：第一行包含一个整数N,其中1 < N < 100,表示三角形的行数。
 接下来的N行输入表示三角形的每一行的元素Ai,j，其中0 < Ai,j < 100。

 输出描述
 输出每次测试的最大值并且占一行。

 样本输入
 1
 5
 7
 3 8
 8 1 0
 2 7 4 4
 4 5 2 6 5

 样本输出
 30
 */
public class _03数字三角形 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[][] tri = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= j; k++) {
                    tri[j][k] = sc.nextInt();
                }
            }
            int[][] help = new int[n][n];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    help[j][k] = -1;
                }
            }
            int res;
            res = maxSum(tri, help, 0, 0);
            System.out.println(res);
            res = maxSum2(tri);
            System.out.println(res);
            res = maxSum3(tri);
            System.out.println(res);
        }
        sc.close();
    }
    //记忆性递归法
    public static int maxSum(int[][] tri, int[][] help, int i, int j){
        if(i == tri.length - 1){
            return tri[i][j];
        }
        if(help[i][j] != -1){
            return help[i][j];
        }
        int res = 0;
        int left = maxSum(tri, help,i + 1, j);
        int right = maxSum(tri, help, i + 1, j + 1);
        if(left >= right){
            res = tri[i][j] + left;
        }else{
            res = tri[i][j] + right;
        }
        help[i][j] = res;
        return res;
    }

    /**
     * 直接用原来的累加
     * @param tri
     * @return
     */
    public static int maxSum2(int[][] tri){
        //这里也可以直接用tri来算，为了不改变原来的数组，这里克隆一个
        int[][] dp = new int[tri.length][tri.length];
        dp[tri.length - 1] = tri[tri.length - 1].clone();
        for (int i = tri.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if(dp[i + 1][j] >= dp[i + 1][j + 1]){
                    dp[i][j] = tri[i][j] + dp[i + 1][j];
                }else{
                    dp[i][j] = tri[i][j] + dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
    //对方法二小改进，用滚动数组实现
    public static int maxSum3(int[][] tri){
        int[] dp = tri[tri.length - 1].clone();
        for (int i = tri.length - 2; i >= 0 ; i--) {
            for (int j = 0; j <= i; j++) {
                if(dp[j] > dp[j + 1]){
                    dp[j] += tri[i][j];
                }else{
                    dp[j] = dp[j + 1] + tri[i][j];
                }
            }
        }
        return dp[0];
    }
}
