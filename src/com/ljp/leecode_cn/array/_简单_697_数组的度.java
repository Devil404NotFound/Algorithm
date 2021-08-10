package com.ljp.leecode_cn.array;

import java.util.HashMap;
import java.util.Map;

/** 每日一题 2021.02.20
 * @author lijunpeng
 * @date 2021/2/20 15:34
697. 数组的度
给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。

你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。



示例 1：

输入：[1, 2, 2, 3, 1]
输出：2
解释：
输入数组的度是2，因为元素1和2的出现频数最大，均为2.
连续子数组里面拥有相同度的有如下所示:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组[2, 2]的长度为2，所以返回2.
示例 2：

输入：[1,2,2,3,1,4,2]
输出：6


提示：

nums.length 在1到 50,000 区间范围内。
nums[i] 是一个在 0 到 49,999 范围内的整数。
 */
public class _简单_697_数组的度 {
    public static void main(String[] args) {
        _简单_697_数组的度 test = new _简单_697_数组的度();
        int[] nums = new int[]{1,2,2,3,1};
        test.findShortestSubArray(nums);
    }

    /**
     * 哈希映射
     * @param nums
     * @return
    执行用时：
    9 ms, 在所有 Java 提交中击败了93.52%的用户
    内存消耗：
    43.2 MB, 在所有 Java 提交中击败了23.89%的用户
     */
    public int findShortestSubArray(int[] nums) {
        int n = nums.length;
        int maxDegree = 1;
        Map<Integer, int[]> map = new HashMap<>(); //key：元素值；value:1.出现次数，2.元素左边界，3.元素右边界
        for(int i = 0; i < n; ++i) {
            int[] degree = map.get(nums[i]);
            if(degree == null) {
                degree = new int[3];
                degree[0] = 1; //设置出现次数为1
                degree[1] = i;//设置左边界
                map.put(nums[i], degree);
            }else{
                degree[0]++; //出现次数+1
                maxDegree = Math.max(maxDegree, degree[0]);//找到最大的度
            }
            degree[2] = i; //设置右边界
        }
        //找到最大度的最小子数组
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, int[]> integerEntry : map.entrySet()) {
            int[] degree = integerEntry.getValue();
            if(degree[0] == maxDegree) {
                ans = Math.min(ans, degree[2] - degree[1] + 1);
            }
        }
        return ans;
    }
}
