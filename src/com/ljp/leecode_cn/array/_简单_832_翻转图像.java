package com.ljp.leecode_cn.array;

/** 每日一题 2021.02.24
 * @author lijunpeng
 * @date 2021/2/24 23:44
832. 翻转图像
给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。

水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。

反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。



示例 1：

输入：[[1,1,0],[1,0,1],[0,0,0]]
输出：[[1,0,0],[0,1,0],[1,1,1]]
解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
示例 2：

输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]


提示：

1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1
 */
public class _简单_832_翻转图像 {

    public static void main(String[] args) {
        _简单_832_翻转图像 test = new _简单_832_翻转图像();
        int[][] A = {{1,1,0}, {1,0,1}, {0, 0, 0}};
        test.flipAndInvertImage(A);
    }

    /**
     *
     * @param A
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了88.09%的用户
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
//        int[][] clone = A.clone(); //问题一：这里copy只能浅复制int[][]数组，里面的int[]还是引用
        int[][] clone = new int[n][n];
        for(int i = 0; i < n; ++i) {
            int left = 0, right = n - 1;
            while(left < right) {
                clone[i][left] = A[i][right] ^ 1;
                clone[i][right] = A[i][left] ^ 1;
                left++;
                right--;
            }
            if(left == right) {
                clone[i][left] = A[i][left] ^ 1;
            }
        }
        return clone;
    }
}
