package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.18
 * @author lijunpeng
 * @date 2021/2/18 23:06
995. K 连续位的最小翻转次数
在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。

返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。



示例 1：

输入：A = [0,1,0], K = 1
输出：2
解释：先翻转 A[0]，然后翻转 A[2]。
示例 2：

输入：A = [1,1,0], K = 2
输出：-1
解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
示例 3：

输入：A = [0,0,0,1,0,1,1,0], K = 3
输出：3
解释：
翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]


提示：

1 <= A.length <= 30000
1 <= K <= A.length
 */
public class _困难_995_K连续位的最小翻转次数 {
    /**
     * 官方题解一：查分数组
     * @param A
     * @param K
     * @return
    执行用时：
    6 ms, 在所有 Java 提交中击败了83.23%的用户
    内存消耗：
    46.9 MB, 在所有 Java 提交中击败了12.50%的用户
     */
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            if ((A[i] + revCnt) % 2 == 0) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + K];
            }
        }
        return ans;
    }
    /**
     * 官方题解一：差分数组-二进制实现
     * @param A
     * @param K
     * @return
    执行用时：
    4 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：
    46.9 MB, 在所有 Java 提交中击败了13.75%的用户
     */
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        int ans = 0;
        int revCnt = 0;
        int[] diff = new int[n + 1];
        for(int i = 0; i < n; ++i) {
            revCnt ^= diff[i];
            if(revCnt == A[i]) {
                if(i + K > n) {
                    return -1;
                }
                ans++;
                revCnt ^= 1;
                diff[i + K] ^= 1;
            }
        }
        return ans;
    }
}
