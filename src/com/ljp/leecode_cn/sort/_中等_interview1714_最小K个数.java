package com.ljp.leecode_cn.sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/** 每日一题 2021.09.03
 * @author lijunpeng
 * @date 2021/9/3 20:13
 * @Description
面试题 17.14. 最小K个数
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：

输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
提示：

0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))
 */
public class _中等_interview1714_最小K个数 {
    /** 优先队列（大顶堆）
    * @Author lijunpeng
    * @Date 2021/9/3 21:19
    * @Description
    执行用时：
    23 ms, 在所有 Java 提交中击败了32.47%的用户
    内存消耗：
    46.6 MB, 在所有 Java 提交中击败了79.20%的用户
    **/
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> priority = new PriorityQueue<>((a, b) -> b - a);
        int[] ans = new int[k];
        if(k == 0) {
            return ans;
        }
        for (int i = 0; i < k; i++) {
            priority.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (priority.peek() > arr[i]) {
                priority.poll();
                priority.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; i++) {
            ans[i] = priority.poll();
        }
        return ans;
    }
    /** 官方题解一；排序
    * @Author lijunpeng
    * @Date 2021/9/3 23:30
    * @Description
    执行用时：
    6 ms, 在所有 Java 提交中击败了70.64%的用户
    内存消耗：
    48.2 MB, 在所有 Java 提交中击败了17.50%的用户
    **/
    public int[] smallestK2(int[] arr, int k) {
        int[] vec = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }
    /** 官方题解三：快排
     作者：LeetCode-Solution
     链接：https://leetcode-cn.com/problems/smallest-k-lcci/solution/zui-xiao-kge-shu-by-leetcode-solution-o5eg/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * @Author lijunpeng
    * @Date 2021/9/3 23:33
    * @Description
    执行用时：
    3 ms, 在所有 Java 提交中击败了82.76%的用户
    内存消耗：
    48.5 MB, 在所有 Java 提交中击败了5.21%的用户
    **/
    public int[] smallestK3(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] vec = new int[k];
        for (int i = 0; i < k; ++i) {
            vec[i] = arr[i];
        }
        return vec;
    }

    private void randomizedSelected(int[] arr, int l, int r, int k) {
        if (l >= r) {
            return;
        }
        int pos = randomizedPartition(arr, l, r);
        int num = pos - l + 1;
        if (k == num) {
            return;
        } else if (k < num) {
            randomizedSelected(arr, l, pos - 1, k);
        } else {
            randomizedSelected(arr, pos + 1, r, k - num);
        }
    }

    // 基于随机的划分
    private int randomizedPartition(int[] nums, int l, int r) {
        int i = new Random().nextInt(r - l + 1) + l;
        swap(nums, r, i);
        return partition(nums, l, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; ++j) {
            if (nums[j] <= pivot) {
                i = i + 1;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, r);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
