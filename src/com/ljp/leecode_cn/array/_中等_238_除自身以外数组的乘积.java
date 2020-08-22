package com.ljp.leecode_cn.array;

/**
 * 238. 除自身以外数组的乘积
 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。



 示例:

 输入: [1,2,3,4]
 输出: [24,12,8,6]


 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

 进阶：
 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

 通过次数75,482提交次数106,730
 */
public class _中等_238_除自身以外数组的乘积 {
    /**
     * 用前缀后缀辅助数组
     * @param nums
     * @return
     * 执行用时：
    2 ms, 在所有 Java 提交中击败了36.00%的用户
    内存消耗：
    48.3 MB, 在所有 Java 提交中击败了49.67%的用户
     */
    public int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length];
        int[] suf = new int[nums.length];
        int len = nums.length;
        pre[0] = 1;
        suf[len - 1] = 1;
        for(int i = 1; i < len; i++){
            pre[i] = pre[i - 1] * nums[i - 1];
            suf[len - i - 1] = suf[len - i] * nums[len - i];
        }
        int[] ans = new int[nums.length];
        for(int i = 0; i < len; i++){
            ans[i] = pre[i] * suf[i];
        }
        return ans;
    }

    /**
     * 优化空间复杂度为O(1)
     * @param nums
     * @return
     * 执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    48.4 MB, 在所有 Java 提交中击败了28.86%的用户
     */
    public int[] productExceptSelf2(int[] nums) {
        int pre = 1;
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            res[i] = pre;
            pre *= nums[i];
        }
        int suf = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            res[i] *= suf;
            suf *= nums[i];
        }
        return res;
    }
}
