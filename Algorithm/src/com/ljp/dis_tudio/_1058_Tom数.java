package com.ljp.dis_tudio;


/**
 *
 题目描述
 Description
 正整数的各位数字之和被Tom称为Tom数。求输入数（<2^32）的Tom数!

 输入描述
 Input
 每行一个整数(<2^63).

 输出描述
 Output
 每行一个输出,对应该数的各位数之和.

 样本输入
 Input example
 12345
 56123
 82
 样本输出
 Output example
 15
 17
 10
 */
import java.util.Scanner;
public class _1058_Tom数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            long num = sc.nextLong();
            System.out.println(tomNum(num));
        }
        sc.close();
    }
    public static long tomNum(long n){
        long res = 0;
        while(n > 0){
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}