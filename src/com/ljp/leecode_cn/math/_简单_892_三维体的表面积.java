package com.ljp.leecode_cn.math;

/**
 * 892. 三维形体的表面积
 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

 请你返回最终形体的表面积。



 示例 1：

 输入：[[2]]
 输出：10
 示例 2：

 输入：[[1,2],[3,4]]
 输出：34
 示例 3：

 输入：[[1,0],[0,2]]
 输出：16
 示例 4：

 输入：[[1,1,1],[1,0,1],[1,1,1]]
 输出：32
 示例 5：

 输入：[[2,2,2],[2,1,2],[2,2,2]]
 输出：46


 提示：

 1 <= N <= 50
 0 <= grid[i][j] <= 50
 */
public class _简单_892_三维体的表面积 {
    /**
     *
     * @param grid
     * @return
     * 执行用时：
    4 ms, 在所有 Java 提交中击败了76.07%的用户
    内存消耗：
    39.5 MB, 在所有 Java 提交中击败了54.54%的用户
     */
    public int surfaceArea(int[][] grid) {
        int N = grid.length;
        int surfaceArea = 0;
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){
                surfaceArea += grid[i][j] == 0 ? 0 : grid[i][j] * 4 + 2;
                if(i > 0 && i < N){
                    surfaceArea -= Math.min(grid[i][j], grid[i - 1][j]) * 2;
                }
                if(j > 0 && j < N){
                    surfaceArea -= Math.min(grid[i][j], grid[i][j - 1]) * 2;
                }
            }
        }
        return surfaceArea;
    }
}
