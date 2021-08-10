package com.ljp.leecode_cn.array;

/** 每日一题 2021.02.22
 * @author lijunpeng
 * @date 2021/2/22 18:04
766. 托普利茨矩阵
给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。

如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。



示例 1：


输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
输出：true
解释：
在上述矩阵中, 其对角线为:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
各条对角线上的所有元素均相同, 因此答案是 True 。
示例 2：


输入：matrix = [[1,2],[2,2]]
输出：false
解释：
对角线 "[1, 2]" 上的元素不同。


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 20
0 <= matrix[i][j] <= 99


进阶：

如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 */
public class _简单_766_托普利茨矩阵 {
    /**
     * 暴力算法 （这代码，屎一样）
     * @param matrix
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了8.21%的用户
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //左下角
        for(int i = 0; i < m; ++i) {
            int start = matrix[i][0];
            for(int j = 1; i + j < m && j < n; ++j) {
                if(matrix[i + j][j] != start) {
                    return false;
                }
            }
        }
        //右上角
        for(int j = 1; j < n; ++j) {
            int start = matrix[0][j];
            for(int i = 1; i < m && i + j < n; ++i) {
                if(matrix[i][i + j] != start) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 官方题解：遍历（太优美）
     * @param matrix
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了12.31%的用户
     */
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
