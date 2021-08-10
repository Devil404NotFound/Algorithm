package com.ljp.leecode_cn.sort;

import java.util.Arrays;

/** 2021.07.20 每日一题
 * @author lijunpeng
 * @date 2021/7/20 7:46
 * @Description
1877. 数组中最大数对和的最小值
一个数对(a,b)的 数对和等于a + b。最大数对和是一个数对数组中最大的数对和。

比方说，如果我们有数对(1,5)，(2,3)和(4,4)，最大数对和为max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8。
给你一个长度为 偶数n的数组nums，请你将 nums中的元素分成 n / 2个数对，使得：

nums中每个元素恰好在 一个数对中，且最大数对和的值 最小。
请你在最优数对划分的方案下，返回最小的 最大数对和。



示例 1：

输入：nums = [3,5,2,3]
输出：7
解释：数组中的元素可以分为数对 (3,3) 和 (5,2) 。
最大数对和为 max(3+3, 5+2) = max(6, 7) = 7 。
示例 2：

输入：nums = [3,5,4,2,4,6]
输出：8
解释：数组中的元素可以分为数对 (3,5)，(4,4) 和 (6,2) 。
最大数对和为 max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8 。


提示：

n == nums.length
2 <= n <= 105
n是 偶数。
1 <= nums[i] <= 105

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _中等_1877_数组中最大数对的最小值 {
    /**
     * 排序+双指针
     * @param nums
     * @return
    执行用时：
    57 ms, 在所有 Java 提交中击败了98.92%的用户
    内存消耗：
    53.2 MB, 在所有 Java 提交中击败了93.89%的用户
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int left = 0, right = nums.length - 1;
        while(left < right) {
            max = Math.max(max, nums[left] + nums[right]);
            ++left;
            --right;
        }
        return max;
    }
}
