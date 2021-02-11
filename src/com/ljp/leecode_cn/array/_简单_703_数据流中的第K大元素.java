package com.ljp.leecode_cn.array;

import java.util.PriorityQueue;

/** 每日一题 2021.02.11-除夕
 * @author lijunpeng
 * @date 2021/2/11 22:05
703. 数据流中的第 K 大元素
设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。

请实现 KthLargest 类：

KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。


示例：

输入：
["KthLargest", "add", "add", "add", "add", "add"]
[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
输出：
[null, 4, 5, 5, 8, 8]

解释：
KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
kthLargest.add(3);   // return 4
kthLargest.add(5);   // return 5
kthLargest.add(10);  // return 5
kthLargest.add(9);   // return 8
kthLargest.add(4);   // return 8


提示：
1 <= k <= 104
0 <= nums.length <= 104
-104 <= nums[i] <= 104
-104 <= val <= 104
最多调用 add 方法 104 次
题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 */
public class _简单_703_数据流中的第K大元素 {
    class KthLargest {
        PriorityQueue<Integer> priority;//小顶堆

        /**
         *优先队列（小顶堆）
         * @param k
         * @param nums
        执行用时：
        20 ms, 在所有 Java 提交中击败了55.97%的用户
        内存消耗：
        43.9 MB, 在所有 Java 提交中击败了21.90%的用户
         */
        public KthLargest(int k, int[] nums) {
            priority = new PriorityQueue<>();
            priority.add(Integer.MIN_VALUE);//避免出现初始化数组为空的情况
            for(int i = 0; i < nums.length; ++i) {
                if(i < k - 1) {
                    priority.add(nums[i]);
                }else{
                    add(nums[i]);
                }
            }
        }

        public int add(int val) {
            //维持小顶堆有K个数
            if(val > this.priority.peek()) {
                priority.add(val);
                priority.remove();
            }
            return priority.peek();
        }
    }

    /**
     * 官方题解 （思路清晰，比我的棒）
     执行用时：
     20 ms, 在所有 Java 提交中击败了55.97%的用户
     内存消耗：
     44 MB, 在所有 Java 提交中击败了13.65%的用户
     */
    class KthLargest2 {
        PriorityQueue<Integer> pq;
        int k;

        public KthLargest2(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<Integer>();
            for (int x : nums) {
                add(x);
            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            return pq.peek();
        }
    }
}
