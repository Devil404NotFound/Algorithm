package com.ljp.leecode_cn.math;

/**
 * 1512. 好数对的数目
 给你一个整数数组 nums 。

 如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。

 返回好数对的数目。



 示例 1：

 输入：nums = [1,2,3,1,1,3]
 输出：4
 解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
 示例 2：

 输入：nums = [1,1,1,1]
 输出：6
 解释：数组中的每组数字都是好数对
 示例 3：

 输入：nums = [1,2,3]
 输出：0


 提示：

 1 <= nums.length <= 100
 1 <= nums[i] <= 100
 */
public class _简单_1512_好数对的数目 {
    /**
     * 方法一：暴力算法
     * @param nums
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了83.35%的用户
    内存消耗：
    37.3 MB, 在所有 Java 提交中击败了14.24%的用户
     */
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for(int i = 0; i < nums.length; ++i){
            for(int j = i + 1; j < nums.length; ++j){
                if(nums[i] == nums[j]){
                    ++count;
                }
            }
        }
        return count;
    }

    /**
     * 执行用时：
     0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：
     36.9 MB, 在所有 Java 提交中击败了84.59%的用户
     * @param nums
     * @return
     */
    public int numIdenticalPairs2(int[] nums) {
        int[] help = new int[101];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            ++help[nums[i]];
        }
        for (int i = 0; i < help.length; i++) {
            if(help[i] != 0){
                count += help[i] * (help[i] - 1) >> 1;//0、1、2、3、4等差数列求和为n(n-1)/2
            }
        }
        return count;
    }

    /**
     * 评论区大佬，再优化
     * @param nums
     * @return
     * 执行用时：
    0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    36.9 MB, 在所有 Java 提交中击败了75.93%的用户
     */
    public int numIdenticalPairs3(int[] nums) {
        int[] help = new int[101];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += help[nums[i]];
            ++help[nums[i]];
        }
        return count;
    }
}
