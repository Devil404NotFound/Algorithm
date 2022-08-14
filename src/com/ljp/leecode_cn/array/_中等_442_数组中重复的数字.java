package com.ljp.leecode_cn.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunpeng
 * @date 2022/5/8 11:06
 * @description
442. 数组中重复的数据
给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。

你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。



示例 1：

输入：nums = [4,3,2,7,8,2,3,1]
输出：[2,3]
示例 2：

输入：nums = [1,1,2]
输出：[1]
示例 3：

输入：nums = [1]
输出：[]


提示：

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
nums 中的每个元素出现 一次 或 两次
 **/

public class _中等_442_数组中重复的数字 {
    /**
    * @Author lijunpeng
    * @Date 2022/5/8 11:07
    * @Description 官方题解一：将元素交换到对应的位置
    */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < nums.length; i++) {
            while(nums[i] != nums[nums[i] - 1]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] - 1 != i) {
                list.add(nums[i]);
            }
        }
        return list;
    }
    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
