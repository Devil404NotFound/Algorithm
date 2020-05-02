package org.lanqiao.algorithm.chapter15;

import java.util.Scanner;

/**
 * 描述

 FJ即将把他的ñ（1≤ ñ ≤2000）奶牛竞争一年一度的“年度农民”。在这场比赛中，每位农民将他们的牛排成一排，并放到评委面前。

 比赛的组织者今年采用了一种新的注册方案：只需按其出现的顺序注册每头母牛的首字母（即，如果FJ以此顺序注册Bessie，Sylvia和Dora，他将注册BSD）。登记阶段结束后，将根据母牛名首字母的字母顺序，按字典顺序对每个组进行判断。

 FJ今年非常忙，必须赶回自己的农场，因此他希望尽早得到评判。他决定在登记之前重新排列已经排队的母牛。

 FJ标志着新的竞争奶牛场的位置。然后，他通过将原始行（其余部分）中的第一头或最后一头母牛重复发送到新行的末尾，将母牛从旧行中调集到新行中。完成后，FJ带着他的母牛在新订单中注册。

 给定他的母牛的初始顺序，确定他可以通过这种方式编写的最小词典字母缩写。

 输入值

 *第1行：一个整数：Ñ
 * 2行.. Ñ 1：行我 1包含单个初始（“A” ..“Z”）的牛在我在原线个位置

 输出量

 他可以制作的最少词典字符串。每行（可能除了最后一行）都在新行中包含80头母牛的首字母（“ A” ..“ Z”）。

 Sample Input

 6
 A
 C
 D
 B
 C
 B
 Sample Output

 ABCBCD
 */
public class _06_贪心策略6_最小字符串_POJ_3617 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            sb.append(sc.next());
        }
        String res = greedy(sb.toString());
        //控制每行80个字符
        int line = res.length() / 80;
        for (int i = 0; i < line; i++) {
            System.out.println(res.substring(i*80, i*80 + 80));
        }
        if(res.length() % 80 != 0){
            System.out.println(res.substring(line*80));
        }
        sc.close();
    }
    public static String greedy(String s){
        StringBuilder res = new StringBuilder();
        String s1 = s;
        String s2 = new StringBuilder(s).reverse().toString();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if(s1.compareTo(s2)<=0){
                res.append(s1.charAt(0));
                s1 = s1.substring(1);
            }else{
                res.append(s2.charAt(0));
                s2 = s2.substring(1);
            }
        }
        return res.toString();
    }
}
