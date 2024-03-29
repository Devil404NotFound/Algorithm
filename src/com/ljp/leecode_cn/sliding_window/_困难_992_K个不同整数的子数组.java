package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.09
 * @author lijunpeng
 * @date 2021/2/9 21:31
992. K 个不同整数的子数组
给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。

（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）

返回 A 中好子数组的数目。



示例 1：

输入：A = [1,2,1,2,3], K = 2
输出：7
解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
示例 2：

输入：A = [1,2,1,3,4], K = 3
输出：3
解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].


提示：

1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length  
 */
public class _困难_992_K个不同整数的子数组 {
    /**
     * 官方题解：滑动窗口
     * @param A
     * @param K
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了86.55%的用户
    内存消耗：
    41.5 MB, 在所有 Java 提交中击败了85.67%的用户
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        //最多为K的子数组 - 最多为K - 1的子数组 所得结果即为恰好为K的子数组个数
        return maxSubarraysWithKDistinct(A, K) - maxSubarraysWithKDistinct(A, K - 1);
    }
    //求不同整数的个数最多为K的子数组
    private int maxSubarraysWithKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0, right = 0;
        int count = 0;
        int res = 0;
        while(right < len) {
            if(freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;
            while(count > K) {
                freq[A[left]]--;
                if(freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }
}
