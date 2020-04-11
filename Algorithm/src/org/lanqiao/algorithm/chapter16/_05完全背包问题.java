package org.lanqiao.algorithm.chapter16;

import java.util.Scanner;

/**
 * 有n种重量和价值分别为wi，vi的物品，从这些物品种挑选出总重量不超过W的物品，求所有挑方案中价值总和的最大值，（每种物品可以选无数次）
 * 1≤n≤100
 * 1≤wi，vi≤100
 * 1≤W≤1000
 *
 样本输入
 Input example
 3 10
 1 1
 2 3
 3 4

 样本输出
 Output example
 15
 */

public class _05完全背包问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int W = sc.nextInt();
            int[]w = new int[n];
            int[]v = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                v[i] = sc.nextInt();
            }
            System.out.println(knapsack(w, v, W));
        }
        sc.close();
    }
    public static int knapsack(int[] w, int[] v, int W){
        int[][] arr = new int[w.length][W + 1];
        //处理第一行
        for (int i = w[0]; i <= W; i++) {
            arr[0][i] = v[0];
            if(i - w[0] >= 0){
                arr[0][i] += arr[0][i - w[0]];
            }
        }
        for (int i = 1; i < w.length; i++) {
            for (int j = 1; j <= W; j++) {
                //如果能选择当前行的物品，并且选择的价值比不选择的大
                if(j - w[i] >= 0 && arr[i][j - w[i]] + v[i] > arr[i - 1][j]){
                    arr[i][j] = arr[i][j - w[i]] + v[i];
                }else{
                    arr[i][j] = arr[i - 1][j];      //否则不选择
                }
            }
        }
        return arr[w.length - 1][W];
    }
}
