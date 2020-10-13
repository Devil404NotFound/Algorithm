package com.ljp.leecode_cn.dynamic_programming;

/** 每日一题 2020.10.11
 416. 分割等和子集
 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

 注意:

 每个数组中的元素不会超过 100
 数组的大小不会超过 200
 示例 1:

 输入: [1, 5, 11, 5]

 输出: true

 解释: 数组可以分割成 [1, 5, 5] 和 [11].


 示例 2:

 输入: [1, 2, 3, 5]

 输出: false

 解释: 数组不能分割成两个元素和相等的子集.

 */
public class _中等_416_分割等和子集 {
    /**
     * DFS 超时！！！
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        //求数组和
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        //如果是单数，就不可能分割成相等的子集
        if((sum & 1) == 1){
            return false;
        }
        int mid = sum / 2;
        //递归调用
        return dfs(nums, 0, 0, mid);
    }
    private boolean dfs(int[] nums, int i, int sum, int mid){
        if(sum == mid){
            return true;
        }else if(sum > mid || i >= nums.length){
            return false;
        }
        //递归调用选择这个数与不选择这个数
        return dfs(nums, i + 1, sum + nums[i], mid) || dfs(nums, i + 1, sum, mid);
    }

    /**
     * 官方题解：经典dp问题
     * @param nums
     * @return
    执行用时：
    59 ms, 在所有 Java 提交中击败了8.88%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了6.29%的用h户
     */
    public boolean canPartition2(int[] nums) {
        if(nums.length < 2){
            return false;
        }
        int sum = 0;
        int maxNum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            maxNum = Math.max(maxNum, nums[i]);
        }
        if((sum & 1) == 1){
            return false;
        }
        int target = sum >> 1;
        if(maxNum > target){
            return false;
        }
        boolean[][] dp = new boolean[nums.length][target + 1];
        //初始化
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        //动态规划，问题转移
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                int num = nums[i];
                if(j >= num){
                    dp[i][j] = dp[i - 1][j - num] | dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    /**
     * 对方法二的优化
     * @param nums
     * @return
    执行用时：
    24 ms, 在所有 Java 提交中击败了63.20%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了59.75%的用户
     */
    public boolean canPartition3(int[] nums) {
        if(nums.length < 2){
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if((sum & 1) == 1){
            return false;
        }
        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i] ; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
