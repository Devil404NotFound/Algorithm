package com.ljp.leecode_cn.array;

/** 每日一题 2022.11.24
 795. 区间子数组个数
 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 生成的测试用例保证结果符合 32-bit 整数范围。

 示例 1：
 输入：nums = [2,1,4,3], left = 2, right = 3
 输出：3
 解释：满足条件的三个子数组：[2], [2, 1], [3]

 示例 2：
 输入：nums = [2,9,2,5,6], left = 2, right = 8
 输出：7

 提示：

 1 <= nums.length <= 105
 0 <= nums[i] <= 109
 0 <= left <= right <= 109
 */
public class _中等_795_区间子数组个数 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,9,2,5,6};
        int left = 2;
        int right = 8;
        int result = new _中等_795_区间子数组个数().numSubarrayBoundedMax(nums, left, right);
        System.out.println(result);
    }

    /**
     * 官方题解一：
     * 将元素分为3类，分别用0，1，2表示
     * 0：小于left
     * 1：大于等于left且小于等于right
     * 2：大于right
     *
     * last1表示上次出现1的下标，不存在则为-1
     * last2表示上次出现2的下标，不存在则为-1
     *
     * 因此
     * 1、如果left<=nums[i]<=right，令last1=i
     * 2、如果nums[i] > right，令last2=i
     * 如果last1!=-1，那么子数组若以i为右端点，合法的左端点可以落在(last2, last1]之间，这样的左端点有last1-last2个
     *
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0, last2 = -1, last1 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                last1 = i;
            } else if (nums[i] > right) {
                last2 = i;
                last1 = -1;
            }
            if (last1 != -1) {
                res += last1 - last2;
            }
        }
        return res;
    }
    /**
     * 官方题解2：计数
     * 方法count(nums,lower)可以求出nums所有元素小于等于lower的子数组个数
     */
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }

    public int count(int[] nums, int lower) {
        int res = 0, cur = 0;
        for (int x : nums) {
            cur = x <= lower ? cur + 1 : 0;
            res += cur;
        }
        return res;
    }
}
