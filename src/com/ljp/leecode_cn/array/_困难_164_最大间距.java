package com.ljp.leecode_cn.array;

import java.util.Arrays;

/** 每日一题 2020.11.26
 * @author ljp
 * @date 2020/11/26 15:25
 *
164. 最大间距
给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例 1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例 2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。
说明:

你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class _困难_164_最大间距 {

    public static void main(String[] args) {
        _困难_164_最大间距 test = new _困难_164_最大间距();
        int[] nums = {1,100000};
        int ans = test.maximumGap3(nums);
        System.out.println(ans);
    }
    /**
     * 直接排序，不符合要求，但是能通过
     * @param nums
     * @return
    执行用时：
    3 ms, 在所有 Java 提交中击败了62.08%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了70.66%的用户
     */
    public int maximumGap(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for(int i = 1; i < nums.length; ++i) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    /**
     * 官方题解一：基数排序
     * @param nums
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了15.33%的用户
    内存消耗：
    38.7 MB, 在所有 Java 提交中击败了75.66%的用户
     */
    public int maximumGap2(int[] nums) {
        int n = nums.length;
        if(n < 2){
            return 0;
        }
        int[] buf = new int[n];//基数排序临时数组
        long exp = 1; //防止溢出
        int maxVal = Arrays.stream(nums).max().getAsInt();//获取最大值
        while(exp <= maxVal) {
            int[] cnt = new int[10];//基数排序辅助数组
            //入基数桶
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int)exp) % 10;
                ++cnt[digit];
            }
            //计算每个数的位置范围
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            //出基数桶
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int)exp) % 10;
                buf[--cnt[digit]] = nums[i];
            }
            //复制buf的排序给原数组
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;//进十
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i] - nums[i - 1]);
        }
        return max;
    }

    /**
     * 官方题解二：基于桶的算法
     * @param nums
     * @return
     核心： 相邻两个数的最大值不小于 (maxVal - minVal) / (N - 1)
    执行用时：
    4 ms, 在所有 Java 提交中击败了41.08%的用户
    内存消耗：
    38.8 MB, 在所有 Java 提交中击败了70.66%的用户
     */
    public int maximumGap3(int[] nums) {
        int n = nums.length;
        if(n < 2) {
             return 0;
        }
        //找到最大最小值
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            maxVal = Math.max(maxVal, nums[i]);
            minVal = Math.min(minVal, nums[i]);
        }
        //初始化桶
        int d = Math.max(1, (maxVal - minVal) / (n - 1)); //求相邻两个数最小值d
        int bucketSize = (maxVal - minVal) / d + 1;
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; i++) {
            Arrays.fill(bucket[i], -1);
        }
        //动态设置每个桶的最大值和最小值
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if(bucket[idx][0] == -1){
                bucket[idx][0] = bucket[idx][1] = nums[i];
            }else{
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }
        //统计相邻两个桶之间的差值
        int pre = -1, max = 0;
        for (int i = 0; i < bucketSize; i++) {
            if(bucket[i][0] == -1){
                continue;
            }
            if(pre != -1){
                max = Math.max(max, bucket[i][0] - bucket[pre][1]);
            }
            pre = i;
        }
        return max;
    }
}
