package com.ljp.leecode_cn.array;

/**
 * 每日一题 2022.11.16
 * 775. 全局倒置与局部倒置
 * 给你一个长度为 n 的整数数组 nums ，表示由范围 [0, n - 1] 内所有整数组成的一个排列。
 * <p>
 * 全局倒置 的数目等于满足下述条件不同下标对 (i, j) 的数目：
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * <p>
 * 局部倒置 的数目等于满足下述条件的下标 i 的数目：
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * 当数组 nums 中 全局倒置 的数量等于 局部倒置 的数量时，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有 1 个全局倒置，和 1 个局部倒置。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有 2 个全局倒置，和 1 个局部倒置。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] < n
 * nums 中的所有整数 互不相同
 * nums 是范围 [0, n - 1] 内所有数字组成的一个排列
 */
public class _中等_775_全局倒置与局部倒置 {
    /**
     * 官方题解一：维护后缀和
     * 一个局部倒置一定是一个全局倒置，因此要判断数组中局部倒置的数量是否与全局倒置的数量相等，只需要检查有没有非局部倒置就可以了。
     * 这里的非局部倒置指的是nums[i]>nums[j]，其中 i < j - 1i<j−1。
     */
    public boolean isIdealPermutation(int[] nums) {
        int minSuf = nums[nums.length - 1];
        for (int i = nums.length - 3; i >= 0; i--) {
            if (nums[i] > minSuf) {
                return false;
            }
            minSuf = Math.min(minSuf, nums[i + 1]);
        }
        return true;
    }

    /**
     * 官方题解二：归纳证明
     * nums 是一个由 0∼n−1 组成的排列，设不存在非局部倒置的排列为「理想排列」。
     * 由于非局部倒置表示存在一个 j > i + 1j>i+1 使得 nums[i]>nums[j] 成立，所以对于最小的元素 00 来说，它的下标不能够大于等于 22。
     * 所以有：
     * 若 nums[0]=0，那么问题转换为 [1, n - 1][1,n−1] 区间的一个子问题。
     * 若 nums[1]=0，那么nums[0] 只能为 11，因为如果是大于 11 的任何元素，都将会与后面的 11 构成非局部倒置。
     * 此时，问题转换为了 [2, n - 1][2,n−1] 区间的一个子问题。
     */
    public Boolean isIdealPermutation2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (Math.abs(nums[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }
}
