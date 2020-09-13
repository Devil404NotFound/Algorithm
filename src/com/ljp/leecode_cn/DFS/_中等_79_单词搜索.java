package com.ljp.leecode_cn.DFS;

/**
 79. 单词搜索
 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



 示例:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 给定 word = "ABCCED", 返回 true
 给定 word = "SEE", 返回 true
 给定 word = "ABCB", 返回 false
 */
public class _中等_79_单词搜索 {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public boolean exist(char[][] board, String word) {
        if("".equals(word)){
            return true;
        }
        char[] chars = word.toCharArray();
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[i].length; ++j){
                if(board[i][j] == chars[0]){
                    if(dfs(board, i, j, chars, 0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, int i, int j, char[] chars, int index){
        if(index == chars.length){
            return true;
        }
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
            return false;
        }
        if(board[i][j] == chars[index]){
            //标记当前元素为遍历过
            char current = board[i][j];
            board[i][j] = '#';
            //遍历每个方向
            for(int d = 0; d < 4; d++){
                if(dfs(board, i + dx[d], j + dy[d], chars, index + 1)){
                    return true;
                }
            }
            //恢复当前元素
            board[i][j] = current;
        }
        return false;
    }
}
