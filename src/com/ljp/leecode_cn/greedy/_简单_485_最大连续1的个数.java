package com.ljp.leecode_cn.greedy;

/** 每日一题 2021.02.15
 * @author lijunpeng
 * @date 2021/2/15 14:35
485. 最大连续 1 的个数
给定一个二进制数组， 计算其中最大连续 1 的个数。



示例：

输入：[1,1,0,1,1,1]
输出：3
解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.


提示：

输入的数组只包含 0 和 1 。
输入数组的长度是正整数，且不超过 10,000。
 */
public class _简单_485_最大连续1的个数 {
    public static void main(String[] args) {
        _简单_485_最大连续1的个数 test = new _简单_485_最大连续1的个数();
        int[] nums = {1, 0, 1, 1, 0, 1};
        int result = test.findMaxConsecutiveOnes(nums);
        System.out.println(result);
    }

    /**
     *
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    40 MB, 在所有 Java 提交中击败了55.97%的用户
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for(int num : nums) {
            if(num == 0) {
                max = Math.max(max, count);
                count = 0;
            }else{
                count++;
            }
        }
        return Math.max(max, count);
    }
}
