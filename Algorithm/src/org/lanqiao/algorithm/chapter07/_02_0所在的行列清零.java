package org.lanqiao.algorithm.chapter07;


import org.lanqiao.Utils.Util;

public class _02_0所在的行列清零 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 100},
                {5, 6, 7, 0, 101},
                {9, 0, 11, 12, 102},
                {104, 105, 106, 107, 108}
        };
        Util.print(matrix);
        solve(matrix);
        Util.print(matrix);
    }
    public static void solve(int[][] matrix){
       int[] row = new int[matrix.length];
       int[] col = new int[matrix[0].length];
       for(int i = 0; i < matrix.length; i++){
           for(int j = 0; j < matrix[i].length; j++){
               if(matrix[i][j] == 0){
                   row[i] = 1;
                   col[j] = 1;
               }
           }
       }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(row[i] == 1 || col[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }

}
