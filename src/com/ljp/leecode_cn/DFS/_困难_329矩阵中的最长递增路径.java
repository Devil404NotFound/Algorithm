package com.ljp.leecode_cn.DFS;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。

 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

 示例 1:

 输入: nums =
 [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 输出: 4
 解释: 最长递增路径为 [1, 2, 6, 9]。
 示例 2:

 输入: nums =
 [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 输出: 4
 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _困难_329矩阵中的最长递增路径 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int[][] countArr = new int[m][n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dfs(matrix, countArr, m, n, i, j));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int[][] countArr, int m, int n, int row, int col){
        if(countArr[row][col] > 0){
            return countArr[row][col];
        }
        int max = 0, current = matrix[row][col];
        //上
        if(row - 1 >= 0 && matrix[row - 1][col] > current){
            max = Math.max(max, dfs(matrix, countArr, m, n, row - 1, col));
        }
        //左
        if(col - 1 >= 0 && matrix[row][col - 1] > current){
            max = Math.max(max, dfs(matrix, countArr, m, n, row, col - 1));
        }
        //下
        if(row + 1 < m && matrix[row + 1][col] > current){
            max = Math.max(max, dfs(matrix, countArr, m, n, row + 1, col));
        }
        //右
        if(col + 1 < n && matrix[row][col + 1] > current){
            max = Math.max(max, dfs(matrix, countArr, m, n, row, col + 1));
        }
        countArr[row][col] = ++max;//这里需要把自己加进去
        return max;
    }
}
