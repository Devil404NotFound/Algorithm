package org.lanqiao.algorithm.chapter07;

/**
 * 顺时针打印二维数组
 * 输入
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 * 输出
 * 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
 */
public class _01顺时针打印二维数组 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        pirnt(arr);

    }

    public static void pirnt(int[][] arr) {
        int leftUpRow = 0, leftUpCol = 0, rightDownRow = arr.length - 1, rightDownCol = arr[0].length - 1;
        int row = 0, col = -1;
        while (leftUpCol <= rightDownCol) {
            //左-右
            while (col < rightDownCol) {
                System.out.print(arr[row][++col] + " ");
            }
            //重置列（越界了）
            //col = rightDownCol;
            //扫描后行+1
            leftUpRow++;
            //避免重复
            //row++;
            //上-下
            while (row < rightDownRow) {
                System.out.print(arr[++row][col] + " ");
            }
            //row = rightDownRow;
            rightDownCol--;
           // col--;
            //下-左
            while (col > leftUpCol) {
                System.out.print(arr[row][--col] + " ");
            }
            //col = leftUpCol;
            rightDownRow--;
            //row--;
            //左-上
            while (row > leftUpRow) {
                System.out.print(arr[--row][col] + " ");
            }
            //row = leftUpRow;
            leftUpCol++;
            //col++;
        }
    }
}
