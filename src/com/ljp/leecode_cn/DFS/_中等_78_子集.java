package com.ljp.leecode_cn.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 78. 子集
 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

 说明：解集不能包含重复的子集。

 示例:

 输入: nums = [1,2,3]
 输出:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class _中等_78_子集 {
    /**
     * 二进制方法
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了99.38%的用户
    内存消耗：
    39 MB, 在所有 Java 提交中击败了75.17%的用户
     */
    public List<List<Integer>> subsets(int[] nums) {
        int n = 1 << nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(long i = 0; i < n; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++){
                if(((i >> j) & 1) == 1){
                    list.add(nums[j]);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}
