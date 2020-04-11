package org.lanqiao.algorithm.chapter15;


/*
描述

一群N人希望只乘一条船穿过一条河，最多只能载两个人。因此，必须安排某种穿梭装置以便来回划船，以便所有人都能穿过。每个人都有不同的划船速度；一对夫妇的速度取决于较慢者的速度。您的工作是确定使这些人相处的时间最短的策略。
输入值

输入的第一行包含一个整数T（1 <= T <= 20），即测试用例的数量。然后是T例。每个案例的第一行包含N，第二行包含N个整数，给出每个人过河的时间。每个案例前面都有一个空白行。人数不会超过1000，而且没有人花费超过100秒的时间穿越。
输出量

对于每个测试用例，打印一行，其中包含所有N个人穿过河流所需的总秒数。
样本输入

1个
4
1 2 5 10
样本输出

17

 */
import java.util.Scanner;
public class _02快速过河_POJ_1700 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = sc.nextInt();
            }
            int res = crossingRiver(arr, n);
            System.out.println(res);
        }
    }
    public static int crossingRiver(int[] speed, int n){
        int left = n;//左边剩余的人数
        int res = 0;
        int min;
        if(left <= 0){
            return 0;
        }
        while(left > 3){
            if(speed[1] * 2 <= speed[0] + speed[left - 2]){
                //方案一：1,2过去，1返回，3,4过去，2返回
                min = speed[1] + speed[0] + speed[left - 1] + speed[1];

            }else{
                //方案二：1,3过去，1返回，1，4过去，1返回
                min = 2 * speed[0] + speed[left - 2] + speed[left - 1];
            }
            res += min;
            left -= 2;
        }
        if(left == 1){
            res += speed[0];
        }else if(left == 2){
            res += speed[1];
        }else{
            //这里是等于3时
            res += speed[0] + speed[1] + speed[2];
        }
        return res;
    }
}
