package com.ljp.leecode_cn.math;

/**
 * 1480. 一维数组的动态和
 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。

 请返回 nums 的动态和。



 示例 1：

 输入：nums = [1,2,3,4]
 输出：[1,3,6,10]
 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
 示例 2：

 输入：nums = [1,1,1,1,1]
 输出：[1,2,3,4,5]
 解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。
 示例 3：

 输入：nums = [3,1,2,10,1]
 输出：[3,4,6,16,17]


 提示：

 1 <= nums.length <= 1000
 -10^6 <= nums[i] <= 10^6
 */
public class _简单_1480_一维数组的动态和 {
    /**
     *
     * @param nums
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    39.6 MB, 在所有 Java 提交中击败了96.05%的用户
     */
    public int[] runningSum(int[] nums) {
        int[] running = new int[nums.length];
        running[0] = nums[0];
        for(int i = 1; i < nums.length; ++i){
            running[i] = running[i - 1] + nums[i];
        }
        return running;
    }

}
