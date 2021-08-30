package com.ljp.leecode_cn.array;

import java.util.Random;

/** 每日一题 2021.08.30
 * @author lijunpeng
 * @date 2021/8/30 22:48
 * @Description
528. 按权重随机选择
给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

也就是说，选取下标 i 的概率为 w[i] / sum(w) 。



示例 1：

输入：
["Solution","pickIndex"]
[[[1]],[]]
输出：
[null,0]
解释：
Solution solution = new Solution([1]);
solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
示例 2：

输入：
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
输出：
[null,1,1,1,1,0]
解释：
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 1
solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。

由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
诸若此类。


提示：

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex 将被调用不超过 10000 次
 */
public class _中等_528_按权重随机选择 {
    /** 前缀和+二分查找
    * @Author lijunpeng
    * @Date 2021/8/30 23:04
    * @Description
    执行用时：
    27 ms, 在所有 Java 提交中击败了72.00%的用户
    内存消耗：
    42.9 MB, 在所有 Java 提交中击败了85.78%的用户
    **/
    class Solution {
        int[] array;
        public Solution(int[] w) {
            array = new int[w.length];
            int sum = 0;
            for(int i = 0; i < array.length; ++i) {
                sum += w[i];
                array[i] = sum;
            }
        }

        public int pickIndex() {
            int num = new Random().nextInt(array[this.array.length - 1]) + 1;
//            int num = (int)(Math.random() * total) + 1;
            return binarySearch(num);
        }
        private int binarySearch(int num) {
            int left = 0, right = this.array.length - 1;
            while(left < right) {
                int mid = left + ((right - left) >> 1);
                if(this.array[mid] < num) {
                    left = mid + 1;
                }else {
                    right = mid; //注意这里不用-1
                }
            }
            return left;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}
