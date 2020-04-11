package org.lanqiao.algorithm.chapter15;

import java.util.Scanner;

public class _06_贪心策略6_最小字符串_POJ_2617 {
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
