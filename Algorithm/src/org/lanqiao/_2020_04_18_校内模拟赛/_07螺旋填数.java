package org.lanqiao._2020_04_18_校内模拟赛;

import org.lanqiao.Utils.Util;

import java.util.Scanner;

/**
 * 问题描述
 　　对于一个 n 行 m 列的表格，我们可以使用螺旋的方式给表格依次填上正整数，我们称填好的表格为一个螺旋矩阵。
 　　例如，一个 4 行 5 列的螺旋矩阵如下：
 　　1 2 3 4 5
 　　14 15 16 17 6
 　　13 20 19 18 7
 　　12 11 10 9 8
 输入格式
 　　输入的第一行包含两个整数 n, m，分别表示螺旋矩阵的行数和列数。
 　　第二行包含两个整数 r, c，表示要求的行号和列号。
 输出格式
 　　输出一个整数，表示螺旋矩阵中第 r 行第 c 列的元素的值。
 样例输入
 4 5
 2 2
 样例输出
 15
 评测用例规模与约定
 　　对于 30% 的评测用例，2 <= n, m <= 20。
 　　对于 70% 的评测用例，2 <= n, m <= 100。
 　　对于所有评测用例，2 <= n, m <= 1000，1 <= r <= n，1 <= c <= m。
 */
public class _07螺旋填数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int res = mat(n, m, r, c);
        System.out.println(res);
        sc.close();
    }
    public static int mat(int n, int m, int r, int c){
        int[][] arr = new int[n][m];
        int leftUpRow = 0;
        int leftUpCol = 0;
        int rightDownRow = n - 1;
        int rightDownCol = m - 1;
        int i = 0, j = -1, num = 0;
        while(leftUpCol <= rightDownCol){
            //左上-右上
            while(j < rightDownCol){
                arr[i][++j] = ++num;
            }
            ++leftUpRow;
            //右上-右下
            while(i < rightDownRow){
                arr[++i][j] = ++num;
            }
            --rightDownCol;
            //右下-左下
            while(j > leftUpCol){
                arr[i][--j] = ++num;
            }
            --rightDownRow;
            //左下-左上
            while(i > leftUpRow){
                arr[--i][j] = ++num;
            }
            ++leftUpCol;
        }
        Util.print(arr);
        return arr[r - 1][c - 1];
    }
}
