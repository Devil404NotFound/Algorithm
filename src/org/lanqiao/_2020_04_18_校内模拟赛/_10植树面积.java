package org.lanqiao._2020_04_18_校内模拟赛;

import java.util.Scanner;

/**
问题描述
　　小明和朋友们一起去郊外植树，他们带了一些在自己实验室精心研究出的小树苗。
　　小明和朋友们一共有 n 个人，他们经过精心挑选，在一块空地上每个人挑选了一个适合植树的位置，总共 n 个。他们准备把自己带的树苗都植下去。
　　然而，他们遇到了一个困难：有的树苗比较大，而有的位置挨太近，导致两棵树植下去后会撞在一起。
　　他们将树看成一个圆，圆心在他们找的位置上。如果两棵树对应的圆相交，这两棵树就不适合同时植下（相切不受影响），称为两棵树冲突。
　　小明和朋友们决定先合计合计，只将其中的一部分树植下去，保证没有互相冲突的树。他们同时希望这些树所能覆盖的面积和（圆面积和）最大。
输入格式
　　输入的第一行包含一个整数 n ，表示人数，即准备植树的位置数。
　　接下来 n 行，每行三个整数 x, y, r，表示一棵树在空地上的横、纵坐标和半径。
输出格式
　　输出一行包含一个整数，表示在不冲突下可以植树的面积和。由于每棵树的面积都是圆周率的整数倍，请输出答案除以圆周率后的值（应当是一个整数）。
样例输入
6
1 1 2
1 4 2
1 7 2
4 1 2
4 4 2
4 7 2
样例输出
12
评测用例规模与约定
　　对于 30% 的评测用例，1 <= n <= 10；
　　对于 60% 的评测用例，1 <= n <= 20；
　　对于所有评测用例，1 <= n <= 30，0 <= x, y <= 1000，1 <= r <= 1000。
 */
public class _10植树面积 {
    static boolean[][] help;
    static boolean[] plant;
    static int res = 0;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] tree = new int[n][3];
        for (int i = 0; i < n; i++) {
            tree[i][0] = sc.nextInt();
            tree[i][1] = sc.nextInt();
            tree[i][2] = sc.nextInt();
        }
        help = new boolean[n][n];
        plant = new boolean[n];
        init(tree);
        treePlanting(tree, 0);
        System.out.println(max);
        sc.close();
    }
    public static void init(int[][] tree){
        for (int i = 0; i < tree.length; i++) {
            for (int j = i + 1; j < tree.length; j++) {
                if(i != j){
                    int length = (tree[i][0] - tree[j][0])*(tree[i][0] - tree[j][0])+(tree[i][1] - tree[j][1])*(tree[i][1] - tree[j][1]);
                    if(length >= (tree[i][2]+tree[j][2])*(tree[i][2]+tree[j][2])){
                        help[i][j] = true;
                        help[j][i] = true;
                    }
                }else{
                    help[i][j] = true;
                }
            }
        }
    }
    public static void treePlanting(int[][] tree, int i){
        if(i == tree.length){
            if(res > max){
                max = res;
            }
            return;
        }
        int area = 0;
        if(isPlant(tree, i)){
            area = tree[i][2]*tree[i][2];
            res += area;
            plant[i] = true;
            treePlanting(tree, i + 1);
            res -= area;
        }
        plant[i] = false;
        treePlanting(tree, i + 1);
    }
    public static boolean isPlant(int[][] tree, int i){
        for (int j = 0; j < tree.length; j++) {
            if(plant[j] && !help[i][j]){
                return false;
            }
        }
        return true;
    }
}
