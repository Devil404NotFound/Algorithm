package org.lanqiao._2020_04_18_校内模拟赛;

import org.lanqiao.Utils.Util;

import java.util.Scanner;

/**
 * 问题描述
 　　如果一个序列的奇数项都比前一项大，偶数项都比前一项小，则称为一个摆动序列。即 a[2i]<a[2i-1], a[2i+1]>a[2i]。
 　　小明想知道，长度为 m，每个数都是 1 到 n 之间的正整数的摆动序列一共有多少个。
 输入格式
 　　输入一行包含两个整数 m，n。
 输出格式
 　　输出一个整数，表示答案。答案可能很大，请输出答案除以10000的余数。
 样例输入
 3 4
 样例输出
 14
 样例说明
 　　以下是符合要求的摆动序列：
 　　2 1 2
 　　2 1 3
 　　2 1 4
 　　3 1 2
 　　3 1 3
 　　3 1 4
 　　3 2 3
 　　3 2 4
 　　4 1 2
 　　4 1 3
 　　4 1 4
 　　4 2 3
 　　4 2 4
 　　4 3 4
 评测用例规模与约定
 　　对于 20% 的评测用例，1 <= n, m <= 5；
 　　对于 50% 的评测用例，1 <= n, m <= 10；
 　　对于 80% 的评测用例，1 <= n, m <= 100；
 　　对于所有评测用例，1 <= n, m <= 1000。
 */
public class _08摆动序列 {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int res = slove(n, m);
        System.out.println(res);
        res = swingSequence(n, m);
        System.out.println(res);
        sc.close();
    }
    public static int slove(int n, int m){
        int res = 0;

        return res;
    }
    //超时
    public static int swingSequence(int n, int m){
        arr = new int[n];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            arr[0] = i;
            res += swingSequenceCore(n, 1, m);
            res %= 10000;
        }
        return res;
    }
    public static int swingSequenceCore(int n, int index, int m){
        int res = 0;
        if(index+1 == n){
            //最后一位奇数
            if((index & 1) == 0){
                res =  m - arr[index-1];
            }else{
                res = arr[index-1] - 1;
            }
            return res;
        }
        //奇数
        if((index & 1) == 0){
            for (int i = arr[index-1] + 1; i < m; i++) {
                arr[index] = i;
                res += swingSequenceCore(n, index+1, m);
            }
        }else{
            for (int i = arr[index-1] - 1; i >= 1 ; i--) {
                arr[index] = i;
                res += swingSequenceCore(n, index + 1, m);
            }
        }
        return res % 10000;
    }
}
