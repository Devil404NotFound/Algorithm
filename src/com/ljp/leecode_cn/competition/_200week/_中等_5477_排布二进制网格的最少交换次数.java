package com.ljp.leecode_cn.competition._200week;

import java.util.*;

/**
 * 给你一个 n x n 的二进制网格 grid，每一次操作中，你可以选择网格的 相邻两行 进行交换。

 一个符合要求的网格需要满足主对角线以上的格子全部都是 0 。

 请你返回使网格满足要求的最少操作次数，如果无法使网格符合要求，请你返回 -1 。

 主对角线指的是从 (1, 1) 到 (n, n) 的这些格子。

 示例1
 输入：grid = [[0,0,1],[1,1,0],[1,0,0]]
 输出：3

 示例2
 输入：grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 输出：-1
 解释：所有行都是一样的，交换相邻行无法使网格符合要求。

 示例3
 输入：grid = [[1,0,0],[1,1,0],[1,1,1]]
 输出：0

 提示：

 n == grid.length
 n == grid[i].length
 1 <= n <= 200
 grid[i][j] 要么是 0 要么是 1 。
 */
public class _中等_5477_排布二进制网格的最少交换次数 {
    public static void main(String[] args) {
        int[][] grid = {
//                {1,0,0,0,0,0},{0,1,0,1,0,0},{1,0,0,0,0,0},{1,1,1,0,0,0},{1,1,0,1,0,0},{1,0,0,0,0,0}
                {0,0,1},{1,1,0},{1,0,0}
        };
        System.out.println(minSwaps2(grid));
    }

    /**
     *
     * @param grid
     * @return
     *  解答错误
     */
    public static int minSwaps(int[][] grid) {
        int n = grid.length;
        //用于记录右边有i个连续的0的行坐标
        ArrayList<Integer>[] nums = new ArrayList[n+1];
        for (int i =  0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0 ; j--) {
                if(grid[i][j] == 1){
                    break;
                }
                count++;
            }
            if(nums[count] == null){
                nums[count] = new ArrayList<>();
            }
            nums[count].add(i);
        }
        int minSetp = 0;
        int count = n;
        for (int i = 0; i < n; i++) {
            while(nums[count] == null){
                count--;
            }
            for(Integer row : nums[count]){
                if(count < n - i - 1){
                    return -1;
                }
                minSetp += row - i;
                i++;
            }
            i--;
            count--;
        }
        int[] preCount = new int[n];
        for (int i = n; i >= 0; i--) {
            if(nums[i] != null){
                for(Integer row : nums[i]){
                    for (int j = 0; j < row; j++) {
                        preCount[j]++;
                    }
                    minSetp += preCount[row];
                }
            }
        }
        return minSetp;
    }

    /**
     *
     * @param grid
     * @return
     * 执行用时：
    10 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    41.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public static int minSwaps2(int[][] grid) {
        int n = grid.length;
        LinkedList<Integer> countLink = new LinkedList<>();//统计每一行的0的个数
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if(grid[i][j] != 0){
                    break;
                }
                count++;
            }
            countLink.offer(count);
        }
        int minStep = 0;
        //n-1,最后一行不用判断（不需要0）
        for (int i = 0; i < n - 1; i++) {
            int count = n - i - 1;
            int j = 0;
            while(j < n){
                if(countLink.get(j) >= count){
                    minStep += j - i;
                    countLink.remove(j);
                    countLink.addFirst(0);
                    break;
                }
                j++;
            }
            if(j == n){
                return -1;
            }
        }
        return minStep;
    }
}
