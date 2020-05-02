package org.lanqiao._2020_04_18_校内模拟赛;

import java.util.Scanner;

/**
 * 问题描述
 　　2015年，全中国实现了户户通电。作为一名电力建设者，小明正在帮助一带一路上的国家通电。
 　　这一次，小明要帮助 n 个村庄通电，其中 1 号村庄正好可以建立一个发电站，所发的电足够所有村庄使用。
 　　现在，这 n 个村庄之间都没有电线相连，小明主要要做的是架设电线连接这些村庄，使得所有村庄都直接或间接的与发电站相通。
 　　小明测量了所有村庄的位置（坐标）和高度，如果要连接两个村庄，小明需要花费两个村庄之间的坐标距离加上高度差的平方，形式化描述为坐标为 (x_1, y_1) 高度为 h_1 的村庄与坐标为 (x_2, y_2) 高度为 h_2 的村庄之间连接的费用为
 　　sqrt((x_1-x_2)*(x_1-x_2)+(y_1-y_2)*(y_1-y_2))+(h_1-h_2)*(h_1-h_2)。
 　　在上式中 sqrt 表示取括号内的平方根。请注意括号的位置，高度的计算方式与横纵坐标的计算方式不同。
 　　由于经费有限，请帮助小明计算他至少要花费多少费用才能使这 n 个村庄都通电。
 输入格式
 　　输入的第一行包含一个整数 n ，表示村庄的数量。
 　　接下来 n 行，每个三个整数 x, y, h，分别表示一个村庄的横、纵坐标和高度，其中第一个村庄可以建立发电站。
 输出格式
 　　输出一行，包含一个实数，四舍五入保留 2 位小数，表示答案。
 样例输入
 4
 1 1 3
 9 9 7
 8 8 6
 4 5 4
 样例输出
 17.41
 评测用例规模与约定
 　　对于 30% 的评测用例，1 <= n <= 10；
 　　对于 60% 的评测用例，1 <= n <= 100；
 　　对于所有评测用例，1 <= n <= 1000，0 <= x, y, h <= 10000。

 我的思路：
 1. 根据输入的村庄数据，建立一个每个村庄之间的费用图（连接矩阵）
 2. 遍历已连接的村庄，找到已连接和未连接之间的最小费用，加到总费用中，然后将已连接的村庄之间的费用设置为最大值（flag数组存储已连接的村庄）
 */
public class _09通电费用 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] village = new int[n][3];
        for (int i = 0; i < n; i++) {
            village[i][0] = sc.nextInt();
            village[i][1] = sc.nextInt();
            village[i][2] = sc.nextInt();
        }
        double res = minCost(village);
        System.out.println(String.format("%.2f",res));
        sc.close();
    }
    //做成连通图
    public static double minCost(int[][] village){
        int n = village.length;
        double res = 0;
        double[][] graph = getVillageCost(village);
        int[] flag = new int[n];
        for (int i = 0; i < n; i++) {
            flag[i] = -1;
        }
        flag[0] = 0;
        double min;
        int to = 0, from = 0, j;
        for (int i = 1; i < n; i++) {
            min = Double.MAX_VALUE;
            j = 0;
            while(flag[j] != -1){
                for (int k = 0; k < n; k++) {
                    if(flag[j] != k && graph[j][k] < min){
                        min = graph[flag[j]][k];
                        from = flag[j];
                        to = k;
                    }
                }
                ++j;
            }
            flag[j] = to;
            graph[from][to] = Double.MAX_VALUE;
            res += min;
        }

        return res;
    }
    //计算每个村庄之间的费用
    public static double[][] getVillageCost(int[][] village){
        int n = village.length;
        double[][] graph = new double[n][n];
        int x, y, h;
        double cost;
        for (int i = 0; i < n; i++) {
            x = village[i][0];
            y = village[i][1];
            h = village[i][2];
            for (int j = i + 1; j < n; j++) {
                cost = Math.sqrt((x - village[j][0])*(x - village[j][0]) + (y - village[j][1])*(y - village[j][1])) + (h - village[j][2])*(h - village[j][2]);
                graph[i][j] = cost;
                graph[j][i] = cost;
            }
        }
        return graph;
    }

}
