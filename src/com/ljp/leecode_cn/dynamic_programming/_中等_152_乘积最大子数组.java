package com.ljp.leecode_cn.dynamic_programming;

/**
 * 152. 乘积最大子数组
 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。



 示例 1:

 输入: [2,3,-2,4]
 输出: 6
 解释: 子数组 [2,3] 有最大乘积 6。
 示例 2:

 输入: [-2,0,-1]
 输出: 0
 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class _中等_152_乘积最大子数组 {
    /**
     * 暴力算法
     * @param nums
     * @return
     * 执行用时 :
    109 ms, 在所有 Java 提交中击败了7.38%的用户
    内存消耗 :
    39.7 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];
        for(int i = 0; i < nums.length; i++){
            int num = 1;
            for(int j = i; j < nums.length; j++){
                num *= nums[j];
                if(num > max){
                    max = num;
                }
            }
        }
        return max;
    }

    /**
     * 题解方法：动态规划
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了92.56%的用户
    内存消耗 :
    39.7 MB, 在所有 Java 提交中击败了6.67%的用户
     */
    public int maxProduct2(int[] nums) {
        int maxF = nums[0], minF = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int mx = maxF;
            int mn = minF;
            maxF = Math.max(nums[i], Math.max(mx * nums[i], mn * nums[i]));
            minF = Math.min(nums[i], Math.min(mx * nums[i], mn * nums[i]));
            res = Math.max(maxF, res);
        }
        return res;
    }
}
