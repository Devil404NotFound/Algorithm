package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.06
 * @author lijunpeng
 * @date 2021/2/6 23:41
 */
public class _中等_1423_可获得的最大点数 {
    /**
     * 滑动窗口
     * @param cardPoints
     * @param k
     * @return
    执行用时：
    2 ms, 在所有 Java 提交中击败了95.77%的用户
    内存消耗：
    47.6 MB, 在所有 Java 提交中击败了63.02%的用户
     */
    public int maxScore(int[] cardPoints, int k) {
        int max = 0;
        for(int i = 0; i < k; ++i) {
            max += cardPoints[i];
        }
        int ans = max;
        int len = cardPoints.length;
        for(int i = 0; i < k; ++i) {
            max += cardPoints[len - i - 1] - cardPoints[k - i - 1];
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
