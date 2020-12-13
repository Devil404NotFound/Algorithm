package com.ljp.leecode_cn.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 每日一题 2020.12.13
 * @author ljp
 * @date 2020/12/13 18:22
217. 存在重复元素
给定一个整数数组，判断是否存在重复元素。

如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。



示例 1:

输入: [1,2,3,1]
输出: true
示例 2:

输入: [1,2,3,4]
输出: false
示例 3:

输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
 */
public class _简单_217_存在重复元素 {
    /**
     * 通过Set判断是否重复
     * @param nums
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了77%的用户
    内存消耗：
    42.7 MB, 在所有 Java 提交中击败了69%的用户
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 官方题解一： 排序
     * @param nums
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了96.88%的用户
    内存消耗：
    41.5 MB, 在所有 Java 提交中击败了94.55%的用户
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; ++i) {
            if(nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

}
