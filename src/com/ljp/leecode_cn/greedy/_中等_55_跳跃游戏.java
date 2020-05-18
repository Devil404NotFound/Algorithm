package com.ljp.leecode_cn.greedy;

/**
 * 55. 跳跃游戏
 给定一个非负整数数组，你最初位于数组的第一个位置。

 数组中的每个元素代表你在该位置可以跳跃的最大长度。

 判断你是否能够到达最后一个位置。

 示例 1:

 输入: [2,3,1,1,4]
 输出: true
 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 示例 2:

 输入: [3,2,1,0,4]
 输出: false
 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class _中等_55_跳跃游戏 {
    /**
     *
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了72.39%的用户
    内存消耗 :
    41.7 MB
    , 在所有 Java 提交中击败了12.50%的用户
     */
    public boolean canJump(int[] nums) {
        if(nums.length == 0){
            return true;
        }
        int end = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(end < i){
                return false;
            }
            end = Math.max(end, nums[i] + i);
        }
        return true;
    }
}
