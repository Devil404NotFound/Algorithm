package com.ljp.leecode_cn.array;

/** 每日一题 201.09.15
 * @author lijunpeng
 * @date 2021/9/15 22:59
 * @Description
162. 寻找峰值
峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。

你可以假设 nums[-1] = nums[n] = -∞ 。

你必须实现时间复杂度为 O(log n) 的算法来解决此问题。



示例 1：

输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
示例 2：

输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5
解释：你的函数可以返回索引 1，其峰值元素为 2；
或者返回索引 5， 其峰值元素为 6。


提示：

1 <= nums.length <= 1000
-231 <= nums[i] <= 231 - 1
对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class _中等_162_寻找峰值 {

    /** 官方题解一：寻找最大值
    * @Author lijunpeng
    * @Date 2021/9/15 23:01
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.1 MB, 在所有 Java 提交中击败了35.48%的用
    **/
    public int findPeakElement(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > nums[idx]) {
                idx = i;
            }
        }
        return idx;
    }
    /** 评论区一：爬坡法
    * @Author lijunpeng
    * @Date 2021/9/15 23:02
    * @Description
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.3 MB, 在所有 Java 提交中击败了5.05%的用户
    **/
    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < nums[mid+1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
