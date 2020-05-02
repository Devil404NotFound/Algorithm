package org.lanqiao.algorithm.chapter15;

import java.util.Scanner;

/*
有1元,5元,10元,50元,100元,500元的硬币各c1,c5,c10,c50,c100,c500枚.

现在要用这些硬币来支付A元,最少需要多少枚硬币?

假定本题至少存在一种支付方案.

0≤ci≤10^9

0≤A≤10^9

输入:

第一行有六个数字,分别代表从小到大6种面值的硬币的个数

第二行为A,代表需支付的A元

样例:

输入

    3 2 1 3 0 2
    620

输出

    6

说明:1*500+2*50+1*10+2*5,共6枚
 */
public class _01贪心策略_硬币支付问题 {
    private static int[] coin  = {1, 5, 10, 50, 100, 500};
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 3, 0, 2};
        System.out.println(minCoin(arr.clone(), 1620, 5));
        System.out.println(minCoin2(arr.clone(), 1620));
    }

    //方法一：递归调用，存在问题（1. 可能会栈溢出   2. 当不够钱时结果错误）
    public static int minCoin(int[] arr, int money, int cur){
        //我的方式
        /*//出口
        if(money == 0 || cur < 0){
            return 0;
        }

        if(arr[cur] > 0 && money - coin[cur] >= 0){
            arr[cur]--;
            return 1 + minCoin(arr, money - coin[cur], cur);
        }else{
            return minCoin(arr, money, cur - 1);
        }*/
        //优化后的方式
        if(money == 0){
            return 0;
        }
        if(cur == 0){
            return money;
        }
        int t= money / coin[cur];
        if(t > arr[cur]){
            t = arr[cur];
        }
        return t + minCoin(arr, money - t*coin[cur], cur - 1);

    }
    //方法二：循环调用
    public static int minCoin2(int[] arr, int money){
        int count= 0;
        int cur = arr.length - 1;
        while (cur >= 0) {
            if(arr[cur] > 0 && money - coin[cur] >= 0){
                count++;
                arr[cur]--;
                money -= coin[cur];
                if(money == 0){
                    return count;
                }
            }else{
                cur--;
            }
        }
        return -1;
    }
}
