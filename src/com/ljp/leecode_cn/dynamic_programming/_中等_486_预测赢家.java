package com.ljp.leecode_cn.dynamic_programming;

/**
 486. 预测赢家
 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。

 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。



 示例 1：

 输入：[1, 5, 2]
 输出：False
 解释：一开始，玩家1可以从1和2中进行选择。
 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 因此，玩家 1 永远不会成为赢家，返回 False 。
 示例 2：

 输入：[1, 5, 233, 7]
 输出：True
 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。


 提示：

 1 <= 给定的数组长度 <= 20.
 数组里所有分数都为非负数且不会大于 10000000 。
 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 */
public class _中等_486_预测赢家 {
    /**
     * 官方方法一：递归
     * 时间复杂度：2^n
     * @param nums
     * @return
    执行用时：
    86 ms, 在所有 Java 提交中击败了15.42%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了52.03%的用户
     */
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }
    private int total(int[] nums, int start, int end, int turn){
        if(start == end){
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }

    /**
     * 官方题解二：动态规划
     * 时间复杂度：n^2
     * @param nums
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.2 MB, 在所有 Java 提交中击败了19.99%的用户
     */
    public boolean PredictTheWinner2(int[] nums){
        int[][] dp = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i][i] = nums[i];
        }
        for(int i = nums.length - 2; i >= 0; i--){
            for(int j = i + 1; j < nums.length; j++){
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][nums.length - 1] >= 0;
    }

    /**
     * 官方方法二优化：一维动态规划
     * 时间复杂度：n^2
     * @param nums
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    37.1 MB, 在所有 Java 提交中击败了47.93%的用户
     */
    public boolean PredictTheWinner3(int[] nums){
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = nums[i];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[dp.length - 1] >= 0;
    }
}
