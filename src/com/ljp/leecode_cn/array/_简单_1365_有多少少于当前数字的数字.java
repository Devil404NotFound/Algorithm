package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2020.10.26
 1365. 有多少小于当前数字的数字
 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。

 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。

 以数组形式返回答案。



 示例 1：

 输入：nums = [8,1,2,2,3]
 输出：[4,0,1,1,3]
 解释：
 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 对于 nums[1]=1 不存在比它小的数字。
 对于 nums[2]=2 存在一个比它小的数字：（1）。
 对于 nums[3]=2 存在一个比它小的数字：（1）。
 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 示例 2：

 输入：nums = [6,5,4,8]
 输出：[2,1,0,3]
 示例 3：

 输入：nums = [7,7,7,7]
 输出：[0,0,0,0]


 提示：

 2 <= nums.length <= 500
 0 <= nums[i] <= 100

 * @author ljp
 * @date 2020/10/26 11:39
 */
public class _简单_1365_有多少少于当前数字的数字 {
    /**
     * 统计哈希表（笨方法）
     * @param nums
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了69.49%的用户
    内存消耗：
    38 MB, 在所有 Java 提交中击败了99.74%的用户
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] hash = new int[101];
        int[] ans = new int[nums.length];
        Arrays.fill(hash, -1);
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(hash[n] != -1){
                ans[i] = hash[n];
                continue;
            }
            for(int j = 0; j < nums.length; j++){
                if(i != j && nums[j] < nums[i]){
                    hash[n]++;
                }
            }
            hash[n]++;
            ans[i] = hash[n];
        }
        return ans;
    }

    /**
     * 计数排序
     * @param nums
     * @return
    执行用时：
    1 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    38.4 MB, 在所有 Java 提交中击败了96.64%的用户
     */
    public int[] smallerNumbersThanCurrent2(int[] nums){
        int[] hash = new int[101];
        //统计每个元素的个数
        for (int i = 0; i < nums.length; i++) {
            hash[nums[i]]++;
        }
        //计算小于等于i的个数
        for(int i = 1; i < hash.length; i++){
            hash[i] += hash[i - 1];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = nums[i] == 0 ? 0 : hash[nums[i] - 1];
        }
        return ans;
    }
}
