package com.ljp.leecode_cn.DFS;

import java.util.HashSet;
import java.util.Set;

/**每日一题 2020.10.17
 52. N皇后 II
 n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



 上图为 8 皇后问题的一种解法。

 给定一个整数 n，返回 n 皇后不同的解决方案的数量。

 示例:

 输入: 4
 输出: 2
 解释: 4 皇后问题存在如下两个不同的解法。
 [
 [".Q..",  // 解法 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // 解法 2
 "Q...",
 "...Q",
 ".Q.."]
 ]


 提示：

 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 * @author ljp
 * @date 2020/10/17 13:10
 */
public class _困难_52_N皇后II {
    public static void main(String[] args) {
        int n = 4;
        _困难_52_N皇后II thisClass = new _困难_52_N皇后II();
        for (int i = 1; i < 10; i++) {
            int result = thisClass.totalNQueens(9);
            int result2 = thisClass.totalNQueens2(i);
            System.out.println("i:" +i + "---"+ result + "---" + result2);

        }
    }

    /** DFS
     执行用时：
     3 ms, 在所有 Java 提交中击败了31.05%的用户
     内存消耗：
     34.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    int count = 0;
    boolean[][] mask;
    public int totalNQueens(int n) {
        mask = new boolean[n][n];
        dfs(0, n);
        return count;
    }
    private void dfs(int i, int n){
        if(i >= n){
            count++;
            return;
        }
        for(int j = 0; j < n; j++){
            if(select(i, j)){
                mask[i][j] = true;
                dfs(i + 1, n);
                mask[i][j] = false;
            }
        }
    }

    /**
     * 检查i、j这个点能否放下皇后
     * @param i
     * @param j
     * @return
     */
    private boolean select(int i, int j){
        int n = mask.length;
        boolean flag = false;
        for(int k = 0; k < n; k++){
            //上
            if(i - k >= 0){
                flag |= mask[i - k][j];
                if(j - k >= 0){
                    flag |= mask[i - k] [j - k];
                }
                if(j + k < n){
                    flag |= mask[i - k][j + k];
                }
            }
            //下
            if(i + k < n){
                flag |= mask[i + k][j];
                //左下
                if(j - k >= 0){
                    flag |= mask[i + k][j - k];
                }
                //右下
                if(j + k < n){
                    flag |= mask[i + k][j + k];
                }
            }
            if(flag){
                return false;
            }
        }
        return true;
    }

    /**
     * 利用一个数组，下标i为行，值为列
     * @param n
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了81.79%的用户
    内存消耗：
    35.4 MB, 在所有 Java 提交中击败了49.31%的用户
     */
    public int totalNQueens2(int n) {
        return solve(new int[n], n, 0);
    }
    private int solve(int[] arr, int n, int row){
        if(row == n) {
            return 1;
        }
        int count = 0;
        for (int col = 0; col < arr.length; col++) {
            boolean flag = true;
            for (int i = 0; i < row; i++) {
                if(col == arr[i] || row + col == i + arr[i] || row - col == i - arr[i]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                arr[row] = col;
                count += solve(arr, n, row + 1);
            }
        }
        return count;
    }

    /***********************************以下为官方题解***********/

    /**
     * 官方题解1:使用3个集合存不能放的位置
     * @param n
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了35.22%的用户
    内存消耗：
    37.7 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int totalNQueens3(int n) {
        return backtrack(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }
    private int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2){
        if(row == n){
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            int d1 = row - i, d2 = row + i;
            if(columns.contains(i) || diagonals1.contains(d1) || diagonals2.contains(d2)){
                continue;
            }
            columns.add(i);
            diagonals1.add(d1);
            diagonals2.add(d2);
            count += backtrack(n, row + 1,columns, diagonals1, diagonals2);
            columns.remove(i);
            diagonals1.remove(d1);
            diagonals2.remove(d2);
        }
        return count;
    }

    /**
     * 位运算
     利用位运算的两条性质
     1. x & -x  可以得到最低位的1的位置
     2. x & (x -1) 可以将最低位的1变成0
     * @param n
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    35.4 MB, 在所有 Java 提交中击败了52.77%的用户
     */
    public int totalNQueens4(int n) {
        return solve(n, 0, 0, 0, 0);
    }
    private int solve(int n, int row, int columns, int diagonals1, int diagonals2){
        if(row == n){
            return 1;
        }
        int count = 0;
        int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2));//除去高位
        while(availablePositions != 0){
            int position = availablePositions & (-availablePositions);//求最低位1的位置
            availablePositions = availablePositions & (availablePositions - 1);//将最低位1变为0
            count += solve(n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
        }
        return count;
    }
}
