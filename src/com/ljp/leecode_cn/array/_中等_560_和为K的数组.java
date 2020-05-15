package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

 示例 1 :

 输入:nums = [1,1,1], k = 2
 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 说明 :

 数组的长度为 [1, 20,000]。
 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class _中等_560_和为K的数组 {
    public static void main(String[] args) {
        int[] nums = {-92,-63,75,-86,-58,22,31,-16,-66,-67,420};
        int k = 100;
        System.out.println(subarraySum(nums, k));
        System.out.println(subarraySum2(nums, k));
    }
    /**
     * 方法一：求前缀和
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] preSum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            preSum[i + 1] = preSum[i] + nums[i];
            for(int j = 0; j < i + 1; j++){
                if(preSum[i + 1] - preSum[j] == k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 失败，答案错误
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        int step = -min + 1;
        k = k + step * 2;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += step;
            sum += nums[i];
            if(sum == k){
                count++;
            }else if(sum > k){
                while(sum > k){
                    sum -= nums[start++];
                }
                if(sum == k && start - 1 != i){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 评论区大神答案
     * @param nums
     * @param k
     * @return
     * 执行用时 :
    28 ms, 在所有 Java 提交中击败了54.58%的用户
    内存消耗 :
    40.2 MB, 在所有 Java 提交中击败了11.54%的用户
     */
    public static int subarraySum3(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer>  map = new HashMap<>();
        map.put(0, 1);//重点，k等于nums[i]自身时，可以+1
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            int n = map.getOrDefault(sum, 0) + 1;
            map.put(sum, n);
        }
        return count;
    }
}
