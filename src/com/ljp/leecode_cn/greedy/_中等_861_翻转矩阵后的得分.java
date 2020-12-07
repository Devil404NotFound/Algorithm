package com.ljp.leecode_cn.greedy;

/**
 * 每日一题 2020.12.07
 861. 翻转矩阵后的得分
 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

 返回尽可能高的分数。



 示例：

 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 输出：39
 解释：
 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39


 提示：

 1 <= A.length <= 20
 1 <= A[0].length <= 20
 A[i][j] 是 0 或 1
 */
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

    /**
     * 7个月后还没有一开始做的好
     * @param A
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了66.10%的用户
     */
    public int matrixScore2(int[][] A) {
        int m = A.length, n = A[0].length;
        int[] help = new int[m]; //统计哪一行需要翻转
        for(int i = 0; i < m; ++i) {
            if(A[i][0] == 0) {
                reverse(A, i, 0, i + 1, n);
            }
        }
        //将每一列都变成1不比0少
        for(int i = 0; i < n; ++i) {
            if(countCol(A, i) * 2 > m) {
                reverse(A, 0, i, m, i + 1);
            }
        }
        //转换为二进制，累加
        int sum = 0;
        for(int i = 0; i < m; ++i) {
            int current = 0;
            for(int j = 0; j < n; ++j) {
                current |= (A[i][j] << (n - 1 - j));
            }
            sum += current;
        }
        return sum;
    }
    //统计列的0的个数
    private int countCol(int[][] nums, int col) {
        int count = 0;
        for(int i = 0; i < nums.length; ++i) {
            count += (nums[i][col] ^ 1);
        }
        return count;
    }
    //翻转startRow行，startCol列，到endRow行，endCol列
    private void reverse(int[][] nums, int startRow, int startCol, int endRow, int endCol) {
        for(int i = startRow; i < endRow; ++i) {
            for(int j = startCol; j < endCol; ++j) {
                nums[i][j] ^= 1;
            }
        }
    }

    /**
     * 官方题解： 非常优美
     * @param A
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.3 MB, 在所有 Java 提交中击败了68.22%的用户
     */
    public int matrixScore3(int[][] A) {
        int m = A.length, n = A[0].length;
//        int ret = m * (1 << (n - 1));
        int ret = m << (n - 1);
        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if(A[i][0] == 1) {
                    nOnes += A[i][j];
                }else{//如果这一行进行了取反，则该元素的实际取值为1 - A[i][j];
                    nOnes += 1 - A[i][j];
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            ret += k << (n - 1 - j);
        }
        return ret;
    }
}
