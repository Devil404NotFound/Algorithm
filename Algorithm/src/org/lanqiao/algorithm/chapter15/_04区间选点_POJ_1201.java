package org.lanqiao.algorithm.chapter15;

import java.util.Arrays;
import java.util.Scanner;

/*
Intervals

描述

给出n个封闭的整数间隔[ai，bi]和n个整数c1，...，cn。
编写一个程序，该程序：
从标准输入中读取间隔的数量，它们的端点和整数c1，...，cn，
计算至少具有ci个公共元素且间隔为[ai， bi]，对于每个i = 1,2，...，n，
将答案写入标准输出。
输入值

输入的第一行包含整数n（1 <= n <= 50000）-间隔数。以下n行描述了间隔。输入的第（i + 1）行包含三个整数ai，bi和ci，这些整数之间用单个空格分隔，因此0 <= ai <= bi <= 50000和1 <= ci <= bi-ai + 1。
输出量

对于每个i = 1,2，...，n，输出包含恰好一个整数，该整数等于集合Z的最小大小，该集合最小共享间隔为[ai，bi]的ci个元素。
样本输入

5
3 7 3
8 10 3
6 8 1
1 3 1
10 11 1
样本输出

6
 */
public class _04区间选点_POJ_1201 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Interval[] intervals = new Interval[n];
        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval(sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        int res =greedy(intervals);
        System.out.println(res);
        sc.close();
    }
    //这里为啥用静态？（不用会报错），但是里面的变量不是唯一的，是可以实例化的对象
    static class Interval implements Comparable<Interval>{
        int s;
        int e;
        int i;

        public Interval(int s, int e, int i) {
            this.s = s;
            this.e = e;
            this.i = i;
        }

        @Override
        public int compareTo(Interval o) {
            if(e == o.e){
                return s - o.s;
            }
            return e - o.e;
        }
    }
    public static int greedy(Interval[] intervals){
        //按照intervals类自定义排序（根据结束时间排序）
        Arrays.sort(intervals);
        int res = 0;
        int n = intervals[intervals.length - 1].e;
        int[] mark = new int[n + 1];
        TreeArray treeArray = new TreeArray(new int[n + 1]);
        int s,e,t;
        for (int i = 0; i < intervals.length; i++) {
            s = intervals[i].s;
            e = intervals[i].e;
            t = intervals[i].i;
            t -= (treeArray.getSum(e) - treeArray.getSum(s) + mark[s]);
            while(t > 0){
                if(mark[e] == 0){
                    mark[e] = 1;
                    treeArray.update(e,1);
                    t--;
                }else{
                    e--;
                }
            }
        }
        return treeArray.getSum(n);
    }
    //构造一个树状数组
    static class TreeArray{
        int[] arr;

        public TreeArray(int[] arr) {
            this.arr = arr;
        }

        int lowbit(int i){
            return (i&-i);
        }
        void update(int i, int k){
            while(i < arr.length){
                arr[i] += k;
                i += lowbit(i);
            }
        }
        int getSum(int i){
            int res = 0;
            while(i > 0){
                res += arr[i];
                i -= lowbit(i);
            }
            return res;
        }
    }
}
