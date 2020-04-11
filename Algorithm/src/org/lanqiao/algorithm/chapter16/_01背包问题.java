package org.lanqiao.algorithm.chapter16;


/*
有n个重星和价值分别为wi,vi的物品,从这些物品中挑选出总重量不超过W的物品,
求所有挑遊方案中价值总和的最大值。
第一行有两个正整数，第一个为物品数n(n<=1000)，第二个为最大承重量W(W<=10000)。
接下来有n行，每行两个正整数，
分别表示每个物品重量w和价值v（w,v<=1000），
中间以空格隔开。
 */
import java.util.Scanner;
public class _01背包问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int W = sc.nextInt();
            int[] w = new int[n];
            int[] v = new int[n];
            for (int i = 0; i < n; i++) {
                w[i] = sc.nextInt();
                v[i] = sc.nextInt();
            }
            System.out.println(knapsack3(w, v, W));
        }
        sc.close();
    }
    //用二维数组实现
    public static int knapsack(int[] w, int[] v, int W){
        int n = w.length;
        if(n <= 0){
            return -1;
        }
        int[][] arr = new int[n][W + 1];
        for (int i = w[0]; i <= W; i++) {
            arr[0][i] = v[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= W; j++) {
                if (j - w[i] >= 0) {
                    //比较选当前的与不选
                    arr[i][j] = v[i] + arr[i - 1][j - w[i]] > arr[i - 1][j] ? v[i] + arr[i - 1][j - w[i]]:arr[i - 1][j] ;
                } else {
                    arr[i][j] = arr[i - 1][j];
                }
            }
        }
        return arr[n - 1][W];
    }
    public static int knapsack3(int[] w, int[] v, int W){
        int n = w.length;
        int[] arr = new int[W + 1];
        for (int i = w[0]; i <= W; i++) {
            arr[i] = v[0];
        }
        for (int i = 1; i < n ; i++) {
            for (int j = W; j > 0; j--) {
                if(j - w[i] >= 0 && arr[j] < arr[j - w[i]] + v[i]){
                    arr[j] = arr[j - w[i]] + v[i];
                }
            }
        }
        return arr[W];
    }
}
