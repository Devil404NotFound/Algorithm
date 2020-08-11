package com.ljp.leecode_cn.DFS;

/**
 * 130. 被围绕的区域
 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

 示例:

 X X X X
 X O O X
 X X O X
 X O X X
 运行你的函数后，矩阵变为：

 X X X X
 X X X X
 X X X X
 X O X X
 解释:

 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class _中等_130_被围绕的区域 {
    /**
     *
     * @param board
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了98.14%的用户
    内存消耗：
    41.8 MB, 在所有 Java 提交中击败了65.57%的用户
     */
    public void solve(char[][] board) {
        if(board.length == 0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        boolean [][] help = new boolean[m][n];
        for(int i = 0; i < m; i++){
            if(board[i][0] == 'O'){
                dfs(board, help, i, 0);
            }
            if(board[i][n - 1] == 'O'){
                dfs(board, help, i, n - 1);
            }
        }
        for(int i = 1; i < n - 1; i++){
            if(board[0][i] == 'O'){
                dfs(board, help, 0, i);
            }
            if(board[m - 1][i] == 'O'){
                dfs(board, help, m - 1, i);
            }
        }
        for(int i = 1; i < m - 1; i++){
            for(int j = 1; j < n - 1; j++){
                if(!help[i][j] && board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void dfs(char[][]board, boolean[][] help, int i, int j){
        help[i][j] = true;
        //左

        if(j > 0 && !help[i][j - 1] && board[i][j - 1] == 'O'){
            dfs(board, help, i, j - 1);
        }
        //上
        if(i > 0 && !help[i - 1][j] && board[i - 1][j] == 'O'){
            dfs(board, help, i - 1, j);
        }
        //右
        if(j < board[i].length - 2 && !help[i][j + 1] && board[i][j + 1] == 'O'){
            dfs(board, help, i, j + 1);
        }
        //下
        if(i < board.length - 2 && !help[i + 1][j] && board[i + 1][j] == 'O'){
            dfs(board, help, i + 1, j);
        }
    }
}
