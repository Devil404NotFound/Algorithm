package com.ljp.leecode_cn.math;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 628. 三个数的最大乘积
 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

 示例 1:

 输入: [1,2,3]
 输出: 6
 示例 2:

 输入: [1,2,3,4]
 输出: 24
 注意:

 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 * @author ljp
 * @date 2020/11/2 23:09
 */
public class _简单_628_三个数的最大成积 {
    /**
     *
     * @param nums
     * @return
    执行用时：
    12 ms, 在所有 Java 提交中击败了69.91%的用户
    内存消耗：
    39.8 MB, 在所有 Java 提交中击败了88.24%的用户
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[0]* nums[1] * nums[len - 1], nums[len - 1] * nums[len - 2] * nums[len - 3]);
    }

    /**
     * 线性扫描
     * @param nums
     * @return
    执行用时：
    3 ms
    , 在所有 Java 提交中击败了82.34%的用户内存消耗：
    39.9 MB, 在所有 Java 提交中击败了85.58%的用户
     */
    public int maximunProduct2(int[] nums){
        int min1, min2;
        int max1, max2, max3;
        min1 = min2 = Integer.MAX_VALUE;
        max1 = max2 = max3 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if(min1 > nums[i]){
                min2 = min1;
                min1 = nums[i];
            }else if(min2 > nums[i]){
                min2 = nums[i];
            }
            if(max3 < nums[i]){
                max1 = max2;
                max2 = max3;
                max3 = nums[i];
            }else if(max2 < nums[i]){
                max1 = max2;
                max2 = nums[i];
            }else if(max1 < nums[i]){
                max1 = nums[i];
            }
        }
        return Math.max(min1 * min2 * max3, max1 * max2 * max3);
    }
}
