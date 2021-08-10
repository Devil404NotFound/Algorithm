package com.ljp.leecode_cn.sliding_window;

/** 每日一题 2021.02.05 【滑动窗口、双指针】
 * @author lijunpeng
 * @date 2021/2/5 12:02
 */
public class _中等_1208_尽可能使字符串相等 {
    /**
     *滑动窗口
     * @param s
     * @param t
     * @param maxCost
     * @return
    执行用时：
    5 ms, 在所有 Java 提交中击败了94.88%的用户
    内存消耗：
    38.7 MB, 在所有 Java 提交中击败了41.47%的用户
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        //开销预处理
        int[] costs = new int[len];
        for(int i = 0; i < len; ++i) {
            costs[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }
        //初始化窗口
        int left = 0, right = 0;
        while(right < len && maxCost - costs[right] >= 0) {
            maxCost -= costs[right];
            right++;
        }
        //滑动窗口
        int ans = right;
        while(right < len) {
            maxCost -= costs[right];
            while(left < len && maxCost < 0) {
                maxCost += costs[left];
                left++;
            }
            right++;
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
