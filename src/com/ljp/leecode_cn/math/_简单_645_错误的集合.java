package com.ljp.leecode_cn.math;

/**
 645. 错误的集合
 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。

 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。

 示例 1:

 输入: nums = [1,2,2,4]
 输出: [2,3]
 注意:

 给定数组的长度范围是 [2, 10000]。
 给定的数组是无序的。

 * @author ljp
 * @date 2020/11/8 13:36
 */
public class _简单_645_错误的集合 {
    /**
     *
     * @param nums
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了94.25%的用户
    内存消耗：
    40.1 MB, 在所有 Java 提交中击败了61.51%的用户
     */
    public int[] findErrorNums(int[] nums) {
        int dup = 0, missing = 0;
        int[] helper = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            missing ^= (i + 1);
            if(helper[num] == 0){
                helper[num] = 1;
                missing ^= num;
            }else{
                dup = num;
            }
        }
        return new int[]{dup, missing};
    }
}
