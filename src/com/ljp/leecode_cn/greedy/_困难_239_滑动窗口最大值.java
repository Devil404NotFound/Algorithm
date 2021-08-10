package com.ljp.leecode_cn.greedy;

import java.util.PriorityQueue;

/** 每日一题 2021.01.02
 * @author ljp
 * @date 2021/1/2 21:33
239. 滑动窗口最大值
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。



示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
示例 3：

输入：nums = [1,-1], k = 1
输出：[1,-1]
示例 4：

输入：nums = [9,11], k = 2
输出：[11]
示例 5：

输入：nums = [4,-2], k = 2
输出：[4]


提示：

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */
public class _困难_239_滑动窗口最大值 {
    /**
     * 超时
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> priority = new PriorityQueue<>((a, b) -> b - a);
        int[] ret = new int[nums.length  - k + 1];
        for(int i = 0; i < k; ++i) {
            priority.offer(nums[i]);
        }

        for (int i = k; i < nums.length; ++i) {
            ret[i - k] = priority.peek();
            priority.remove(nums[i - k]);
            priority.offer(nums[i]);
        }
        ret[nums.length - k] = priority.peek();
        return ret;
    }

    /**
     * 官方题解一：优先队列
     * @param nums
     * @param k
     * @return
    执行用时：
    81 ms, 在所有 Java 提交中击败了11.30%的用户
    内存消耗：
    59.2 MB, 在所有 Java 提交中击败了8.73%的用户
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int[]> priority = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for(int i = 0; i < k; ++i) {
            priority.offer(new int[]{nums[i], i});
        }
        ans[0] = priority.peek()[0];
        for(int i = k; i < n; ++i) {
            priority.offer(new int[]{nums[i], i});
            while(priority.peek()[1] <= i - k) {
                priority.poll();
            }
            ans[i - k + 1] = priority.peek()[0];
        }
        return ans;
    }
}
