package org.lanqiao.algorithm.chapter14;

import org.lanqiao.Utils.Util;

import java.util.Scanner;

/**
 * 一个大小为N*M的园子,兩后积起了水。八连通的积水被认为是连接在一起的。
 * 请求出园子里总共有多少水洼?
 * (连通指的是下图中相对W的的部分)
 *      ***
 *      *W*
 *      ***
 * 限制条件
 *  M，N <= 100
 *
 *  样例
 *      输入：
 *      N = 10， M = 12
W........WW.
.WWW.....WWW
....WW...WW.
.........WW.
.........W..
..W......W..
.W.W.....WW.
W.W.W.....W.
.W.W......W.
..W.......W.
 *
 *      输出：
 *          3
 *
 */
public class _03dfs竞赛题_水洼数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();//读取换行
        char[][] a = new char[n][];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLine().toCharArray();
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(a[i][j] == 'W'){
                    dfs(a, i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
        sc.close();
    }
    public static void dfs(char[][] a, int i, int j){
        a[i][j] = '.';
        //八个方向都搜索一遍
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                if(k == 0 && l ==0){
                    continue;
                }
                int x = i + k;
                int y = j + l;
                if(x >= 0 && x < a.length && y >= 0 && y < a[0].length && a[x][y] == 'W'){
                    dfs(a, x, y);
                }
            }
        }
    }
}
