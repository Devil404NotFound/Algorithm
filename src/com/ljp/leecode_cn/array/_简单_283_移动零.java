package com.ljp.leecode_cn.array;

import org.lanqiao.Utils.Util;

/** 每日一题 2020.11.19
 *
 283. 移动零
 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

 示例:

 输入: [0,1,0,3,12]
 输出: [1,3,12,0,0]
 说明:

 必须在原数组上操作，不能拷贝额外的数组。
 尽量减少操作次数。

 * @author ljp
 * @date 2020/11/19 10:21
 */
public class _简单_283_移动零 {
    public static void main(String[] args) {
        _简单_283_移动零 test = new _简单_283_移动零();
        int[] nums = {0,1,2,3,0,0,5,0};
        test.moveZeroes(nums);
        Util.print(nums);
    }

    /**
     * 直接遍历覆盖
     * @param nums
    执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.9 MB, 在所有 Java 提交中击败了71.94%的用户
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int i = 0;
        for (int j = 0; j < len; j++) {
            while(j < len && nums[j] == 0){
                j++;
            }
            if(j == len){
                break;
            }
            nums[i++] = nums[j];
        }
        while(i < len){
            nums[i++] = 0;
        }
    }

    /**
     * 官方题解：双指针
     使用双指针，左指针指向已经处理好的序列尾部，右指针指向未处理序列的头部
     右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     注意以下性质
     1. 左指针左边均为非零数
     2. 右指针左边到左指针处均为零。
     因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while(right < n) {
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
