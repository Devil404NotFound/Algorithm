package com.ljp.leecode_cn.binary_search;

/** 每日一题2020.12.01
 * @author ljp
 * @date 2020/12/1 0:10
34. 在排序数组中查找元素的第一个和最后一个位置
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？


示例 1：

输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：

输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：

输入：nums = [], target = 0
输出：[-1,-1]


提示：

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
 */
public class _中等_34_在排序数组中查找元素的第一个和最后一个元素 {
    /**
     * 假的二分法
     * @param nums
     * @param target
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    41.5 MB, 在所有 Java 提交中击败了89.82%的用户
     */
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(target > nums[mid]) {
                left = mid + 1;
            }else if(target < nums[mid]) {
                right = mid - 1;
            }else{
                int l, r;
                l = r = mid;
                while(l >= 0 && nums[l] == nums[mid]){
                    --l;
                }
                while(r < nums.length && nums[r] == nums[mid]) {
                    ++r;
                }
                return new int[]{l + 1, r - 1};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 题解评论区大佬算法
     个人理解：
        如果target > nums[mid],就将left = mid + 1; 如果target <= nums[mid]，就将right = mid - 1;
        1. 求起点：
            当left > right时， left就是target的起点位置， 有1种特殊情况
            mid位置刚好是起点位置，那么执行mid - 1右端点就比起点位置少了1。
            但是这时，前面的元素都比target小，因此每次都会是left = mid + 1, 直到left>right，这时，left = right + 1;就在起点位置
        2. 求终点：
            传target+1作为target参数， 有两种情况
            ① 如果数组有target+1元素值，那么求到的就是target+1的起点，因此需要将结果减一就可以
            ② 如果数组没有target+1元素，那么right会等于target的终点，left会是target的终点+1，因此最后还是将结果减一即可
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        int left = solve(nums, target);
        int right = solve(nums, target + 1);
        if(left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{left, right - 1};
    }
    private int solve(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) >> 1;
            if(target > nums[mid]) {
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
