package org.lanqiao._2020_03_14_校内模拟赛;

import java.util.Scanner;

/**
 * 问题描述
 　　一个正整数如果任何一个数位不大于右边相邻的数位，则称为一个数位递增的数，例如1135是一个数位递增的数，而1024不是一个数位递增的数。
 　　给定正整数 n，请问在整数 1 至 n 中有多少个数位递增的数？
 输入格式
 　　输入的第一行包含一个整数 n。
 输出格式
 　　输出一行包含一个整数，表示答案。
 样例输入
 30
 样例输出
 26
 评测用例规模与约定
 　　对于 40% 的评测用例，1 <= n <= 1000。
 　　对于 80% 的评测用例，1 <= n <= 100000。
 　　对于所有评测用例，1 <= n <= 1000000。
 */
public class _06一个数位递增的数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count = 0;
        for (int i = 1; i <= num; i++) {
            if(judge(i)){
                count++;
            }
        }
        System.out.println(count);
        sc.close();
    }
    public static boolean judge(int num){
        if(num < 10){
            return true;
        }
        boolean flag = true;
        int max = num % 10;
        num /= 10;
        int digt;
        while(num != 0){
            digt = num % 10;
            if(digt > max){
                return false;
            }
            num /= 10;
        }
        return flag;
    }
}
