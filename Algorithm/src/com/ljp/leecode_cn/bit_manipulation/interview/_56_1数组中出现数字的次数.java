package com.ljp.leecode_cn.bit_manipulation.interview;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题56 - I. 数组中数字出现的次数【中等】
 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。



 示例 1：

 输入：nums = [4,1,4,6]
 输出：[1,6] 或 [6,1]
 示例 2：

 输入：nums = [1,2,10,4,1,4,3,3]
 输出：[2,10] 或 [10,2]


 限制：

 2 <= nums <= 10000
 */
public class _56_1数组中出现数字的次数 {
    /**
     * 我的方法，空间复杂度不符合题目
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else{
                set.add(nums[i]);
            }
        }
        int[] res = new int[2];
        int i = 0;
        for(int n : set){
            res[i++] = n;
        }
        return res;
    }

    /**
     * 大佬思路：1. 循环一遍，求出两个数的异或值，1的地方即是不同的地方，2.再循环一遍 按照其中一个1给nums数组分类
     * @param nums
     * @return
     * 执行用时 :
    2 ms, 在所有 Java 提交中击败了95.47%的用户
    内存消耗 :
    41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] singleNumbers2(int[] nums){
        //求两个结果数的异或值bit，bit为1的位置即是两个数不同的地方
        int bit = 0;
        for (int i = 0; i < nums.length; i++) {
            bit ^= nums[i];
        }
        int single1 = 0;
        int single2 = 0;
        int shift = 0;
        //找到bit第一个为1的地方
        while(((bit >> shift) & 1) == 0){
            shift++;
        }
        //根据第一个为1的地方分类
        for (int i = 0; i < nums.length; i++) {
            if(((nums[i] >> shift) & 1) == 0){
                single1 ^= nums[i];
            }else{
                single2 ^= nums[i];
            }
        }
        return new int[]{single1, single2};
    }
}
