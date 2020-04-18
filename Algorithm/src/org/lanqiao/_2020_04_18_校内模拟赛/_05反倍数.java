package org.lanqiao._2020_04_18_校内模拟赛;

import java.util.Scanner;

/**
 * 问题描述
 　　给定三个整数 a, b, c，如果一个整数既不是 a 的整数倍也不是 b 的整数倍还不是 c 的整数倍，则这个数称为反倍数。
 　　请问在 1 至 n 中有多少个反倍数。
 输入格式
 　　输入的第一行包含一个整数 n。
 　　第二行包含三个整数 a, b, c，相邻两个数之间用一个空格分隔。
 输出格式
 　　输出一行包含一个整数，表示答案。
 样例输入
 30
 2 3 6
 样例输出
 10
 样例说明
 　　以下这些数满足要求：1, 5, 7, 11, 13, 17, 19, 23, 25, 29。
 评测用例规模与约定
 　　对于 40% 的评测用例，1 <= n <= 10000。
 　　对于 80% 的评测用例，1 <= n <= 100000。
 　　对于所有评测用例，1 <= n <= 1000000，1 <= a <= n，1 <= b <= n，1 <= c <= n。
 */
public class _05反倍数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[3];
        arr[0] = sc.nextInt();
        arr[1] = sc.nextInt();
        arr[2] = sc.nextInt();
        int res = sumInverseMult(arr, n);
        System.out.println(res);
        sc.close();
    }
    public static int sumInverseMult(int[] arr, int n){
        int res = 0;
        int[] help = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            int j = arr[i];
            while(j <= n) {
                help[j] = -1;
                j += arr[i];
            }
        }
        for (int i = 1; i < help.length; i++) {
            if(help[i] == 0){
                ++res;
            }
        }
        return res;
    }
}
