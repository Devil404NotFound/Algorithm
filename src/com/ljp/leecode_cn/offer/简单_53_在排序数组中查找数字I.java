package com.ljp.leecode_cn.offer;

/** 每日一题 2021.07.16
 * @author lijunpeng
 * @date 2021/7/16 22:39
 * @Description
统计一个数字在排序数组中出现的次数。

 

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
 

限制：

0 <= 数组长度 <= 50000

 

注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 简单_53_在排序数组中查找数字I {
    /**
     * 暴力算法
     * @param nums
     * @param target
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了27.22%的用户
    内存消耗：
    41.3 MB, 在所有 Java 提交中击败了64.50%的用户
     */
    public int search(int[] nums, int target) {
        int count = 0;
        for(int num : nums) {
            if(num == target) {
                ++count;
            }
        }
        return count;
    }

    /**
     * 官方题解：二分法
     * @param nums
     * @param target
     * @return
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    41.5 MB, 在所有 Java 提交中击败了15.51%的用户
     */
    public int search2(int[] nums, int target) {
        int leftIndex = binarySearch(nums, target, true);
        int rightIndex = binarySearch(nums, target, false) - 1;
        if(leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target) {
            return rightIndex - leftIndex + 1;
        }
        return 0;
    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] > target || (lower&& nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
