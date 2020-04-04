package org.lanqiao.algorithm.chapter14;

import org.lanqiao.Utils.Util;

import java.util.Scanner;

/*
输入正整数n,对1-n进行排列,使得相邻两个数之和均为素数,
输出时从整数1开始,逆时针排列。同一个环应恰好输出一次。
    如输入:
    6
    输出
    1 4 3 2 5 6
    1 6 5 2 3 4
 */
public class _05dfs竞赛题_素数环 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        int[] arr = new int[n];
        arr[0] = 1;
        dfs(arr, 1);
    }
    public static void dfs(int[] arr, int cur){
        //出口
        if(cur == arr.length){
            if(Util.isPrime(arr[cur - 1] + 1)){
                Util.print(arr);
            }
            return;
        }
        //从2到n取一个数
        for (int i = 2; i <= arr.length; i++) {
            //判断是否可以放进去（有没有取过？和前一项相加是不是素数？）
            boolean ok = Util.isPrime(i+arr[cur - 1]);
            //判断是否存在
            for (int j = 0; j < cur; j++) {
                if(arr[j] == i){
                    ok = false;
                }
            }
            if(ok){
                arr[cur] = i;
                dfs(arr, cur+1);
                arr[cur] = 0;
            }
        }

    }

}
