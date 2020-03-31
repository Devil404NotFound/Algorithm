package org.lanqiao.algorithm.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 天平称重：变种3进制
 * 用天平称重时，我们希望用尽可能少的砝码组合称出尽可能多的重量
 * 如果有无限个砝码，但它们的重量分别是1,3,9,27,81，......等3的指数幂，砝码允许放在左右两个盘中
 * 本题目要求编程实现：对用户给定的重量，给出砝码组合方案，重量<1000000。
 * 例如：
 * 用户输入：
 * 5
 * 程序输出
 * 9-3-1
 * 用户输入
 * 19
 * 程序输出
 * 27-9+1
 */
public class _01巧用进制解决天平称重问题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String result = weigh(n);
        System.out.println(result);
        sc.close();
    }

    public static String weigh(int n){
        if(n <= 0){
            return "输入错误！";
        }
        char[] bin3 = Integer.toString(n, 3).toCharArray();
        int digit = 1;
        List<Integer> list = new ArrayList<>();
        int i = bin3.length - 1;
        while(i >= 0){
            if(bin3[i] == '2'){
                list.add(0, -digit);
                if(i > 0){
                    ++bin3[i - 1];
                }else{
                    list.add(0, 3*digit);
                }
            }else if(bin3[i] == '3'){
//                list.add(0, 0);
                if(i > 0){
                    ++bin3[i - 1];
                }else{
                    list.add(0, 3*digit);
                }
            }else if (bin3[i] == '1'){
                list.add(0, digit);
            }else{
//                list.add(0,0);
            }
            digit *= 3;
            --i;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int j = 1; j < list.size(); j++) {
            i = list.get(j);
            if(i >= 0){
                sb.append("+").append(i);
            }else{
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
