package com.ljp.leecode_cn.array;

/**
 * 33. 搜索旋转排序数组【中等】
 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

 ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

 你可以假设数组中不存在重复的元素。

 你的算法时间复杂度必须是 O(log n) 级别。

 示例 1:

 输入: nums = [4,5,6,7,0,1,2], target = 0
 输出: 4
 示例 2:

 输入: nums = [4,5,6,7,0,1,2], target = 3
 输出: -1
 */
public class _medium_33搜索旋转排序数组 {
    public static void main(String[] args) {
        int[] nums = {10,11,12,0,1,2,3,4,5,6};
        System.out.println(search(nums, 0));
    }

    /**
     * 递归调用
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        return searchCore(nums, target, 0, nums.length - 1);
    }
    private static int searchCore(int[] nums, int target, int low, int high){
        if(low <= high) {
            int mid = low + ((high - low) >> 1);
            if(low == high){
                if(nums[low] == target){
                    return low;
                }else{
                    return -1;
                }
            }
            if(target == nums[mid]){
                return mid;
            }
            //判断左有序还是右有序
            if(nums[mid] >= nums[low]){//左有序(因为两个数时，mid等于前一个，因此需要包括等于
                if(target < nums[mid] && target >= nums[low]){
                    return searchCore(nums, target, low, mid - 1);
                }else{
                    return searchCore(nums, target, mid + 1, high);
                }
            }else{
                if(target > nums[mid] && target <= nums[high]){
                    return searchCore(nums, target, mid + 1, high);
                }else{
                    return searchCore(nums, target, low, mid - 1);
                }
            }
        }
        return -1;
    }

    /**
     *同思路，递归换成循环
     * @param nums
     * @param target
     * @return
     * 执行用时 :
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗 :
    39.6 MB, 在所有 Java 提交中击败了17.74%的用户
     */
    public static int search2(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + ((right - left) >> 1);
            if(target == nums[mid]){
                return mid;
            }else if(nums[mid] < nums[right]){//右边有序
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{
                if(nums[mid] > target && target >= nums[left]){
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
