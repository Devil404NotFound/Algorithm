package com.ljp.leecode_cn.math;

/**
 453. 最小移动次数使数组元素相等
 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。



 示例:

 输入:
 [1,2,3]

 输出:
 3

 解释:
 只需要3次移动（注意每次移动会增加两个元素的值）：

 [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * @author ljp
 * @date 2020/10/30 19:04
 */
public class _简单_453_最小移动次数使数组元素相等 {
    /**
     * n-1的元素增加1相当于最大值-1，也就是求把所有数都减到最小值的次数
     * @param nums
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了84.34%的用户
    内存消耗：
    39.3 MB, 在所有 Java 提交中击败了44.37%的用户
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
        }
        int ans = min * nums.length * -1;
        for(int i = 0; i < nums.length; i++){
            ans += nums[i];
        }
        return ans;
    }

    /**
     * 一次循环就可以
     * @param nums
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了84.34%的用户
    内存消耗：
    39.4 MB, 在所有 Java 提交中击败了37.41%的用户
     */
    public int minMoves2(int[] nums){
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for(int i = 0; i < nums.length; i++){
            min = Math.min(min, nums[i]);
            ans += nums[i];
        }
        return ans - min * nums.length;
    }
}

