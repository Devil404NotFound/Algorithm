package com.ljp.leecode_cn.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 659. 分割数组为连续子序列
 输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，其中每个子序列至少包含三个连续整数。返回你是否能做出这样的分割？



 示例 1：

 输入: [1,2,3,3,4,5]
 输出: True
 解释:
 你可以分割出这样两个连续子序列 :
 1, 2, 3
 3, 4, 5


 示例 2：

 输入: [1,2,3,3,4,4,5,5]
 输出: True
 解释:
 你可以分割出这样两个连续子序列 :
 1, 2, 3, 4, 5
 3, 4, 5


 示例 3：

 输入: [1,2,3,4,4,5]
 输出: False


 提示：

 输入的数组长度范围为 [1, 10000]
 */
public class _中等_659分割数组为连续子序列 {

    /**
     * 官方题解-方法二-贪心算法
     * @param nums
     * @return
     * 执行用时 :
    30 ms, 在所有 Java 提交中击败了63.17%的用户
    内存消耗 :
    40.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isPossible(int[] nums) {
        Counter count = new Counter();
        Counter tails = new Counter();
        for (int i = 0; i < nums.length; i++) {
            count.add(nums[i], 1);
        }
        for (int i = 0; i < nums.length; i++) {
            if(count.get(nums[i]) == 0){
                continue;
            }else if(tails.get(nums[i]) > 0){
                tails.add(nums[i], -1);
                tails.add(nums[i] + 1, 1);
            }else if(count.get(nums[i] + 1) > 0 && count.get(nums[i] + 2) > 0){
                count.add(nums[i] + 1, -1);
                count.add(nums[i] + 2, -1);
                tails.add(nums[i] + 3, 1);
            }else{
                return false;
            }
            count.add(nums[i], -1);
        }
        return true;
    }
    class Counter extends HashMap<Integer, Integer> {
        public Integer get(Integer key) {
           return getOrDefault(key, 0);
        }
        public void add(Integer key, Integer val){
            put(key, get(key) + val);
        }
    }
}
