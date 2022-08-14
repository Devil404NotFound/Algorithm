package com.ljp.leecode_cn;

/**
 * @author lijunpeng
 * @date 2022/4/22 20:12
 * @description
给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。

示例 1：
输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
输出：6
解释：[1,1,1,0,0,1,1,1,1,1,1]
粗体数字从 0 翻转到 1，最长的子数组长度为 6。

示例 2：
输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
输出：10
解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]

 **/

public class _od_二面 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,0,0,0,1,1,1,1,0};
        int k = 2;
        int result = getMaxNum(nums, k);
        System.out.println(result);
    }
    public static int getMaxNum(int[] nums, int k) {
        int left = 0, right = 0;
        int maxCount = 0;
        while(right < nums.length) {
            if(nums[right] == 0) {
                k--;
            }
            while(k < 0) {
                if(nums[left] == 0) {
                    k++;
                }
                left++;
            }
            maxCount = Math.max(right - left + 1, maxCount);
            right++;
        }
        return maxCount;
    }
}
