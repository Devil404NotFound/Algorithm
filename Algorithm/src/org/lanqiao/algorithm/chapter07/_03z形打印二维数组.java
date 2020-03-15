package org.lanqiao.algorithm.chapter07;

public class _03z形打印二维数组 {
    /**
     * 输入二维数组
     * matrix = {
     *     {1, 2, 3, 4},
     *     {5, 6, 7, 8},
     *     {9,10,11,12}
     * }
     * 打印1 2 5 9 6 3 4 7 10 11 8 12
     */
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}

        };
        printZ(matrix);

    }
    public static void printZ(int[][] matrix){
        int r = 0, c = 0, lengthR = matrix.length, lengthC = matrix[0].length;
        boolean up = true;    //true为上坡，false为下坡
        while(r < lengthR && c < lengthC){
            System.out.print(matrix[r][c] + " ");
            if(up){
                if(r == 0 && c < lengthC){
                    up = !up;
                    c++;
                }else if(c == lengthC - 1){
                    up = !up;
                    r++;
                }else{
                    r--;
                    c++;
                }
            }else{
                if(c == 0){
                    up = !up;
                    r++;
                }else if(r == lengthR - 1){
                    up = !up;
                    c++;
                }else{
                    r++;
                    c--;
                }
            }
        }

    }
}
