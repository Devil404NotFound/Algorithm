package org.lanqiao.algorithm.chapter15;

import java.util.Arrays;
import java.util.Scanner;

/*
清洁班
描述

农夫约翰正在分配他的N头（1 <= N <= 25,000）头母牛在谷仓周围做一些清洁工作。他一直想让一头母牛进行清理工作，并将一天分为T个班次（1 <= T <= 1,000,000），第一个是班次1，最后一个是班次T。

每头母牛只能以一定的间隔使用白天进行清洁工作的次数。任何被选定负责清洁工作的母牛将在整个间隔时间内工作。

您的工作是帮助农夫约翰分配一些奶牛到班次，以便（i）每个班次至少分配一头奶牛，并且（ii）尽可能少地母牛参与清洁工作。如果无法为每个班次分配母牛，则打印-1。
输入值

*第1行：两个以空格分隔的整数：N和T

*第2..N + 1行：每行包含母牛可以工作的时间间隔的开始和结束时间。母牛在开始时间开始工作，在结束时间之后结束。
输出量

*第1行：如果无法为每个班次分配一头母牛，农夫约翰需要雇用的最小母牛数或-1。
样本输入

3 10
1 7
3 6
6 10
样本输出

2
暗示

此问题有大量输入数据，请使用scanf（）而不是cin读取数据，以避免超过时间限制。

输入详细信息：

有3头母牛和10个班次。＃1奶牛可以上班1..7，＃2牛可以上班3..6，而＃3牛可以上班6..10。

输出详细信息：

通过选择1号和3号奶牛，所有班次都包括在内。没有办法使用少于2头母牛来覆盖所有班次。
 */
public class _05区间覆盖_POJ_2376 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(sc.nextInt(), sc.nextInt());
        }
        int res = greedy(jobs, t);
        System.out.println(res);
        sc.close();
    }
    public static int greedy(Job[] jobs, int t){
        Arrays.sort(jobs);
        if(jobs == null || jobs.length <= 0 || jobs[0].s > 1){
            return -1;
        }
        int start = 1, end = 1, res = 1; ;
        int s,e;
        for (int i = 0; i < jobs.length; i++) {
            s = jobs[i].s;
            e = jobs[i].e;
            if(s <= start){
                if(end < e){
                    end = e;
                }
            }else{
                start = end + 1;
                if(s <= start){
                    if(end < e){
                        end = e;
                    }
                }else{
                    break;
                }
                res++;
            }
            //找到覆盖全部之后
            if(end >= t){
                return res;
            }
        }
        //无法覆盖时
        return -1;
    }
    static class Job implements Comparable<Job>{
        int s;
        int e;

        public Job(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Job o) {
            return this.s - o.s;
        }
    }
}
