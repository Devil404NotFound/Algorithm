package com.ljp.leecode_cn.greedy;

/**
 * 45. 跳跃游戏 II【困难】
 给定一个非负整数数组，你最初位于数组的第一个位置。

 数组中的每个元素代表你在该位置可以跳跃的最大长度。

 你的目标是使用最少的跳跃次数到达数组的最后一个位置。

 示例:

 输入: [2,3,1,1,4]
 输出: 2
 解释: 跳到最后一个位置的最小跳跃数是 2。
 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 说明:

 假设你总是可以到达数组的最后一个位置。
 */
public class _困难_45_跳跃游戏II {
    /**
     * 我的方法
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了94.90%的用户
    内存消耗 :
    42.2 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public int jump(int[] nums) {
        int count = 0;
        int i = 0;
        while(i < nums.length - 1){
            //记录当前的位置下标以及能跳跃的最大距离之和
            int len = nums[i] + i;
            //如果已经找到最后一个，再跳跃一次，就直接返回
            if(len >= nums.length - 1){//len指的是下标，需要-1
                count++;
                break;
            }
            int max = len;
            int maxIndex = i;
            for(int j = i; j <= len; j++){
                if(max < nums[j] + j){
                    max = nums[j] + j;
                    maxIndex = j;
                }
            }
            count++;
            i = maxIndex;
        }
        return count;
    }

    /**
     * 官方题解
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了94.90%的用户
    内存消耗 :
    41.9 MB, 在所有 Java 提交中击败了5.00%的用户
     */
    public int jump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
