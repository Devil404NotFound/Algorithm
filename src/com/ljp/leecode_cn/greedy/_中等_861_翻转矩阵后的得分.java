package com.ljp.leecode_cn.greedy;

public class _中等_861_翻转矩阵后的得分 {
    public static void main(String[] args) {
        int[][] A = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        System.out.println(matrixScore(A));
    }

    /**
     *
     * @param A
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    38.1 MB, 在所有 Java 提交中击败了25.00%的用户
     */
    public static int matrixScore(int[][] A) {
        //先处理第一列。必须全部为1
        for(int i = 0; i < A.length; i++){
            if(A[i][0] == 0){
                for(int j = 0; j < A[i].length; j++){
                    A[i][j] ^= 1;
                }
            }
        }
        int sum = A.length << (A[0].length - 1);
        for(int i = 1; i < A[0].length; i++){
            int count = 0;
            for(int j = 0; j < A.length; j++){
                count += A[j][i];
            }
            //0与1哪个多就用哪个
            count = Math.max(count, A.length - count);
            sum += (count << (A[0].length - i - 1));
        }
        return sum;
    }
}
